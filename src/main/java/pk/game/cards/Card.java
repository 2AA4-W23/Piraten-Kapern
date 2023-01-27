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
     * Determines what this card does to the player
     * @param player The {@link Player} using this {@link Card}
     */
    void action(Player player);

    /**
     *
     * @param player The {@link Player} using this {@link Card}
     * @return Whether the card should act or not. Cards can choose when to affect the player
     */
    boolean shouldUse(Player player);

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
