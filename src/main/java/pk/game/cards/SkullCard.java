package pk.game.cards;

import pk.game.player.Player;
import pk.game.score.scorable.Faces;
import pk.game.strategy.player.PlayerStrategy;

public class SkullCard extends AbstractCard {

    public static final String NAME = "Skull";

    private final int skullCount;

    public SkullCard(int skullCount) {
        super(SkullCard.NAME);
        this.skullCount = skullCount;
    }

    @Override
    public PlayerStrategy getStrategy() {
        return null;
    }

    @Override
    public boolean shouldUse(Player player) {
        // Did the player not roll yet?
        return player.getRollsPlayed().getCount() == 0;
    }

    @Override
    public void action(Player player) {
        player.getTurnScoreCard().putReserve(Faces.SKULL, this.skullCount);
    }
}
