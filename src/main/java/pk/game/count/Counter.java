package pk.game.count;

public class Counter {
    private int count;

    public Counter() {
        this.count = 0;
    }

    /**
     *
     * @return The count so far
     */
    public int getCount() {
        return this.count;
    }

    /**
     *
     * @param count The new count value
     */
    public void setCount(int count) {
        this.count = count;
    }
}
