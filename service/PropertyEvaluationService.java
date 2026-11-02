package service;

import java.util.Objects;
import java.util.List;

import model.ScoreBreakdown;
import model.Property;
import model.PropertyScore;
import model.UserPreference;

public class PropertyEvaluationService {

    private final ScoringService scoringService;

    public PropertyEvaluationService(ScoringService scoringService) {
        this.scoringService = Objects.requireNonNull(
                scoringService, "ScoringService cannot be null");
    }

    public PropertyScore evaluateProperty(Property property,UserPreference preference) {

        Objects.requireNonNull(property, "Property cannot be null");
        Objects.requireNonNull(preference, "UserPreference cannot be null");

        List<ScoreBreakdown> breakdowns = scoringService.calculateScoreBreakdown(property, preference);

        double totalScore = breakdowns.stream()
            .mapToDouble(ScoreBreakdown::getWeightedScore)
            .sum();

        return new PropertyScore(property, totalScore, breakdowns);
    }
}