package pk.game.cards;

import pk.game.player.Player;
import pk.game.score.scorable.Faces;
import pk.game.strategy.player.PlayerStrategy;

public class GoldCoinCard extends AbstractCard {

    public static final String NAME = "Gold Coin";

    public GoldCoinCard() {
        super(GoldCoinCard.NAME);
    }


    @Override
    public PlayerStrategy getStrategy() {
        return null;
    }

    @Override
    public boolean shouldUse(Player player) {
        return player.getRollsPlayed().getCount() == 0;
    }

    @Override
    public void action(Player player) {
        // Add extra gold coin
        player.getTurnScoreCard().putReserve(Faces.GOLD, 1);
    }
}
