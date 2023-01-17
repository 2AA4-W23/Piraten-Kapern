package pk.game.player;

import pk.game.count.Counter;
import pk.game.dice.Dice;
import pk.game.score.ScoreCard;

public class Player {

    private final ScoreCard scoreCard;
    private final ScoreCard turnScoreCard;
    private final Dice dice;
    private final Counter wins;
    private final Counter turnsPlayed;
    private final Counter rollsPlayed;
    private final Counter skullsRolled;
    private boolean isTurnOver;

    public Player() {
        this.scoreCard = new ScoreCard();
        this.turnScoreCard = new ScoreCard();
        this.dice = new Dice();
        this.wins = new Counter();
        this.turnsPlayed = new Counter();
        this.rollsPlayed = new Counter();
        this.skullsRolled = new Counter();
        this.isTurnOver = false;
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
     * @return The scorecard used to keep track of the score during the current turn
     */
    public ScoreCard getTurnScoreCard() {
        return this.turnScoreCard;
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

    /**
     *
     * @return The number of rolls used by the player in this turn
     */
    public Counter getRollsPlayed() {
        return this.rollsPlayed;
    }

    /**
     *
     * @return The counter used to keep track of the number of skulls rolled during current turn
     */
    public Counter getSkullsRolled() {
        return this.skullsRolled;
    }

    /**
     *
     * @return Is the players turn over?
     */
    public boolean isTurnOver() {
        return this.isTurnOver;
    }

    /**
     *
     * @param turnOver The new value indicating whether the players turn is over or not
     */
    public void setTurnOver(boolean turnOver) {
        this.isTurnOver = turnOver;
    }
}
