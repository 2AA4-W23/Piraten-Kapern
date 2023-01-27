package pk.game.score.scorecard;

import pk.game.score.scorable.Faces;
import pk.game.score.scorable.Scorable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractScoreCard {

    // HashMap to keep count of the
    private final HashMap<Scorable, Integer> scoreCount;

    public AbstractScoreCard() {
        this.scoreCount = new HashMap<>();
    }

    /**
     *
     * @return The {@link HashMap<>} used to keep track of the score
     */
    protected HashMap<Scorable, Integer> getScoreCount() {
        return this.scoreCount;
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
     * Clear the {@link AbstractScoreCard}
     */
    public void clear() {
        this.getScoreCount().clear();
    }

    /**
     *
     * @return The total score found in this score card
     */
    public int totalScore() {
        Set<Map.Entry<Scorable, Integer>> entrySet = this.getScoreEntrySet();

        // Sum up the score
        return entrySet.stream().mapToInt(e -> e.getKey().getScore()*e.getValue()).sum();
    }

    @Override
    public String toString() {
        return this.getScoreCount().toString();
    }
}
