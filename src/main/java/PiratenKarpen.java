import org.apache.commons.cli.Option;
import pk.Simulation;
import pk.cli.InputHandler;
import pk.game.GameRules;
import pk.game.strategy.player.PlayerStrategy;
import pk.game.strategy.player.strategies.ComboStrategy;
import pk.game.strategy.player.strategies.RandomStrategy;
import pk.logging.GameLogger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PiratenKarpen {

    public static void main(String[] args) {
        InputHandler handler = new InputHandler(args);

        // Handle request to display help menu
        handleHelpInput(handler);

        // Handle cmd input
        handleDebugInput(handler);
        PlayerStrategy[] strategies = handleStrategyInput(handler);
        Integer numGames = handleNumGamesInput(handler);

        System.out.println("Welcome to Piraten Karpen Simulator!");

        // Simulate the game
        Simulation sim = new Simulation(numGames, strategies);
        sim.run();

        // Display simulation statistics
        sim.displayStats();
    }

    /**
     *
     * @param handler The {@link InputHandler} to use to get input
     */
    private static void handleHelpInput(InputHandler handler) {
        if(handler.hasOption(InputHandler.getOption(InputHandler.HELP))) {
            handler.printHelp();
            System.exit(1);
        }
    }

    /**
     *
     * @param handler The {@link InputHandler} to use to get input
     */
    private static void handleDebugInput(InputHandler handler) {
        GameLogger.SHOULD_LOG_DEBUG = handler.hasOption(InputHandler.getOption(InputHandler.TRACE));
    }

    /**
     *
     * @param handler The {@link InputHandler} to use to get input
     * @return The number of games to play in {@link Simulation} as per given by user
     */
    private static Integer handleNumGamesInput(InputHandler handler) {
        Option gameOption = InputHandler.getOption(InputHandler.GAMES);

        if(handler.hasOption(gameOption)) { // Did the player pass in the number of games?
            String arg = handler.getOptionValue(gameOption);

            int numGames = 0;

            try {
                numGames = Integer.parseInt(arg);
            } catch(NumberFormatException e) { // Input not a number?
                System.out.printf("Invalid number %s\n", arg);
                handler.printHelp();
                System.exit(1);
            }

            if(numGames < GameRules.MIN_GAMES) { // Less than the minimum games allowed to play?
                System.out.println("Need to at least simulate 1 game!");
                handler.printHelp();
                System.exit(1);
            }

            return numGames;
        }

        // Missing number of games?
        System.out.println("Missing number of games to simulate!");
        handler.printHelp();
        System.exit(1);
        return null;
    }

    /**
     *
     * @param handler The {@link InputHandler} to get CMD arguments passed into the program
     * @return An array of {@link PlayerStrategy} to assign to the players
     */
    private static PlayerStrategy[] handleStrategyInput(InputHandler handler) {
        if(handler.hasOption(InputHandler.getOption(InputHandler.STRATEGIES))) {
            // Get strategies input
            String[] args = handler.getOptionValues(InputHandler.getOption(InputHandler.STRATEGIES));

            // Get player strategies
            String randomStrategy = RandomStrategy.INPUT_NAME;
            String comboStrategy = ComboStrategy.INPUT_NAME;
            List<String> strategies = Arrays.stream(args)
                    .filter(s -> s.equals(randomStrategy) || s.equals(comboStrategy))
                    .collect(Collectors.toList());

            if (args.length != strategies.size()) { // Is there a non-strategy value?
                // Get all strategy input that dont match expected input
                String[] invalidStrategies = Arrays.stream(args)
                        .filter(s -> !(s.equals(randomStrategy) || s.equals(comboStrategy)))
                        .toArray(String[]::new);

                // Display help to user
                System.out.printf("Invalid strategies %s\n", Arrays.toString(invalidStrategies));
                handler.printHelp();
                System.exit(1);
            } else if (strategies.size() < GameRules.MIN_PLAYERS) { // Not enough strategies?
                System.out.printf(
                        "Invalid strategies, need at least %d! Only available strategies are %s and %s.\n",
                        GameRules.MIN_PLAYERS, randomStrategy, comboStrategy
                );
                handler.printHelp();
                System.exit(1);
            } else if (strategies.size() > GameRules.MAX_PLAYERS) {
                System.out.printf("Can only have a %d-%d players!\n", GameRules.MIN_PLAYERS, GameRules.MAX_PLAYERS);
                handler.printHelp();
                System.exit(1);
            }

            // The strategies that
            return strategies.stream().map(s -> {
                if (s.equals(randomStrategy)) // Random strategy?
                    return RandomStrategy.getInstance();
                else if (s.equals(comboStrategy)) // Combo strategy?
                    return ComboStrategy.getInstance();

                return null;
            }).toArray(PlayerStrategy[]::new);
        }

        // Missing strategies?
        System.out.println("Missing strategies!");
        handler.printHelp();
        System.exit(1);
        return null;
    }
    
}
