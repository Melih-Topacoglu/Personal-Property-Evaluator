package scoring;

import model.Property;
import model.UserPreference;

public class SizeScore implements ScoringStrategy {

    @Override
    public double calculateScore(Property property, UserPreference userPreference) {
        if (userPreference.getMinArea() == 0.0) {
            return 100.0;
        }
        double ratio = property.getArea() / userPreference.getMinArea();

        if (ratio < 0.50)
            return 0.0;

        if (ratio <= 1.00) {
            return 0.0 + (ratio - 0.50) * 140.0;
        }

        if (ratio <= 1.20) {
            return 70.0 + (ratio - 1.00) * 150.0;
        }
        return 100.0;
    }

    @Override
    public String getCriterionName() {
        return "SIZE";
    }
}
