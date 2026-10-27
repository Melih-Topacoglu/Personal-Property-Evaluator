package service;

import java.util.Map;
import java.util.Objects;

import model.Property;
import model.UserPreference;
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

    public double calculateTotalScore(
            Property property,
            UserPreference preference) {

        Objects.requireNonNull(property, "Property cannot be null");
        Objects.requireNonNull(preference, "UserPreference cannot be null");

        double totalScore = 0.0;

        for (Map.Entry<ScoringStrategy, Double> entry : strategies.entrySet()) {

            ScoringStrategy strategy = entry.getKey();
            double weight = entry.getValue();
            double strategyScore = strategy.calculateScore(property, preference);

            if (!Double.isFinite(strategyScore)
                    || strategyScore < 0.0
                    || strategyScore > 100.0) {

                throw new IllegalStateException(
                        "Strategy score must be between 0 and 100");
            }

            strategyScore *= weight;
            totalScore += strategyScore;
        }

        return totalScore;
    }
}