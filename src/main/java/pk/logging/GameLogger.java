package pk.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class GameLogger {

    // Should the debug logs be printed to the console?
    public static boolean SHOULD_LOG_DEBUG = false;

    private GameLogger() {}

    /**
     *
     * @param message The message to log to the console
     */
    public static void debugLog(String message) {
        if(GameLogger.SHOULD_LOG_DEBUG) { // Has the game been launched in debug mode?
            Logger logger = LogManager.getRootLogger();
            Configurator.setLevel(logger, Level.TRACE);
            logger.trace(message); // Log the message to the console
        }
    }

}
