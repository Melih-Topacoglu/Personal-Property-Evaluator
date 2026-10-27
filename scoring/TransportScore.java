package scoring;

import model.Property;
import model.UserPreference;

public class TransportScore implements ScoringStrategy {
    @Override
    public double calculateScore(Property property, UserPreference userPreference) {

        if (!userPreference.isWantsPublicTransport()) {
            return 100.0;
        }
        double distance = property.getDistanceToMetro();
        if (distance <= 500) {
            return 100.0;
        }
        if (distance <= 1000) {
            return 100.0 - (distance - 500) * 0.06;
        }
        if (distance <= 2000) {
            return 70.0 - (distance - 1000) * 0.07;
        }
        return 0.0;
    }

    @Override
    public String getCriterionName() {
        return "TRANSPORTATION";
    }
}
