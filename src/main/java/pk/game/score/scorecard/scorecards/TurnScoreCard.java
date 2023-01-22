package pk.game.score.scorecard.scorecards;

import pk.game.GameRules;
import pk.game.score.scorable.Bonus;
import pk.game.score.scorable.Groups;
import pk.game.score.scorable.Scorable;
import pk.game.score.scorecard.AbstractScoreCard;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class TurnScoreCard extends AbstractScoreCard {
    @Override
    public void addScore(Scorable face, int count) {
        // If there is already a record of this Face just overwrite it
        super.getScoreCount().put(face, count);
    }

    /**
     *
     * @param map The map of the scorables to add and their counts
     */
    public void addAll(Map<? extends Scorable, Integer> map) {
        this.clear(); // Clear old scores from last roll

        // Look for combinations
        map.forEach((k, v) -> {
            Groups group = GameRules.groupMap.get(v);
            if(Objects.nonNull(group)) {
                this.addScore(group, 1);
            }
        });
        super.getScoreCount().putAll(map); // Add new scores

        if(GameRules.isBonusChestRoll(this)) { // Is there a bonus chest in the scorecard?
            this.addScore(Bonus.CHEST, 1);
        }
    }

    @Override
    public void clear() {
        super.getScoreCount().clear();
    }

    @Override
    public int totalScore() {
        Set<Map.Entry<Scorable, Integer>> entrySet = super.getScoreCount().entrySet();

        // Sum up the score
        return entrySet.stream().mapToInt(e -> e.getKey().getScore()*e.getValue()).sum();
    }
}
