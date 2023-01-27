package pk.game.cards;

import pk.game.player.Player;
import pk.game.score.scorable.Cards;
import pk.game.strategy.player.PlayerStrategy;

public class CaptainCard extends AbstractCard {

    public static final String NAME = "Captain";
    public CaptainCard() {
        super(CaptainCard.NAME);
    }

    @Override
    public PlayerStrategy getStrategy() {
        return null;
    }

    @Override
    public boolean shouldUse(Player player) {
        return player.isTurnOver();
    }

    @Override
    public void action(Player player) {
        int totalScore = player.getTurnScoreCard().totalScore();
        int count = totalScore / Cards.CAPTAIN_TOKEN.getScore();

        player.getTurnScoreCard().addScore(Cards.CAPTAIN_TOKEN, count);
    }
}
