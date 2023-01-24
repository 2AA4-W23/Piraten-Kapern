import pk.Simulation;
import pk.game.GameRules;
import pk.game.strategy.player.PlayerStrategy;
import pk.game.strategy.player.strategies.ComboStrategy;
import pk.game.strategy.player.strategies.RandomStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PiratenKarpen {

    public static void main(String[] args) {
        // Get player strategies
        PlayerStrategy[] strategies = handleStrategyInput(args);

        System.out.println("Welcome to Piraten Karpen Simulator!");

        // Simulate the game
        Simulation sim = new Simulation(strategies);
        sim.run();

        // Display simulation statistics
        sim.displayStats();
    }

    /**
     *
     * @param args The CMD arguments passed into the program
     * @return An array of {@link PlayerStrategy} to assign to the players
     */
    private static PlayerStrategy[] handleStrategyInput(String[] args) {
        // Get player strategies
        String randomStrategy = RandomStrategy.INPUT_NAME;
        String comboStrategy = ComboStrategy.INPUT_NAME;
        List<String> strategies = Arrays.stream(args)
                .filter(s -> s.equals(randomStrategy) || s.equals(comboStrategy))
                .collect(Collectors.toList());

        if(strategies.size() < GameRules.MIN_PLAYERS) { // Not enough strategies?
            System.out.printf(
                    "Invalid strategies, need at least %d! Only available strategies are %s and %s.",
                    GameRules.MIN_PLAYERS, randomStrategy, comboStrategy
            );
            System.exit(1);
        } else if(strategies.size() > GameRules.MAX_PLAYERS) {
            System.out.printf("Can only have a %d-%d players!", GameRules.MIN_PLAYERS, GameRules.MAX_PLAYERS);
            System.exit(1);
        }

        // The strategies that
        return strategies.stream().map(s -> {
            if(s.equals(randomStrategy)) // Random strategy?
                return RandomStrategy.getInstance();
            else if(s.equals(comboStrategy)) // Combo strategy?
                return ComboStrategy.getInstance();

            return null;
        }).toArray(PlayerStrategy[]::new);
    }
    
}
