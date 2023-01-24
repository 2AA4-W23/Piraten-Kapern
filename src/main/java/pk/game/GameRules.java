package pk.game;

import pk.game.cards.Card;
import pk.game.cards.NopCard;
import pk.game.cards.SeaBattleCard;
import pk.game.dice.Dice;
import pk.game.player.Player;
import pk.game.score.scorable.Cards;
import pk.game.score.scorable.Faces;
import pk.game.score.scorable.Groups;
import pk.game.score.scorable.Scorable;
import pk.game.score.scorecard.scorecards.TurnScoreCard;

import java.util.Map;
import java.util.Set;

public class GameRules {

    public static final int MIN_DICE = 2; // Minimum number of dice a player can roll
    public static final int MAX_DICE = 8; // Maximum number of dice a player can roll
    public static final int WIN_SCORE = 6000; // The minimum score needed by a player to win
    public static final Map<Integer, Groups> groupMap = Map.of( // All the groups to score
            Groups.GROUP_OF_3.getGroupSize(), Groups.GROUP_OF_3,
            Groups.GROUP_OF_4.getGroupSize(), Groups.GROUP_OF_4,
            Groups.GROUP_OF_5.getGroupSize(), Groups.GROUP_OF_5,
            Groups.GROUP_OF_6.getGroupSize(), Groups.GROUP_OF_6,
            Groups.GROUP_OF_7.getGroupSize(), Groups.GROUP_OF_7,
            Groups.GROUP_OF_8.getGroupSize(), Groups.GROUP_OF_8
    );
    public static final Card[] GAME_CARDS = { // All the cards in the game
            new SeaBattleCard(Cards.SEA_BATTLE_300, 2),
            new SeaBattleCard(Cards.SEA_BATTLE_300, 2),
            new SeaBattleCard(Cards.SEA_BATTLE_500, 3),
            new SeaBattleCard(Cards.SEA_BATTLE_500, 3),
            new SeaBattleCard(Cards.SEA_BATTLE_1000, 4),
            new SeaBattleCard(Cards.SEA_BATTLE_1000, 4),
            new NopCard(), new NopCard(), new NopCard(), new NopCard(),
            new NopCard(), new NopCard(), new NopCard(), new NopCard(),
            new NopCard(), new NopCard(), new NopCard(),
            new NopCard(), new NopCard(), new NopCard(),
            new NopCard(), new NopCard(), new NopCard(),
            new NopCard(), new NopCard(), new NopCard(),
            new NopCard(), new NopCard(), new NopCard(),
            new NopCard(), new NopCard(), new NopCard(),
            new NopCard(), new NopCard(), new NopCard()
    };

    /**
     *
     * @param player The {@link Player} to check skulls count
     * @return A boolean determining whether the number of dice the {@link Player} rolled end their turn or not
     */
    public static boolean playerSkullsEndTurn(Player player) {
        return player.getTurnScoreCard().getScore(Faces.SKULL) >= 3;
    }

    /**
     * Rolls a {@link Player} dice for their first roll in a turn
     * @param player The {@link Player} performing their first roll
     */
    public static void firstRoll(Player player) {
        player.getDiceHolder().diceStream().forEach(Dice::roll);
    }

    /**
     *
     * @param player The {@link Player} to check if they reached the winning score
     * @return Whether the {@link Player} has reached the winning score or not
     */
    public static boolean didPlayerReachWinScore(Player player) {
        return player.getScoreCard().totalScore() >= GameRules.WIN_SCORE;
    }

    /**
     *
     * @param scoreCard The {@link TurnScoreCard} that the player uses each turn
     * @return Whether the current scorecard has a bonus chest or not
     */
    public static boolean isBonusChestRoll(TurnScoreCard scoreCard) {
        Set<Map.Entry<Scorable, Integer>> scoreEntries = scoreCard.getScoreEntrySet();

        // Get counts
        int goldCount = scoreCard.getScore(Faces.GOLD);
        int diamondCount = scoreCard.getScore(Faces.DIAMOND);
        int scoreContribDice = scoreEntries.stream().filter(e -> e.getKey() instanceof Groups)
                                                    .mapToInt(e -> ((Groups) e.getKey()).getGroupSize())
                                                    .sum();
        scoreContribDice += goldCount + diamondCount;

        // Cancel out any gold and diamond combinations
        scoreContribDice += goldCount >= Groups.GROUP_OF_3.getGroupSize() ? -goldCount : 0;
        scoreContribDice += diamondCount >= Groups.GROUP_OF_3.getGroupSize() ? -diamondCount : 0;


        return scoreContribDice == GameRules.MAX_DICE;
    }

}
