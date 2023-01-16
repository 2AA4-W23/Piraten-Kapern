package pk.game.score;

import pk.game.dice.Faces;

import java.util.HashMap;

public class ScoreCard {

    // HashMap to keep count of the
    private final HashMap<Faces, Integer> scoreCount;

    public ScoreCard() {
        this.scoreCount = new HashMap<>();
    }
}
