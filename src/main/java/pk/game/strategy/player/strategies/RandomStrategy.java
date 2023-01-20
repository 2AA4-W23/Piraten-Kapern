package pk.game.strategy.player.strategies;

import pk.game.Util;
import pk.game.dice.Dice;
import pk.game.score.scorable.Faces;
import pk.game.player.Player;
import pk.game.strategy.player.PlayerStrategy;
import pk.logging.GameLogger;

import java.util.Arrays;
import java.util.Map;

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
        PlayerStrategy.super.use(player);

        Map<Faces, Integer> rollMap = player.getDiceHolder().getFacesMap();

        // Add scores to this turns scorecard
        player.getTurnScoreCard().addAll(rollMap);

        // Is the players turn over? Either by choice or 3 skulls rolled
        boolean threeSkullsRolled = player.getDiceHolder().getSkullCount() >= 3;
        boolean playerTurnChoice = (Util.RANDOM.nextBoolean());

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

    @Override
    public void firstRoll(Player player) {
        player.getDiceHolder().getRollableDice().forEach(Dice::roll);
    }

    @Override
    public void otherRolls(Player player) {
        int numDiceRoll = Util.RANDOM.nextInt(Dice.MIN_DICE, Dice.MAX_DICE-player.getDiceHolder().getSkullCount());
        player.getDiceHolder().getRollableDice().limit(numDiceRoll).forEach(Dice::roll);
    }
}
