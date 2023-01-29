package pk.game.player;

import pk.game.GameRules;
import pk.game.cards.Card;
import pk.game.count.Counter;
import pk.game.dice.holder.DiceHolder;
import pk.game.score.scorecard.scorecards.GameScoreCard;
import pk.game.score.scorecard.scorecards.TurnScoreCard;
import pk.game.strategy.player.PlayerStrategy;
import pk.game.strategy.player.strategies.ComboStrategy;
import pk.logging.GameLogger;

import java.util.Objects;

public class Player {

    private static int PLAYER_COUNT = 1;

    private final int id;
    private final PlayerStrategy strategy;
    private final GameScoreCard scoreCard;
    private final TurnScoreCard turnScoreCard;
    private final DiceHolder diceHolder;
    private final Counter wins;
    private final Counter turnsPlayed;
    private final Counter rollsPlayed;
    private boolean isTurnOver;

    public Player(PlayerStrategy strategy) {
        this.id = Player.PLAYER_COUNT;
        this.strategy = strategy;
        this.scoreCard = new GameScoreCard();
        this.turnScoreCard = new TurnScoreCard();
        this.diceHolder = new DiceHolder();
        this.wins = new Counter();
        this.turnsPlayed = new Counter();
        this.rollsPlayed = new Counter();
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
    public TurnScoreCard getTurnScoreCard() {
        return this.turnScoreCard;
    }

    /**
     *
     * @return The {@link DiceHolder} that belongs to this player
     */
    public DiceHolder getDiceHolder() {
        return this.diceHolder;
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
     *
     * @return The name of the {@link PlayerStrategy} being used by this {@link Player}
     */
    public String getStrategyName() {
        return this.strategy.getClass().getSimpleName();
    }

    /**
     * Reset the player so they can play their next turn
     */
    private void resetTurn() {
        this.getTurnScoreCard().clear();
        this.getDiceHolder().reset();
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
    public void play(Card card) {
        GameLogger.debugLog(String.format(
                "Player #%d playing turn %d",
                this.getId(),
                this.getTurnsPlayed().getCount()+1
        ));

        // Select the player strategy to use
        PlayerStrategy strategy = Objects.nonNull(card.getStrategy()) && this.strategy instanceof ComboStrategy
                ? card.getStrategy() : this.strategy;

        do {
            GameLogger.debugLog(String.format("Using strategy: %s", strategy.getClass().getSimpleName()));
            card.use(this);
            strategy.use(this);
            card.use(this);
        } while (!this.isTurnOver()); // Is the players turn not yet over?

        // Increment number of turns played
        this.getTurnsPlayed().add(1);

        // Log what has been collected during this turn
        GameLogger.debugLog(String.format(
                "Player #%d collected during turn %d: %s",
                this.getId(),
                this.getTurnsPlayed().getCount(),
                this.getTurnScoreCard()
        ));

        if(!GameRules.playerSkullsEndTurn(this)) { // Did the player not roll 3 or more skulls?
            // Count score collected in this turn
            this.getScoreCard().merge(this.getTurnScoreCard());
        }

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
                "diceHolder=" + this.getDiceHolder() +
                ", Total Score= " + this.getScoreCard().totalScore() +
                ", turnScoreCard=" + this.getTurnScoreCard() +
                ", Turn Total Score= " + this.getTurnScoreCard().totalScore() +
                ", wins=" + this.getWins().getCount() +
                ", turnsPlayed=" + this.getTurnsPlayed().getCount() +
                ", rollsPlayed=" + this.getRollsPlayed().getCount() +
                ", isTurnOver=" + this.isTurnOver() +
                '}';
    }
}
