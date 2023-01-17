package pk.game.strategy.player.strategies;

import pk.game.player.Player;
import pk.game.strategy.player.PlayerStrategy;

public class RandomStrategy implements PlayerStrategy {

    private static final RandomStrategy INSTANCE = new RandomStrategy();

    /**
     *
     * @return Get a reference to the random strategy instance
     */
    public static RandomStrategy getInstance() {
        return RandomStrategy.INSTANCE;
    }

    private RandomStrategy() {}

    @Override
    public void use(Player player) {

    }
}
