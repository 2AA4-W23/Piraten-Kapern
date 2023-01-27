package pk.game.cards;

import pk.game.player.Player;
import pk.game.strategy.player.PlayerStrategy;
import pk.logging.GameLogger;

public abstract class AbstractCard implements Card {

    private final String name;

    protected AbstractCard(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public abstract PlayerStrategy getStrategy();

    @Override
    public abstract boolean shouldUse(Player player);

    @Override
    public abstract void action(Player player);

    @Override
    public void use(Player player) {
        if(this.shouldUse(player)) {
            GameLogger.debugLog(String.format("Using the %s card", this.getName()));
            this.action(player);
        }
    }
}
