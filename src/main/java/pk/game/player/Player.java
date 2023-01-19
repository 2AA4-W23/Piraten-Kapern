package pk.game.player;

import pk.game.count.Counter;
import pk.game.dice.Dice;
import pk.game.score.GameScoreCard;
import pk.game.strategy.player.PlayerStrategy;
import pk.game.strategy.player.strategies.RandomStrategy;
import pk.logging.GameLogger;

public class Player {

    private static int PLAYER_COUNT = 1;

    private final int id;
    private final PlayerStrategy strategy;
    private final GameScoreCard scoreCard;
    private final GameScoreCard turnScoreCard;
    private final Dice dice;
    private final Counter wins;
    private final Counter turnsPlayed;
    private final Counter rollsPlayed;
    private final Counter skullsRolled;
    private boolean isTurnOver;

    public Player() {
        this.id = Player.PLAYER_COUNT;
        this.strategy = RandomStrategy.getInstance();
        this.scoreCard = new GameScoreCard();
        this.turnScoreCard = new GameScoreCard();
        this.dice = new Dice();
        this.wins = new Counter();
        this.turnsPlayed = new Counter();
        this.rollsPlayed = new Counter();
        this.skullsRolled = new Counter();
        this.isTurnOver = false;

        Player.PLAYER_COUNT++;
    }

    /**
     *
     * @return The id of this player
     */
    public int getId() {
        return this.id;
    }

    /**
     *
     * @return The {@link GameScoreCard} that belongs to this user
     */
    public GameScoreCard getScoreCard() {
        return this.scoreCard;
    }

    /**
     *
     * @return The {@link GameScoreCard} used to keep track of the score during the current turn
     */
    public GameScoreCard getTurnScoreCard() {
        return this.turnScoreCard;
    }

    /**
     *
     * @return The {@link Dice} that belongs to this player
     */
    public Dice getDice() {
        return this.dice;
    }

    /**
     *
     * @return The {@link Counter} keeping track of the number of wins this player has
     */
    public Counter getWins() {
        return this.wins;
    }

    /**
     *
     * @return The {@link Counter} keeping track of the number of turns this player has played
     */
    public Counter getTurnsPlayed() {
        return this.turnsPlayed;
    }

    /**
     *
     * @return The {@link Counter} keeping track of the number of rolls used by the player in this turn
     */
    public Counter getRollsPlayed() {
        return this.rollsPlayed;
    }

    /**
     *
     * @return The {@link Counter} used to keep track of the number of skulls rolled during current turn
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

    /**
     * Reset the player so they can play their next turn
     */
    private void resetTurn() {
        this.getTurnScoreCard().clear();
        this.getSkullsRolled().reset();
        this.getRollsPlayed().reset();
        this.setTurnOver(false);
    }

    /**
     * Reset the player so they can play a new game
     */
    public void resetGame() {
        this.resetTurn();
        this.getScoreCard().clear();
        this.getTurnsPlayed().reset();
    }

    /**
     * Let this player play their turn
     */
    public void play() {
        GameLogger.debugLog(String.format(
                "Player #%d playing turn %d",
                this.getId(),
                this.getTurnsPlayed().getCount()+1
        ));

        do {
            this.strategy.use(this);
        } while (!this.isTurnOver()); // Is the players turn not yet over?

        // Log what has been collected during this turn
        GameLogger.debugLog(String.format(
                "Player #%d collected during turn %d: %s",
                this.getId(),
                this.getTurnsPlayed().getCount(),
                this.getTurnScoreCard()
        ));

        if(this.getSkullsRolled().getCount() < 3) { // Did the player not roll 3 or more skulls?
            // Count score collected in this turn
            this.getScoreCard().merge(this.getTurnScoreCard());
        }

        this.getTurnsPlayed().add(1);
        this.resetTurn();

        // Log player status after turn
        GameLogger.debugLog(this.toString());

        // Log end of turn
        GameLogger.debugLog(String.format(
                "Player #%d turn %d over\n",
                this.getId(),
                this.getTurnsPlayed().getCount()
        ));
    }

    @Override
    public String toString() {
        return "Player #" + this.getId() + " {" +
                "scoreCard=" + this.getScoreCard() +
                ", Total Score= " + this.getScoreCard().totalScore() +
                ", turnScoreCard=" + this.getTurnScoreCard() +
                ", Turn Total Score= " + this.getTurnScoreCard().totalScore() +
                ", wins=" + this.getWins().getCount() +
                ", turnsPlayed=" + this.getTurnsPlayed().getCount() +
                ", rollsPlayed=" + this.getRollsPlayed().getCount() +
                ", skullsRolled=" + this.getSkullsRolled().getCount() +
                ", isTurnOver=" + this.isTurnOver() +
                '}';
    }
}
