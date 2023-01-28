package pk.cli;

import org.apache.commons.cli.*;

import java.util.Map;

public class InputHandler {

    public static final String STRATEGIES = "strategies";
    public static final String TRACE = "TRACE";
    private static final Map<String, Option> OPTION = Map.of(
            STRATEGIES, Option.builder()
                    .argName("strategy1> <strategy2> ... <strategy5")
                    .option(STRATEGIES.substring(0, 1))
                    .longOpt(STRATEGIES)
                    .hasArgs()
                    .valueSeparator(' ')
                    .required(true)
                    .desc("set number of players in the game and their strategies (combo or random)")
                    .build(),
            TRACE, Option.builder()
                    .option(TRACE.substring(0, 1))
                    .longOpt(TRACE)
                    .desc("enables TRACING mode")
                    .hasArg(false)
                    .required(false)
                    .build()
    );

    private CommandLine cmd;
    private final HelpFormatter formatter;
    private final Options options;

    public InputHandler(String[] args) {
        // Set options
        this.options = new Options();
        InputHandler.OPTION.forEach((k, v) -> this.options.addOption(v));
        this.formatter = new HelpFormatter();

        try {
            // Parse data in array
            CommandLineParser parser = new DefaultParser();
            this.cmd = parser.parse(this.options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            this.printHelp();
            System.exit(1);
        }
    }

    /**
     *
     * @param option The key of the {@link Option} to get
     * @return The {@link Option} instance from {@link InputHandler#OPTION}
     */
    public static Option getOption(String option) {
        return (Option) InputHandler.OPTION.get(option).clone();
    }

    /**
     * Print the help string to the user
     */
    public void printHelp() {
        this.formatter.printHelp(200, "Piraten Kapern", "", options, "");
    }
}
