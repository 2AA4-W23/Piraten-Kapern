package pk.game.strategy.player;

import pk.game.GameRules;
import pk.game.player.Player;
import pk.game.score.scorable.Faces;
import pk.logging.GameLogger;

import java.util.Arrays;
import java.util.Map;

public abstract class AbstractPlayerStrategy implements PlayerStrategy {
    @Override
    public void use(Player player) {
        this.roll(player);
        this.score(player);
        this.endTurn(player);
    }

    @Override
    public void roll(Player player) {
        if(player.getRollsPlayed().getCount() == 0) { // Players first roll?
            GameRules.firstRoll(player);
        } else {
            this.otherRolls(player);
        }

        if(!player.isTurnOver()){ // Only register roll if players turn not interrupted
            // Increment the number of rolls played by user
            player.getRollsPlayed().add(1);
            Faces[] rollResults = player.getDiceHolder().getFaces().toArray(Faces[]::new);

            // Log each roll the user plays in their turn
            GameLogger.debugLog(String.format(
                    "Roll #%d: %s",
                    player.getRollsPlayed().getCount(),
                    Arrays.toString(rollResults)
            ));
        }
    }

    @Override
    public abstract void otherRolls(Player player);

    @Override
    public void score(Player player) {
        if(!player.isTurnOver()) {
            Map<Faces, Integer> rollMap = player.getDiceHolder().getFacesMap();

            // Add scores to this turns scorecard
            player.getTurnScoreCard().addAll(rollMap);
        }
    }

    @Override
    public void endTurn(Player player) {
        // Is the players turn over? Either by choice or 3 skulls rolled
        boolean threeSkullsRolled = GameRules.playerSkullsEndTurn(player);
        boolean playerTurnChoice = player.isTurnOver() || this.shouldEndTurn(player);

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
    public abstract boolean shouldEndTurn(Player player);
}
