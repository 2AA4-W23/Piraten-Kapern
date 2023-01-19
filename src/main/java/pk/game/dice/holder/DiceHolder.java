package pk.game.dice.holder;

import pk.game.dice.Dice;

import java.util.Arrays;
import java.util.stream.Stream;

public class DiceHolder {

    // Class fields
    private final Dice[] dice;

    public DiceHolder() {
        this.dice = new Dice[Dice.MAX_DICE];
        Arrays.fill(this.dice, new Dice()); // Fill dice array with 8 dice
    }


    /**
     *
     * @return The array of all {@link Dice}s
     */
    public Dice[] getDice() {
        return this.dice;
    }

    /**
     *
     * @return The {@link Stream} of {@link Dice}s
     */
    public Stream<Dice> diceStream() {
        return Arrays.stream(this.getDice());
    }

}
