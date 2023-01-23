package pk.game.cards;

import pk.game.player.Player;

public interface Card {
    /**
     *
     * @param player The {@link Player} using this card
     */
    void use(Player player);
}
