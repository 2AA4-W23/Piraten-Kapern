package pk.game;

import pk.game.dice.Dice;
import pk.game.player.Player;

public class GameRules {

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
