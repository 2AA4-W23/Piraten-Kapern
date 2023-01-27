package pk.game.score.scorecard.scorecards;

import pk.game.GameRules;
import pk.game.score.scorable.Bonus;
import pk.game.score.scorable.Groups;
import pk.game.score.scorable.Scorable;
import pk.game.score.scorecard.AbstractScoreCard;

import java.util.Map;
import java.util.Objects;

public class TurnScoreCard extends AbstractScoreCard {

    private Scorable reserve;

    public TurnScoreCard() {
        this.reserve = null;
    }

    /**
     *
     * @return {@link TurnScoreCard#reserve}. This is a {@link Scorable} instance that will be added to the player roll
     */
    public Scorable getReserve() {
        return this.reserve;
    }

    /**
     *
     * @param reserve The {@link Scorable} to set {@link TurnScoreCard#reserve} to
     */
    public void putReserve(Scorable reserve) {
        this.reserve = reserve;
    }

    /**
     *
     * @param map The map of the scorables to add and their counts
     */
    public void addAll(Map<? extends Scorable, Integer> map) {
        super.clear(); // Clear old scores from last roll

        super.getScoreCount().putAll(map); // Add new scores

        if(Objects.nonNull(this.getReserve())) // Does the player have a reserved scorable this turn?
            super.addScore(this.getReserve(), 1);

        this.addCombos();

        if(GameRules.isBonusChestRoll(this)) { // Is there a bonus chest in the scorecard?
            this.addScore(Bonus.CHEST, 1);
        }
    }

    /**
     * Check for combinations and add them to scorecard
     */
    private void addCombos() {
        Map<Scorable, Integer> map = Map.copyOf(super.getScoreCount());

        // Look for combinations
        map.forEach((k, v) -> {
            Groups group = GameRules.GROUP_MAP.get(v);
            if(Objects.nonNull(group)) {
                this.addScore(group, 1);
            }
        });
    }

    @Override
    public void clear() {
        super.clear();
        this.putReserve(null);
    }
}
