package pk.game.score.scorable;

public enum Groups implements Scorable {
    GROUP_OF_3(100, 3),
    GROUP_OF_4(200, 4),
    GROUP_OF_5(500, 5),
    GROUP_OF_6(1000, 6),
    GROUP_OF_7(2000, 7),
    GROUP_OF_8(4000, 8);

    final int score;
    final int groupSize;

    Groups(int score, int groupSize) {
        this.score = score;
        this.groupSize = groupSize;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    /**
     *
     * @return The size of this group
     */
    public int getGroupSize() {
        return this.groupSize;
    }
}
