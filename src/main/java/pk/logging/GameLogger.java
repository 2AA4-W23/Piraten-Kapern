package pk.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.util.StackLocator;

public class GameLogger {

    // Should the debug logs be printed to the console?
    public static boolean SHOULD_LOG_DEBUG = System.getProperties().containsKey("DEBUG");

    private GameLogger() {}

    /**
     *
     * @param message The message to log to the console
     */
    public static void debugLog(String message) {
        if(GameLogger.SHOULD_LOG_DEBUG) { // Has the game been launched in debug mode?
            Logger logger = LogManager.getLogger(StackLocator.getInstance().getCallerClass(2));
            Configurator.setLevel(logger, Level.DEBUG);
            logger.log(Level.DEBUG, message); // Log the message to the console
        }
    }

}
