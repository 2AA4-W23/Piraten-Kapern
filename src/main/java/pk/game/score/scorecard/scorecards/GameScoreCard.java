package pk.game.score.scorecard.scorecards;

import pk.game.score.scorecard.AbstractScoreCard;

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
