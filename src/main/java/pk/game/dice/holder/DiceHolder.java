package pk.game.dice.holder;

import pk.game.dice.Dice;
import pk.game.score.scorable.Faces;

import java.util.Arrays;
import java.util.stream.Stream;

public class DiceHolder {

    // Class fields
    private final Dice[] dice;

    public DiceHolder() {
        this.dice = new Dice[Dice.MAX_DICE];
        for(int i=0; i < this.getDice().length; i++) {
            this.getDice()[i] = new Dice();
        }
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


    /**
     *
     * @return The {@link Faces} of the dice
     */
    public Faces[] getFaces() {
        return this.diceStream().map(Dice::getFace).toArray(Faces[]::new);
    }

    @Override
    public String toString() {
        return Arrays.toString(this.getDice());
    }
}
