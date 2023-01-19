package pk.game.dice.holder;

import pk.game.dice.Dice;

import java.util.Arrays;

public class DiceHolder {

    // Class fields
    private final Dice[] dice;

    public DiceHolder() {
        this.dice = new Dice[Dice.MAX_DICE];
        Arrays.fill(this.dice, new Dice()); // Fill dice array with 8 dice
    }

}
