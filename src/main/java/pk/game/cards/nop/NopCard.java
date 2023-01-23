package pk.game.cards.nop;

import pk.game.cards.AbstractCard;
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
    public void use(Player player) {
        super.use(player);
    }
}
