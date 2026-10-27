package scoring;

import model.Property;
import model.UserPreference;

public class QuietAreaScoringStrategy implements ScoringStrategy {

    @Override
    public double calculateScore(
            Property property,
            UserPreference userPreference) {

        if (!userPreference.isWantsQuietArea()) {
            return 100.0;
        }

        return property.isQuietArea() ? 100.0 : 0.0;
    }

    @Override
    public String getCriterionName() {
        return "QUIET_AREA";
    }
}
