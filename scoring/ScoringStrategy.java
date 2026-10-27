package scoring;

import model.Property;
import model.UserPreference;

public interface ScoringStrategy {

    double calculateScore(
            Property property,
            UserPreference preference);
}