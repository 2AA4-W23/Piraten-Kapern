package pk.game.score;

import pk.game.score.scorable.Faces;
import pk.game.score.scorable.Scorable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ScoreCard {

    public static final int WIN_SCORE = 6000; // The minimum score needed by a player to win

    // HashMap to keep count of the
    private final HashMap<Scorable, Integer> scoreCount;

    public ScoreCard() {
        this.scoreCount = new HashMap<>();
    }

    /**
     *
     * @return The {@link HashMap<>} used to keep track of the score
     */
    public HashMap<Scorable, Integer> getScoreCount() {
        return this.scoreCount;
    }

    /**
     *
     * @param face The {@link Scorable} to add
     * @param count The number of this {@link Faces} to add to the score
     */
    public void addScore(Scorable face, int count) {
        // If there is already a record of this Face just increase by count, otherwise set to count
        this.getScoreCount().compute(face, (k, v) -> (Objects.isNull(v)) ? count : v+count);
    }

    /**
     *
     * @return The total score recorded by this {@link ScoreCard}
     */
    public int totalScore() {
        Set<Map.Entry<Scorable, Integer>> entrySet = this.getScoreCount().entrySet();

        // Sum up the score
        return entrySet.stream().mapToInt(e -> e.getKey().getScore()*e.getValue()).sum();
    }

    /**
     *
     * @param scoreCard The {@link ScoreCard} to merge into this one
     */
    public void merge(ScoreCard scoreCard) {
        scoreCard.getScoreCount().forEach((k, v) -> {
            this.getScoreCount().merge(k, v, Integer::sum);
        });
    }

    /**
     * Clear the {@link ScoreCard}
     */
    public void clear() {
        this.getScoreCount().clear();
    }

    @Override
    public String toString() {
        return this.getScoreCount().toString();
    }
}
