package scoring;

import model.Property;
import model.UserPreference;

public class PriceScoringStrategy implements ScoringStrategy {

    @Override
    public double calculateScore(
            Property property,
            UserPreference preference) {

        double ratio = property.getPrice()
                / preference.getMaxPrice();

        if (ratio <= 0.80) {
            return 100.0;
        }

        if (ratio <= 1.0) {
            return 100.0 - (ratio - 0.80) * 150.0;
        }

        if (ratio <= 1.50) {
            return 70.0 - (ratio - 1.0) * 140.0;
        }

        return 0.0;
    }

    @Override
    public String getCriterionName() {
        return "PRICE";
    }
}