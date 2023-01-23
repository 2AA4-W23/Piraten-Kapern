package pk.game.strategy.player.strategies;

import pk.game.dice.Dice;
import pk.game.player.Player;
import pk.game.score.scorable.Faces;
import pk.game.score.scorable.Groups;
import pk.game.strategy.player.AbstractPlayerStrategy;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComboStrategy extends AbstractPlayerStrategy {

    public final static String INPUT_NAME = "combo";
    private final static ComboStrategy INSTANCE = new ComboStrategy();

    /**
     *
     * @return The instance of {@link ComboStrategy}
     */
    public static ComboStrategy getInstance() {
        return ComboStrategy.INSTANCE;
    }

    private ComboStrategy() {}

    @Override
    public void otherRolls(Player player) {
        this.getNonComboDice(player).forEach(Dice::roll);
    }

    @Override
    public boolean shouldEndTurn(Player player) {
        // Did the player get 2 skulls OR does the player not have enough dice to roll another combination?
        return player.getDiceHolder().getSkullCount() == 2 || this.getNonComboDice(player).count() < Groups.GROUP_OF_3.getGroupSize();
    }

    /**
     *
     * @param player The {@link Player} using this strategy
     * @return A {@link Stream} object with all dice that are not a part of a combination
     */
    private Stream<Dice> getNonComboDice(Player player) {
        // Get the faces that
        Set<Map.Entry<Faces, Integer>> entrySet = player.getDiceHolder().getFacesMap().entrySet();
        Set<Faces> nonComboFaces = entrySet.stream()
                .filter(e -> e.getValue() < Groups.GROUP_OF_3.getGroupSize() && !Faces.SKULL.equals(e.getKey()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return player.getDiceHolder().getRollableDice().filter(d -> nonComboFaces.contains(d.getFace()));
    }
}
