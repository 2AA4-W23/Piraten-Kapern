package pk.game.score.scorecard.scorecards;

import pk.game.score.scorable.Scorable;
import pk.game.score.scorecard.AbstractScoreCard;

import java.util.Map;
import java.util.Set;

public class TurnScoreCard extends AbstractScoreCard {
    @Override
    public void addScore(Scorable face, int count) {
        // If there is already a record of this Face just overwrite it
        super.getScoreCount().compute(face, (k, v) -> count);
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
