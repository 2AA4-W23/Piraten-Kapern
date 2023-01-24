package pk.game.score.scorecard.scorecards;

import pk.game.GameRules;
import pk.game.score.scorable.Bonus;
import pk.game.score.scorable.Groups;
import pk.game.score.scorable.Scorable;
import pk.game.score.scorecard.AbstractScoreCard;

import java.util.Map;
import java.util.Objects;

public class TurnScoreCard extends AbstractScoreCard {
    /**
     *
     * @param map The map of the scorables to add and their counts
     */
    public void addAll(Map<? extends Scorable, Integer> map) {
        this.clear(); // Clear old scores from last roll

        // Look for combinations
        map.forEach((k, v) -> {
            Groups group = GameRules.GROUP_MAP.get(v);
            if(Objects.nonNull(group)) {
                this.addScore(group, 1);
            }
        });
        super.getScoreCount().putAll(map); // Add new scores

        if(GameRules.isBonusChestRoll(this)) { // Is there a bonus chest in the scorecard?
            this.addScore(Bonus.CHEST, 1);
        }
    }
}
