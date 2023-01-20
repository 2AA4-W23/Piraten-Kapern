package pk.game;

import pk.game.player.Player;
import pk.game.score.scorecard.scorecards.GameScoreCard;

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
     * @return The first {@link Player} in this game
     */
    public Player getPlayer1() {
        return this.p1;
    }

    /**
     *
     * @return The second {@link Player} in this game
     */
    public Player getPlayer2() {
        return this.p2;
    }

    /**
     * Reset the game so that it can be replayed by the {@link Player}s
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
            if(this.getPlayer1().getScoreCard().totalScore() >= GameRules.WIN_SCORE) { // Player 1 reached 6k points?
                // Give player 2 an extra turn
                this.getPlayer2().play();
                break;
            }

            this.getPlayer2().play();
            if(this.getPlayer2().getScoreCard().totalScore() >= GameRules.WIN_SCORE) { // Player 2 reached 6k points?
                // Give player 1 an extra turn
                this.getPlayer1().play();
                break;
            }
        } while(true);

        // Get the scores of the players
        int p1Score = this.getPlayer1().getScoreCard().totalScore();
        int p2Score = this.getPlayer2().getScoreCard().totalScore();

        if(p1Score > p2Score) { // Did player p1 win?
            this.getPlayer1().getWins().add(1);
        } else if(p1Score < p2Score) { // Did player p2 win?
            this.getPlayer2().getWins().add(1);
        }
    }

}
