package scoring;

import model.Property;
import model.UserPreference;

public class CommuteScore implements ScoringStrategy {

    @Override
    public double calculateScore(Property property, UserPreference userPreference) {

        if (userPreference.getMaxCommuteTime() == 0) {
            return property.getCommuteTime() == 0 ? 100.0 : 0.0;
        }
        double ratio = (double) property.getCommuteTime() / userPreference.getMaxCommuteTime();

        if (ratio <= 0.60)
            return 100.0;

        if (ratio <= 1.00) {
            return 100.0 - (ratio - 0.60) * 75.0;
        }

        if (ratio <= 1.50) {
            return 70.0 - (ratio - 1.0) * 140.0;
        }
        return 0.0;
    }
    @Override
    public String getCriterionName() {
        return "COMMUTE";
    }
}
