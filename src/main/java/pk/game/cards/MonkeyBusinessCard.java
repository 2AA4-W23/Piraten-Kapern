package pk.game.cards;

import pk.game.strategy.player.PlayerStrategy;

public class MonkeyBusinessCard extends AbstractCard {

    public static final String NAME = "Monkey Business";

    public MonkeyBusinessCard() {
        super(MonkeyBusinessCard.NAME);
    }

    @Override
    public PlayerStrategy getStrategy() {
        return null;
    }
}
