package pk.game.score.scorable;

public enum Cards implements Scorable {
    SEA_BATTLE_300(300),
    SEA_BATTLE_500(500),
    SEA_BATTLE_1000(1000);

    final int score;

    Cards(int score) {
        this.score = score;
    }

    @Override
    public int getScore() {
        return this.score;
    }
}
