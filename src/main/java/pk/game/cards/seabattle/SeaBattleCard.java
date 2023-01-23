package pk.game.cards.seabattle;

import pk.game.cards.AbstractCard;
import pk.game.player.Player;
import pk.game.score.scorable.Faces;
import pk.game.score.scorable.Scorable;
import pk.game.strategy.player.strategies.cards.SeaBattleStrategy;

public class SeaBattleCard extends AbstractCard {

    public static final String NAME = "Sea Battle";

    private final int numSwords;
    private final Scorable scorable;
    private final SeaBattleStrategy strategy;

    public SeaBattleCard(Scorable bonus, int numSwords) {
        super(SeaBattleCard.NAME);
        this.numSwords = numSwords;
        this.scorable = bonus;
        this.strategy = new SeaBattleStrategy(this);
    }

    /**
     *
     * @return The number of swords needed by this {@link SeaBattleCard}
     */
    public int getNumSwords() {
        return this.numSwords;
    }

    /**
     *
     * @return The {@link Scorable} tied with this {@link pk.game.cards.Card}
     */
    public Scorable getScoreable() {
        return this.scorable;
    }

    @Override
    public SeaBattleStrategy getStrategy() {
        return this.strategy;
    }

    @Override
    public void use(Player player) {
        super.use(player);
        // The number of swords rolled by the player
        int numSwords = player.getTurnScoreCard().getScoreCount().getOrDefault(Faces.SABER, 0);
        if(numSwords >= this.getNumSwords()) { // Did the player roll enough swords?
            player.getTurnScoreCard().addScore(this.getScoreable(), 1);
        } else {
            player.getTurnScoreCard().clear();
            player.getTurnScoreCard().getScoreCount().put(this.getScoreable(), -1);
        }
    }
}
