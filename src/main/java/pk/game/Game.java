package pk.game;

import pk.game.player.Player;

public class Game {

    // Players of the game
    private final Player p1;
    private final Player p2;

    public Game() {
        this.p1 = new Player();
        this.p2 = new Player();
    }
}
