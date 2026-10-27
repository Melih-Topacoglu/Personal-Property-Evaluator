package service;

import java.util.Objects;

import model.Property;
import model.PropertyScore;
import model.UserPreference;

public class PropertyEvaluationService {

    private final ScoringService scoringService;

    public PropertyEvaluationService(ScoringService scoringService) {
        this.scoringService = Objects.requireNonNull(
                scoringService, "ScoringService cannot be null");
    }

    public PropertyScore evaluateProperty(
            Property property,
            UserPreference preference) {

        double totalScore = scoringService.calculateTotalScore(property, preference);
        PropertyScore propertyScore = new PropertyScore(property, totalScore);
        return propertyScore;
    }
}