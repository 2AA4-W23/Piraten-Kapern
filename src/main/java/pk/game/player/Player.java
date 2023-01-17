package pk.game.player;

import pk.game.count.Counter;
import pk.game.dice.Dice;
import pk.game.score.ScoreCard;

public class Player {

    private final ScoreCard scoreCard;
    private final Dice dice;
    private final Counter wins;
    private final Counter turnsPlayed;

    public Player() {
        this.scoreCard = new ScoreCard();
        this.dice = new Dice();
        this.wins = new Counter();
        this.turnsPlayed = new Counter();
    }

    /**
     *
     * @return The scorecard that belongs to this user
     */
    public ScoreCard getScoreCard() {
        return this.scoreCard;
    }

    /**
     *
     * @return The dice that belongs to this player
     */
    public Dice getDice() {
        return this.dice;
    }

    /**
     *
     * @return The counter keeping track of the number of wins this player has
     */
    public Counter getWins() {
        return this.wins;
    }

    /**
     *
     * @return The counter keeping track of the number of turns this player has played
     */
    public Counter getTurnsPlayed() {
        return this.turnsPlayed;
    }
}
