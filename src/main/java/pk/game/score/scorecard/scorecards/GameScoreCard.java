package pk.game.score.scorecard.scorecards;

import pk.game.score.scorable.Scorable;
import pk.game.score.scorecard.AbstractScoreCard;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class GameScoreCard extends AbstractScoreCard {

    /**
     *
     * @param scoreCard The {@link AbstractScoreCard} to merge into this one
     */
    public void merge(AbstractScoreCard scoreCard) {
        scoreCard.getScoreEntrySet().forEach(e -> {
            super.getScoreCount().merge(e.getKey(), e.getValue(), Integer::sum);
        });
    }
}
