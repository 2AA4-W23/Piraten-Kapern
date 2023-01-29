package pk.game.score.scorecard.scorecards;

import pk.game.GameRules;
import pk.game.score.scorable.Bonus;
import pk.game.score.scorable.Faces;
import pk.game.score.scorable.Groups;
import pk.game.score.scorable.Scorable;
import pk.game.score.scorecard.ScoreCard;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class TurnScoreCard implements ScoreCard {

    private final Map<Scorable, Integer> scoreCount;
    private final Map<Scorable, Integer> reserve;

    public TurnScoreCard() {
        this.scoreCount = new HashMap<>();
        this.reserve = new HashMap<>();
    }

    /**
     *
     * @return The {@link HashMap<>} used to keep track of the score
     */
    private Map<Scorable, Integer> getScoreCount() {
        return this.scoreCount;
    }


    /**
     *
     * @return {@link TurnScoreCard#reserve}. This is a {@link Map} with all the scorable instances that will be added to the player roll
     */
    private Map<Scorable, Integer> getReserve() {
        return this.reserve;
    }

    /**
     *
     * @param key The {@link Scorable} key to get value of
     * @return The value associated with the given key
     */
    public Integer getFromReserve(Scorable key) {
        return this.getReserve().getOrDefault(key, 0);
    }

    /**
     *
     * @param reserve The {@link Scorable} to add to {@link TurnScoreCard#reserve}
     */
    public void putReserve(Scorable reserve, Integer count) {
        this.reserve.put(reserve, count);
    }

    /**
     *
     * @param key The key to get the value of
     * @return The value assigned to the key or 0 if there is no assignment
     */
    public Integer getScore(Scorable key) {
        return this.getScoreCount().getOrDefault(key, 0);
    }

    /**
     *
     * @return The set of all {@link Map.Entry} in the {@link HashMap}
     */
    public Set<Map.Entry<Scorable, Integer>> getScoreEntrySet() {
        return this.getScoreCount().entrySet();
    }

    /**
     *
     * @param key The key to put into the {@link HashMap}
     * @param count The value of key
     */
    public void putScore(Scorable key, Integer count) {
        this.getScoreCount().put(key, count);
    }

    /**
     *
     * @param face The {@link Scorable} to add
     * @param count The number of this {@link Faces} to add to the score
     */
    public void addScore(Scorable face, int count) {
        // If there is already a record of this Face just add to it
        this.getScoreCount().compute(face, (k, v) -> Objects.isNull(v) ? count : v+count);
    }

    /**
     *
     * @param scorable The {@link Scorable} to remove from
     * @param count The number of occurrences to remove
     */
    public void removeScore(Scorable scorable, int count) {
        this.getScoreCount().compute(scorable, (k, v) -> Objects.nonNull(v) && v != 0 ? v-count : 0);
    }

    /**
     *
     * @return The total score found in this score card
     */
    @Override
    public int totalScore() {
        Set<Map.Entry<Scorable, Integer>> entrySet = this.getScoreEntrySet();

        // Sum up the score
        return entrySet.stream().mapToInt(e -> e.getKey().getScore()*e.getValue()).sum();
    }

    /**
     *
     * @param map The map of the scorables to add and their counts
     */
    public void addAll(Map<? extends Scorable, Integer> map) {
        this.getScoreCount().clear(); // Clear old scores from last roll

        this.getScoreCount().putAll(map); // Add new scores

        if(!this.getReserve().isEmpty())  // Does the player have a reserved scorable this turn?
            this.getReserve().forEach(this::addScore);

        this.addCombos();

        if(GameRules.isBonusChestRoll(this)) { // Is there a bonus chest in the scorecard?
            this.addScore(Bonus.CHEST, 1);
        }
    }

    /**
     * Check for combinations and add them to scorecard
     */
    private void addCombos() {
        Map<Scorable, Integer> map = Map.copyOf(this.getScoreCount());

        // Look for combinations
        map.forEach((k, v) -> {
            if(!Faces.SKULL.equals(k)) {
                Groups group = GameRules.GROUP_MAP.get(v);
                if (Objects.nonNull(group)) {
                    this.addScore(group, 1);
                }
            }
        });
    }

    @Override
    public void clear() {
        this.getScoreCount().clear();
        this.getReserve().clear();
    }

    @Override
    public String toString() {
        return this.getScoreCount().toString();
    }
}
