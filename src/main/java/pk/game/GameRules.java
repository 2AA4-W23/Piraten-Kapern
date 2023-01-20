package pk.game;

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

}
