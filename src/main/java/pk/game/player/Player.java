package pk.game.player;

import pk.game.count.Counter;
import pk.game.dice.Dice;
import pk.game.score.ScoreCard;

public class Player {

    private final ScoreCard scoreCard;
    private final Dice dice;
    private final Counter wins;

    public Player() {
        this.scoreCard = new ScoreCard();
        this.dice = new Dice();
        this.wins = new Counter();
    }

    /**
     *
     * @return The scorecard that belongs to this user
     */
    public ScoreCard getScoreCard() {
        return this.scoreCard;
    }

}
