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
     * @return The {@link Game} being used by this simulation
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

    /**
     * Display the statistics of the simulation
     */
    public void displayStats() {
        // Calculate win statistics for both players
        int p1Wins = this.getGame().getPlayer1().getWins().getCount();
        int p2Wins = this.getGame().getPlayer2().getWins().getCount();
        double p1WinRate = p1Wins/(double)Simulation.NUM_SIMS;
        double p2WinRate = p2Wins/(double)Simulation.NUM_SIMS;

        // Print game results
        System.out.printf("Player 1 won %%%.2f of the games!\n", p1WinRate*100);
        System.out.printf("Player 2 won %%%.2f of the games!\n", p2WinRate*100);
    }
}