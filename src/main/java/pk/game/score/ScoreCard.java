package pk.game.score;

import pk.game.dice.Faces;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ScoreCard {

    // HashMap to keep count of the
    private final HashMap<Faces, Integer> scoreCount;

    public ScoreCard() {
        this.scoreCount = new HashMap<>();
    }

    /**
     *
     * @return The hashmap used to keep track of the score
     */
    public HashMap<Faces, Integer> getScoreCount() {
        return this.scoreCount;
    }

    /**
     *
     * @param face The {@link pk.game.dice.Faces} to add
     * @param count The number of this {@link pk.game.dice.Faces} to add to the score
     */
    public void addScore(Faces face, int count) {
        // If there is already a record of this Face just increase by count, otherwise set to count
        this.getScoreCount().compute(face, (k, v) -> (Objects.isNull(v)) ? count : v+count);
    }

    /**
     *
     * @return The total score recorded by this scorecard
     */
    public int totalScore() {
        Set<Map.Entry<Faces, Integer>> entrySet = this.getScoreCount().entrySet();

        // Sum up the score
        return entrySet.stream().mapToInt(e -> e.getKey().getScore()*e.getValue()).sum();
    }

    /**
     *
     * @param scoreCard The scorecard to merge into this one
     */
    public void merge(ScoreCard scoreCard) {
        scoreCard.getScoreCount().forEach((k, v) -> {
            this.getScoreCount().merge(k, v, Integer::sum);
        });
    }

    /**
     * Clear the scorecard
     */
    public void clear() {
        this.getScoreCount().clear();
    }
}
