package pk.game.score.scorable;

public enum Bonus implements Scorable {
    CHEST(500);

    final int score;

    Bonus(int score) {
        this.score = score;
    }

    @Override
    public int getScore() {
        return this.score;
    }
}
