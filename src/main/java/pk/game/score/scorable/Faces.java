package pk.game.score.scorable;

public enum Faces implements Scorable {
    MONKEY(0),
    PARROT(0),
    GOLD(100),
    DIAMOND(100),
    SABER(0),
    SKULL(0);

    final int score;

    Faces(int score) {
        this.score = score;
    }

    /**
     *
     * @return The score associated with this {@link Faces}
     */
    @Override
    public int getScore() {
        return this.score;
    }
}

