package pk.game.strategy.player;

import pk.game.player.Player;

/**
 * A strategy used by a player to play their turn
 */
public interface PlayerStrategy {
    void use(Player player);
}
