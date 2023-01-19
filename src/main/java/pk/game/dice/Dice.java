package pk.game.dice;
import pk.game.Util;
import pk.game.score.scorable.Faces;

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
        this.setFace(Faces.values()[Util.RANDOM.nextInt(howManyFaces)]);
    }

    /**
     * Reset the {@link Dice} so that no {@link Faces} are selected
     */
    public void reset() {
        this.setFace(null);
    }

    @Override
    public String toString() {
        return "Dice{" +
                "face=" + this.getFace() +
                '}';
    }
}
