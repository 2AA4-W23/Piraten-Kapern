package pk.game.strategy.player;

import pk.game.player.Player;
import pk.game.score.scorable.Faces;
import pk.logging.GameLogger;

import java.util.Arrays;

/**
 * A strategy used by a player to play their turn
 */
public interface PlayerStrategy {
    /**
     *
     * @param player The {@link Player} to use the strategy on
     */
    default void use(Player player) {
        this.roll(player);
    }

    /**
     *
     * @param player The {@link Player} using this strategy
     */
    default void roll(Player player) {
        if(player.getRollsPlayed().getCount() == 0) { // Players first roll?
            this.firstRoll(player);
        } else {
            this.otherRolls(player);
        }

        // Increment the number of rolls played by user
        player.getRollsPlayed().add(1);
        Faces[] rollResults = player.getDiceHolder().getFaces().toArray(Faces[]::new);

        // Log each roll the user plays in their turn
        GameLogger.debugLog(String.format(
                "Roll #%d: %s",
                player.getRollsPlayed().getCount(),
                Arrays.toString(rollResults)
        ));
    }

    /**
     *
     * Handles the logic for the first roll done by the player
     * @param player The {@link Player} using this strategy
     */
    void firstRoll(Player player);

    /**
     *
     * Handles the logic for all the rolls after the first roll
     * @param player The {@link Player} using this strategy
     */
    void otherRolls(Player player);

}
