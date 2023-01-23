package pk.game.strategy.player;

import pk.game.GameRules;
import pk.game.player.Player;
import pk.game.score.scorable.Faces;
import pk.logging.GameLogger;

import java.util.Arrays;
import java.util.Map;

/**
 * A strategy used by a player to play their turn
 */
public interface PlayerStrategy {
    /**
     *
     * @param player The {@link Player} to use the strategy on
     */
    void use(Player player);

    /**
     *
     * @param player The {@link Player} using this strategy
     */
    void roll(Player player);

    /**
     *
     * Handles the logic for all the rolls after the first roll
     * @param player The {@link Player} using this strategy
     */
    void otherRolls(Player player);

    /**
     * Add the score to the players scorecard
     * @param player The {@link Player} using this strategy
     */
    void score(Player player);

    /**
     * Logic used to determine whether the {@link Player} turn is over
     * @param player The {@link Player} using this strategy
     */
    void endTurn(Player player);

    /**
     *
     * @param player The {@link Player} using this strategy
     * @return A boolean representing whether the player should end their turn or not
     */
    boolean shouldEndTurn(Player player);

}
