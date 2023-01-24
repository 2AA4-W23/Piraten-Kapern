package pk.game.strategy.player.strategies.cards;

import pk.game.cards.seabattle.SeaBattleCard;
import pk.game.dice.Dice;
import pk.game.player.Player;
import pk.game.score.scorable.Faces;

import java.util.stream.Stream;

public class SeaBattleStrategy extends AbstractCardStrategy<SeaBattleCard> {

    public SeaBattleStrategy(SeaBattleCard card) {
        super(card);
    }

    @Override
    public void otherRolls(Player player) {
        int numSabers = this.getNumSabers(player);

        if(numSabers < this.getCard().getNumSwords()) { // Does the player not have enough sabers?
            this.getNonSaberDice(player).forEach(Dice::roll);
        }
    }

    @Override
    public boolean shouldEndTurn(Player player) {
        return getNumSabers(player) >= this.getCard().getNumSwords();
    }

    /**
     *
     * @param player The {@link Player} using this strategy
     * @return The number of {@link Faces#SABER} the {@link Player} has
     */
    private int getNumSabers(Player player) {
        return player.getTurnScoreCard().getScoreCount().getOrDefault(Faces.SABER, 0);
    }

    /**
     *
     * @param player The {@link Player} using this strategy
     * @return The dice that are not {@link Faces#SABER}
     */
    private Stream<Dice> getNonSaberDice(Player player) {
        return player.getDiceHolder().getRollableDice().filter(d -> Faces.SABER != d.getFace());
    }
}