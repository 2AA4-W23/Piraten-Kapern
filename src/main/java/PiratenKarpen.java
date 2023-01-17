import pk.game.Game;

public class PiratenKarpen {

    // The number of simulations to run
    private static final int NUM_SIMS = 42;

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");

        // Simulate the game
        Game game = new Game();
        for(int i=0; i < PiratenKarpen.NUM_SIMS; i++) {
            game.play();
            game.reset();
        }

        // Calculate win statistics for both players
        int p1Wins = game.getPlayer1().getWins().getCount();
        int p2Wins = game.getPlayer2().getWins().getCount();
        double p1WinRate = p1Wins/(double)PiratenKarpen.NUM_SIMS;
        double p2WinRate = p2Wins/(double)PiratenKarpen.NUM_SIMS;

        // Print game results
        System.out.printf("Player 1 won %%%.2f of the games!\n", p1WinRate*100);
        System.out.printf("Player 2 won %%%.2f of the games!\n", p2WinRate*100);

    }
    
}
