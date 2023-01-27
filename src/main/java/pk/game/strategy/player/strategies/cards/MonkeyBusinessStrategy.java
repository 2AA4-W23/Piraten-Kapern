package pk.game.strategy.player.strategies.cards;

import pk.game.cards.MonkeyBusinessCard;
import pk.game.player.Player;

public class MonkeyBusinessStrategy extends AbstractCardStrategy<MonkeyBusinessCard> {

    public MonkeyBusinessStrategy(MonkeyBusinessCard card) {
        super(card);
    }

    @Override
    public void otherRolls(Player player) {
        
    }

    @Override
    public boolean shouldEndTurn(Player player) {
        return false;
    }
}
