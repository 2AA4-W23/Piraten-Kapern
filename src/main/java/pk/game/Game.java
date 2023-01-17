package pk.game;

import pk.game.player.Player;
import pk.game.score.ScoreCard;

public class Game {

    // Players of the game
    private final Player p1;
    private final Player p2;

    public Game() {
        this.p1 = new Player();
        this.p2 = new Player();
    }

    /**
     *
     * @return The first player in this game
     */
    public Player getPlayer1() {
        return this.p1;
    }

    /**
     *
     * @return The second player in this game
     */
    public Player getPlayer2() {
        return this.p2;
    }

    /**
     * Reset the game so that it can be replayed by the players
     */
    public void reset() {
        this.getPlayer1().resetGame();
        this.getPlayer2().resetGame();
    }

    /**
     * Play the games
     */
    public void play() {
        do {
            // Players play their turns
            this.getPlayer1().play();
            this.getPlayer2().play();

            // Get the scores of the players
            int p1Score = this.p1.getScoreCard().totalScore();
            int p2Score = this.p2.getScoreCard().totalScore();

            // Find who won
            if(p1Score >= ScoreCard.WIN_SCORE || p2Score >= ScoreCard.WIN_SCORE) {
                if(p1Score > p2Score) { // Did player p1 win?
                    this.getPlayer1().getWins().add(1);
                    this.getPlayer2().play();
                } else if(p1Score < p2Score) { // Did player p2 win?
                    this.getPlayer2().getWins().add(1);
                    this.getPlayer1().play();
                }
                break;
            }
        } while(true);
    }

}
