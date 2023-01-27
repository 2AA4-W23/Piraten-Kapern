package pk.game.cards;

import pk.game.GameRules;
import pk.game.player.Player;
import pk.game.score.scorable.Faces;
import pk.game.score.scorable.Groups;
import pk.game.strategy.player.PlayerStrategy;
import pk.game.strategy.player.strategies.cards.MonkeyBusinessStrategy;

import java.util.Objects;

public class MonkeyBusinessCard extends AbstractCard {

    public static final String NAME = "Monkey Business";

    private final MonkeyBusinessStrategy strategy;

    public MonkeyBusinessCard() {
        super(MonkeyBusinessCard.NAME);
        this.strategy = new MonkeyBusinessStrategy(this);
    }

    @Override
    public PlayerStrategy getStrategy() {
        return this.strategy;
    }

    @Override
    public void use(Player player) {
        super.use(player);

        if(player.isTurnOver()) {
            // Calculate the number of monkey and parrots rolled
            Integer monkeyCount = player.getTurnScoreCard().getScore(Faces.MONKEY);
            Integer parrotCount = player.getTurnScoreCard().getScore(Faces.PARROT);
            Integer monkeyParrotSum = monkeyCount + parrotCount;

            // Get the combination of the monkey and parrots
            Groups monkeyParrotCombo = GameRules.GROUP_MAP.get(monkeyParrotSum);

            if (Objects.nonNull(monkeyParrotCombo)) { // Does the player have enough monkeys and parrots to make a combination?
                // Monkey combo and parrot combo
                Groups monkeyCombo = GameRules.GROUP_MAP.get(monkeyCount);
                Groups parrotCombo = GameRules.GROUP_MAP.get(parrotCount);

                if (Objects.nonNull(monkeyCombo)) // Does the player have a combo with monkeys?
                    player.getTurnScoreCard().removeScore(monkeyCombo, 1);

                if (Objects.nonNull(parrotCombo)) // Does the player have a combo with parrots?
                    player.getTurnScoreCard().removeScore(parrotCombo, 1);

                // Add combo of monkeys and parrots combined
                player.getTurnScoreCard().addScore(monkeyParrotCombo, 1);
            }
        }
    }
}
