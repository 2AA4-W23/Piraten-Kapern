package pk.game.cards;

import pk.game.player.Player;
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
    public void use(Player player) {
        GameLogger.debugLog(String.format("Using the %s card", this.getName()));
    }
}
