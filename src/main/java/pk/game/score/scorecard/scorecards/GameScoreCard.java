package pk.game.score.scorecard.scorecards;

import pk.game.score.scorable.Scorable;
import pk.game.score.scorecard.AbstractScoreCard;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class GameScoreCard extends AbstractScoreCard {

    public static final int WIN_SCORE = 6000; // The minimum score needed by a player to win

    @Override
    public void addScore(Scorable face, int count) {
        // If there is already a record of this Face just increase by count, otherwise set to count
        super.getScoreCount().compute(face, (k, v) -> (Objects.isNull(v)) ? count : v+count);
    }

    @Override
    public int totalScore() {
        Set<Map.Entry<Scorable, Integer>> entrySet = super.getScoreCount().entrySet();

        // Sum up the score
        return entrySet.stream().mapToInt(e -> e.getKey().getScore()*e.getValue()).sum();
    }

    /**
     *
     * @param scoreCard The {@link AbstractScoreCard} to merge into this one
     */
    public void merge(AbstractScoreCard scoreCard) {
        scoreCard.getScoreCount().forEach((k, v) -> {
            super.getScoreCount().merge(k, v, Integer::sum);
        });
    }

    /**
     * Clear the {@link GameScoreCard}
     */
    @Override
    public void clear() {
        super.getScoreCount().clear();
    }
}
