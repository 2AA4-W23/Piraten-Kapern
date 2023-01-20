package pk.game;

import pk.game.dice.Dice;
import pk.game.player.Player;

public class GameRules {

    public static final int MIN_DICE = 2; // Minimum number of dice a player can roll
    public static final int MAX_DICE = 8; // Maximum number of dice a player can roll
    public static final int WIN_SCORE = 6000; // The minimum score needed by a player to win

    /**
     *
     * @param player The {@link Player} to check skulls count
     * @return A boolean determining whether the number of dice the {@link Player} rolled end their turn or not
     */
    public static boolean playerSkullsEndTurn(Player player) {
        return player.getDiceHolder().getSkullCount() >= 3;
    }

    /**
     * Rolls a {@link Player} dice for their first roll in a turn
     * @param player The {@link Player} performing their first roll
     */
    public static void firstRoll(Player player) {
        player.getDiceHolder().diceStream().forEach(Dice::roll);
    }

}
