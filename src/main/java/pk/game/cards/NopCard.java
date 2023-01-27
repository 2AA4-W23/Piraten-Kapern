package pk.game.cards;

import pk.game.player.Player;
import pk.game.strategy.player.PlayerStrategy;

public class NopCard extends AbstractCard {
    public static final String NAME = "NOP";

    private final PlayerStrategy strategy;

    public NopCard() {
        super(NopCard.NAME);
        this.strategy = null;
    }

    @Override
    public PlayerStrategy getStrategy() {
        return this.strategy;
    }

    @Override
    public boolean shouldUse(Player player) {
        return false;
    }

    @Override
    public void action(Player player) {}
}
