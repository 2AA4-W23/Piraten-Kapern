package pk.game.strategy.player.strategies.cards;

import pk.game.GameRules;
import pk.game.cards.MonkeyBusinessCard;
import pk.game.dice.Dice;
import pk.game.player.Player;
import pk.game.score.scorable.Faces;

import java.util.stream.Stream;

public class MonkeyBusinessStrategy extends AbstractCardStrategy<MonkeyBusinessCard> {

    public MonkeyBusinessStrategy(MonkeyBusinessCard card) {
        super(card);
    }

    @Override
    public void otherRolls(Player player) {
        GameRules.roll(this.getMonkeyParrotDice(player), player);
    }

    @Override
    public boolean shouldEndTurn(Player player) {
        return player.getTurnScoreCard().getScore(Faces.SKULL) == 2;
    }

    /**
     *
     * @param player The {@link Player} using this strategy
     * @return A {@link Stream} of {@link Dice} that are neither {@link Faces#PARROT} nor {@link Faces#MONKEY}
     */
    private Stream<Dice> getMonkeyParrotDice(Player player) {
        return player.getDiceHolder().getRollableDice(d -> !Faces.MONKEY.equals(d.getFace()) && !Faces.PARROT.equals(d.getFace()));
    }
}
