package pk.game.dice;
import java.util.Arrays;
import java.util.Random;

public class Dice {

    public Faces roll() {
        int howManyFaces = Faces.values().length;
        System.out.println("  (DEBUG) there are " + howManyFaces + " faces");
        System.out.println("  (DEBUG) " + Arrays.toString(Faces.values()));
        Random bag = new Random();
        return Faces.values()[bag.nextInt(howManyFaces)];
    }

    /**
     *
     * @param n The number of times to roll the dice
     * @return The result of rolling the dice n times
     */
    public Faces[] rollNTimes(int n) {
        Faces[] rollResult = new Faces[n];

        for(int i=0; i < n; i++) {
            rollResult[i] = this.roll();
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
