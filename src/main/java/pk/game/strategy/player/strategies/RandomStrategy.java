package pk.game.strategy.player.strategies;

import pk.game.dice.Dice;
import pk.game.score.scorable.Faces;
import pk.game.player.Player;
import pk.game.strategy.player.PlayerStrategy;
import pk.logging.GameLogger;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RandomStrategy implements PlayerStrategy {

    private static final RandomStrategy INSTANCE = new RandomStrategy();

    /**
     *
     * @return Get a reference to the {@link RandomStrategy} {@link RandomStrategy#INSTANCE}
     */
    public static RandomStrategy getInstance() {
        return RandomStrategy.INSTANCE;
    }

    private RandomStrategy() {}

    @Override
    public void use(Player player) {
        Random random = new Random();

        if(player.getRollsPlayed().getCount() == 0) { // Players first roll?
            player.getDiceHolder().getRollableDice().forEach(Dice::roll);
        } else {
            int diceToRoll = random.nextInt(Dice.MIN_DICE, Dice.MAX_DICE-player.getDiceHolder().getSkullCount());
            player.getDiceHolder().getRollableDice().limit(diceToRoll).forEach(Dice::roll);
        }

        Faces[] rollResults = player.getDiceHolder().getFaces().toArray(Faces[]::new);

        // Increment the number of rolls played by user
        player.getRollsPlayed().add(1);

        // Log each roll the user plays in their turn
        GameLogger.debugLog(String.format(
                "Roll #%d: %s",
                player.getRollsPlayed().getCount(),
                Arrays.toString(rollResults)
        ));

        Map<Faces, Integer> rollMap = player.getDiceHolder().getFacesMap();

        // Add scores to this turns scorecard
        rollMap.forEach((k, v) -> player.getTurnScoreCard().addScore(k, v));

        // Is the players turn over? Either by choice or 3 skulls rolled
        boolean threeSkullsRolled = player.getDiceHolder().getSkullCount() >= 3;
        boolean playerTurnChoice = (random.nextBoolean());

        if(threeSkullsRolled) {
            // 3 skulls have been rolled so players turn is over
            GameLogger.debugLog(String.format(
                    "Player #%d turn ended because %d skulls have been rolled",
                    player.getId(),
                    player.getDiceHolder().getSkullCount()
            ));
        } else if(playerTurnChoice) {
            // Player decided to stop rolling
            GameLogger.debugLog(String.format(
                    "Player #%d chose to end their turn",
                    player.getId()
            ));
        }

        player.setTurnOver(threeSkullsRolled || playerTurnChoice);
    }
}
