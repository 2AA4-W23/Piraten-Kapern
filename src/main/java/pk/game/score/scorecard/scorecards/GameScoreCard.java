package pk.game.score.scorecard.scorecards;

import pk.game.score.scorecard.ScoreCard;

public class GameScoreCard implements ScoreCard {

    private int score;

    @Override
    public void clear() {
        this.score = 0;
    }

    @Override
    public int totalScore() {
        return this.score;
    }

    /**
     *
     * @param scoreCard The {@link TurnScoreCard} to merge into this one
     */
    public void merge(ScoreCard scoreCard) {
        this.score += scoreCard.totalScore();
    }

    @Override
    public String toString() {
        return "GameScoreCard{" +
                "score=" + score +
                '}';
    }
}
