package pk.game.dice;
import pk.game.score.scorable.Faces;

import java.util.Objects;
import java.util.Random;

public class Dice {

    public static final int MIN_DICE = 2; // Minimum number of dice a player can roll
    public static final int MAX_DICE = 8; // Maximum number of dice a player can roll

    private Faces face;

    public Dice() {
        this.face = null;
    }

    /**
     *
     * @return The {@link Faces} this dice is currently on
     */
    public Faces getFace() {
        if(Objects.isNull(this.face))
            throw new IllegalStateException("Dice must be rolled first!");

        return this.face;
    }

    /**
     *
     * @param face The {@link Faces} to set the face of this dice to
     */
    public void setFace(Faces face) {
        this.face = face;
    }

    /**
     * Roll this {@link Dice}
     */
    public void roll() {
        int howManyFaces = Faces.values().length;
        Random bag = new Random();
        this.setFace(Faces.values()[bag.nextInt(howManyFaces)]);
    }

    /**
     *
     * @param n The number of times to roll the dice
     * @return The result of rolling the dice n times
     */
    public Faces[] rollNTimes(int n) {
        Faces[] rollResult = new Faces[n];

        for(int i=0; i < n; i++) {
            this.roll();
            rollResult[i] = this.getFace();
        }

        return rollResult;
    }

    /**
     *
     * @param min The minimum number of times to roll the dice
     * @param max The maximum number of times to roll the dice
     * @return The result of rolling the dice a random number of times between [min, max]
     */
    public Faces[] rollRandTimes(int min, int max) {
        Random rand = new Random();
        int numRolls = rand.nextInt(min, max+1);

        return this.rollNTimes(numRolls);
    }
    
}
