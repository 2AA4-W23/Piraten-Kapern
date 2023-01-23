package pk.game.strategy.player.strategies.cards;

import pk.game.cards.Card;
import pk.game.player.Player;
import pk.game.strategy.player.AbstractPlayerStrategy;

public abstract class AbstractCardStrategy<T extends Card> extends AbstractPlayerStrategy {

    private final T card;

    protected AbstractCardStrategy(T card) {
        this.card = card;
    }

    /**
     *
     * @return The {@link Card} for this strategy
     */
    public T getCard() {
        return this.card;
    }

    @Override
    public abstract void otherRolls(Player player);

    @Override
    public abstract boolean shouldEndTurn(Player player);
}
