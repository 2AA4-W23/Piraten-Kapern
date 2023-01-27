package pk.game.cards;

import pk.game.player.Player;
import pk.game.score.scorable.Faces;
import pk.game.strategy.player.PlayerStrategy;

public class DiamondCard extends AbstractCard {

    public static final String NAME = "Diamond";

    public DiamondCard() {
        super(DiamondCard.NAME);
    }

    @Override
    public PlayerStrategy getStrategy() {
        return null;
    }

    @Override
    public boolean shouldUse(Player player) {
        // Player did not roll yet?
        return player.getRollsPlayed().getCount() == 0;
    }

    @Override
    public void action(Player player) {
        // Add diamond to player scorecard
        player.getTurnScoreCard().putReserve(Faces.DIAMOND);
    }
}
