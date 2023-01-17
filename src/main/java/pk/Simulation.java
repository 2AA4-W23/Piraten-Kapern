package pk;

import pk.game.Game;

public class Simulation {
    // Class fields
    private final Game game;

    public Simulation() {
        this.game = new Game();
    }

    /**
     *
     * @return The game being used by this simulation
     */
    public Game getGame() {
        return this.game;
    }
}