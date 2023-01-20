package pk.game.strategy.player.strategies;

import pk.game.GameRules;
import pk.game.Util;
import pk.game.dice.Dice;
import pk.game.player.Player;
import pk.game.strategy.player.PlayerStrategy;

public class RandomStrategy implements PlayerStrategy {

    private static final RandomStrategy INSTANCE = new RandomStrategy();

    /**
     *
     * @return Get a reference to the {@link RandomStrategy} {@link RandomStrategy#INSTANCE}
     */
    public static RandomStrategy getInstance() {
        return RandomStrategy.INSTANCE;
    }

    private RandomStrategy() {}

    @Override
    public void otherRolls(Player player) {
        int numDiceRoll = Util.RANDOM.nextInt(GameRules.MIN_DICE, GameRules.MAX_DICE-player.getDiceHolder().getSkullCount());
        player.getDiceHolder().getRollableDice().unordered().limit(numDiceRoll).forEach(Dice::roll);
    }

    @Override
    public boolean shouldEndTurn(Player player) {
        return Util.RANDOM.nextBoolean();
    }
}
