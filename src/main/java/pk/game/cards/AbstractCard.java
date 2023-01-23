package pk.game.cards;

import pk.game.player.Player;

public abstract class AbstractCard implements Card {

    private final String name;

    protected AbstractCard(String name) {
        this.name = name;
    }

    /**
     *
     * @return The name of this {@link Card}
     */
    public String getName() {
        return this.name;
    }

    @Override
    public abstract void use(Player player);
}
