package pk.game;

import pk.game.cards.deck.CardDeck;
import pk.game.player.Player;
import pk.game.strategy.player.PlayerStrategy;

import java.util.Arrays;
import java.util.stream.Stream;

public class Game {

    // Players of the game
    private final Player[] players;
    private final CardDeck deck;

    public Game(PlayerStrategy... strategies) {
        this.players = new Player[strategies.length];
        this.deck = new CardDeck(GameRules.GAME_CARDS);
        for(int i=0; i < strategies.length; i++) {
            this.players[i] = new Player(strategies[i]);
        }
    }

    /**
     *
     * @return The first {@link Player} in this game
     */
    public Player[] getPlayers() {
        return this.players;
    }

    /**
     *
     * @return A {@link Stream} object with all the {@link Player}s in the game
     */
    public Stream<Player> playerStream() {
        return Arrays.stream(this.getPlayers());
    }

    /**
     * Reset the game so that it can be replayed by the {@link Player}s
     */
    public void reset() {
        this.playerStream().forEach(Player::resetGame);
        this.deck.reset();
    }

    /**
     * Play the games
     */
    public void play() {
        boolean shouldEndGame = false;

        // Play turns
        do {
            for(Player player : this.getPlayers()) {
                player.play(this.deck.draw()); // Make player play their turn

                if(GameRules.didPlayerReachWinScore(player)) { // Did the player reach the winning score?
                    this.playerStream().filter(p -> p != player).forEach(p -> p.play(this.deck.draw())); // Give all other players 1 more turn
                    shouldEndGame = true;
                    break;
                }
            }
        } while(!shouldEndGame);

        // Add a win to the player with the maximum score
        int maxScore = this.playerStream().mapToInt(p -> p.getScoreCard().totalScore()).max().getAsInt(); // Max score

        long numPlayerWinScore = this.playerStream().filter(p -> p.getScoreCard().totalScore() == maxScore).count();
        if(numPlayerWinScore == 1){ // Allow only 1 winner
            // Add wins to the winning player
            this.playerStream()
                .filter(p -> p.getScoreCard().totalScore() == maxScore)
                .findFirst().ifPresent(p -> p.getWins().add(1));
        }
    }

}
