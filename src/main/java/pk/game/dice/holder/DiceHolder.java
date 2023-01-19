package pk.game.dice.holder;

import pk.game.dice.Dice;
import pk.game.score.scorable.Faces;

public class DiceHolder {

    // Class fields
    private final Dice dice;
    private final Faces[] faces;

    public DiceHolder() {
        this.dice = new Dice();
        this.faces = new Faces[Dice.MAX_DICE];
    }

}
