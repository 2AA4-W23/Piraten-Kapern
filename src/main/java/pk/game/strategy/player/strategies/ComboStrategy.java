package pk.game.strategy.player.strategies;

import pk.game.player.Player;
import pk.game.strategy.player.PlayerStrategy;

public class ComboStrategy implements PlayerStrategy {
    private final static ComboStrategy INSTANCE = new ComboStrategy();

    /**
     *
     * @return The instance of {@link ComboStrategy}
     */
    public static ComboStrategy getInstance() {
        return ComboStrategy.INSTANCE;
    }

    private ComboStrategy() {}

    @Override
    public void otherRolls(Player player) {
    }

    @Override
    public boolean shouldEndTurn(Player player) {
        return false;
    }
}
