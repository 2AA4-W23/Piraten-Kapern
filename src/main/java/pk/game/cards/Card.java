package pk.game.cards;

import pk.game.player.Player;
import pk.game.strategy.player.PlayerStrategy;

public interface Card {
    /**
     *
     * @param player The {@link Player} using this card
     */
    void use(Player player);

    /**
     *
     * @return The name of this {@link Card}
     */
    String getName();

    /**
     *
     * @return The {@link PlayerStrategy} tied to this card
     */
    PlayerStrategy getStrategy();
}
