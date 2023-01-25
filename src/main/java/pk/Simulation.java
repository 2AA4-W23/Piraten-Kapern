package pk;

import pk.game.Game;
import pk.game.strategy.player.PlayerStrategy;
import pk.logging.GameLogger;

public class Simulation {

    // The number of simulations to run
    public static final int NUM_SIMS = 1000;

    // Class fields
    private final Game game;

    public Simulation(PlayerStrategy... strategies) {
        this.game = new Game(strategies);
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
        System.out.println("Simulating...");
        // Simulate games
        for(int i=1; i <= Simulation.NUM_SIMS; i++) {
            GameLogger.debugLog(String.format("Game #%d\n", i));

            // Simulate the game
            this.game.play();
            this.game.reset();

            GameLogger.debugLog(String.format("Game #%d complete!\n", i));
        }
    }

    /**
     * Display the statistics of the simulation
     */
    public void displayStats() {
        // Calculate win statistics for all players
        this.game.playerStream().forEach(p -> {
            int playerWins = p.getWins().getCount();
            double playerWinRate = playerWins/(double)Simulation.NUM_SIMS;
            System.out.printf(
                    "Player %d won %%%.2f of the games!\n",
                    p.getId(),
                    playerWinRate*100
            );
        });
    }
}