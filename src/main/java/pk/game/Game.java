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
     * Play the games
     */
    public void play() {
        do {
            // Players play their turns
            this.p1.play();
            this.p2.play();

            // Get the scores of the players
            int p1Score = this.p1.getScoreCard().totalScore();
            int p2Score = this.p2.getScoreCard().totalScore();

            // Find who won
            if(p1Score >= ScoreCard.WIN_SCORE || p2Score >= ScoreCard.WIN_SCORE) {
                if(p1Score > p2Score) { // Did player p1 win?
                    this.p1.getWins().add(1);
                    this.p2.play();
                } else if(p1Score < p2Score) { // Did player p2 win?
                    this.p2.getWins().add(1);
                    this.p1.play();
                }
                break;
            }
        } while(true);
    }

}
