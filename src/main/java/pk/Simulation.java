package pk;

import pk.game.Game;

public class Simulation {

    // The number of simulations to run
    public static final int NUM_SIMS = 42;

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

    /**
     * Run the simulation
     */
    public void run() {
        // Simulate games
        for(int i=0; i < Simulation.NUM_SIMS; i++) {
            // Simulate the game
            this.game.play();
            this.game.reset();
        }
    }
}