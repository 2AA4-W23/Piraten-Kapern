package pk.game.score.scorecard;

import pk.game.score.scorable.Faces;
import pk.game.score.scorable.Scorable;

import java.util.HashMap;

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
    public HashMap<Scorable, Integer> getScoreCount() {
        return this.scoreCount;
    }

    /**
     *
     * @param face The {@link Scorable} to add
     * @param count The number of this {@link Faces} to add to the score
     */
    public abstract void addScore(Scorable face, int count);

    /**
     * Clear the {@link AbstractScoreCard}
     */
    public abstract void clear();

    @Override
    public String toString() {
        return this.getScoreCount().toString();
    }
}
