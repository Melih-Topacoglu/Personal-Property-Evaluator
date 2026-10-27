package service;

import java.util.Map;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

import model.Property;
import model.UserPreference;
import model.ScoreBreakdown;
import scoring.ScoringStrategy;

public class ScoringService {

    private final Map<ScoringStrategy, Double> strategies;

    public ScoringService(Map<ScoringStrategy, Double> strategies) {
        Objects.requireNonNull(strategies, "Strategies cannot be null");

        if (strategies.isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one scoring strategy is required");
        }

        for (double weight : strategies.values()) {
            if (!Double.isFinite(weight) || weight < 0.0 || weight > 1.0) {
                throw new IllegalArgumentException(
                        "Each weight must be between 0 and 1");
            }
        }
        double totalWeight = strategies.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        if (Math.abs(totalWeight - 1.0) > 0.000001) {
            throw new IllegalArgumentException("Total weight must equal 1.0");
        }

        this.strategies = Map.copyOf(strategies);
    }

    public double calculateTotalScore(Property property, UserPreference preference) {

        return calculateScoreBreakdown(property, preference)
                .stream()
                .mapToDouble(ScoreBreakdown::getWeightedScore)
                .sum();
        }

    public List<ScoreBreakdown> calculateScoreBreakdown(Property property, UserPreference preference) {

        Objects.requireNonNull(property, "Property cannot be null");
        Objects.requireNonNull(preference, "UserPreference cannot be null");

        List<ScoreBreakdown> breakdowns = new ArrayList<>();

        for (Map.Entry<ScoringStrategy, Double> entry : strategies.entrySet()) {

            ScoringStrategy strategy = entry.getKey();
            double weight = entry.getValue();

        // TODO: Get criterion name
            String criterionName = strategy.getCriterionName();
        // TODO: Calculate raw score
            double rawScore = strategy.calculateScore(property,preference);
        // TODO: Create ScoreBreakdown object
            ScoreBreakdown scoreBreakdown = new ScoreBreakdown(criterionName,rawScore,weight);
        // TODO: Add ScoreBreakdown to the list

            breakdowns.add(scoreBreakdown);
        }

        return List.copyOf(breakdowns);
    }
}