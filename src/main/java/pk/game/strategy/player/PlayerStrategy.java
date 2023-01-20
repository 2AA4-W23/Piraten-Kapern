package pk.game.strategy.player;

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
    default void use(Player player) {
        this.roll(player);
        this.score(player);
        this.endTurn(player);
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

    /**
     * Add the score to the players scorecard
     * @param player The {@link Player} using this strategy
     */
    default void score(Player player) {
        Map<Faces, Integer> rollMap = player.getDiceHolder().getFacesMap();

        // Add scores to this turns scorecard
        player.getTurnScoreCard().addAll(rollMap);
    }

    /**
     * Logic used to determine whether the {@link Player} turn is over
     * @param player The {@link Player} using this strategy
     */
    default void endTurn(Player player) {
        // Is the players turn over? Either by choice or 3 skulls rolled
        boolean threeSkullsRolled = player.getDiceHolder().getSkullCount() >= 3;
        boolean playerTurnChoice = this.shouldEndTurn(player);

        if(threeSkullsRolled) {
            // 3 skulls have been rolled so players turn is over
            GameLogger.debugLog(String.format(
                    "Player #%d turn ended because %d skulls have been rolled",
                    player.getId(),
                    player.getDiceHolder().getSkullCount()
            ));
        } else if(playerTurnChoice) {
            // Player decided to stop rolling
            GameLogger.debugLog(String.format(
                    "Player #%d chose to end their turn",
                    player.getId()
            ));
        }

        player.setTurnOver(threeSkullsRolled || playerTurnChoice);
    }

    /**
     *
     * @param player The {@link Player} using this strategy
     * @return A boolean representing whether the player should end their turn or not
     */
    boolean shouldEndTurn(Player player);

}
