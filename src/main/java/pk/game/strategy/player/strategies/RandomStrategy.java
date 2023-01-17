package pk.game.strategy.player.strategies;

import pk.game.dice.Dice;
import pk.game.dice.Faces;
import pk.game.player.Player;
import pk.game.strategy.player.PlayerStrategy;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RandomStrategy implements PlayerStrategy {

    private static final RandomStrategy INSTANCE = new RandomStrategy();

    /**
     *
     * @return Get a reference to the random strategy instance
     */
    public static RandomStrategy getInstance() {
        return RandomStrategy.INSTANCE;
    }

    private RandomStrategy() {}

    @Override
    public void use(Player player) {
        Faces[] rollResults;

        if(player.getRollsPlayed().getCount() == 0) { // Players first roll?
            rollResults = player.getDice().rollNTimes(Dice.MAX_DICE);
        } else {
            rollResults = player.getDice()
                    .rollRandTimes(Dice.MIN_DICE, Dice.MAX_DICE-player.getSkullsRolled().getCount());
        }

        // Increment the number of rolls played by user
        player.getRollsPlayed().add(1);

        Map<Faces, Integer> rollMap = Arrays.stream(rollResults).collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.summingInt(e -> 1)
        ));

        // Add scores to this turns scorecard
        rollMap.forEach((k, v) -> player.getTurnScoreCard().addScore(k, v));

        // Count the number of skulls rolled
        player.getSkullsRolled().add(rollMap.getOrDefault(Faces.SKULL, 0));

        // Is the players turn over? Either by choice or 3 skulls rolled
        boolean isTurnOver = player.getSkullsRolled().getCount() >= 3 || (new Random().nextBoolean());
        player.setTurnOver(isTurnOver);
    }
}
