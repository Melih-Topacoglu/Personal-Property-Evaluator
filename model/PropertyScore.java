package model;

import java.util.Objects;
import java.util.List;

public class PropertyScore {

    private final Property property;
    private final double score;
    private final List<ScoreBreakdown> breakdowns;

    public PropertyScore(Property property, double score) {
        this(property, score, List.of());
    }

    public PropertyScore(Property property, double score, List<ScoreBreakdown> breakdowns) {

        this.property = Objects.requireNonNull(property, "Property cannot be null");

        if (!Double.isFinite(score) || score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }

        Objects.requireNonNull(breakdowns, "Breakdowns cannot be null");

        double breakdownsSum = breakdowns.stream()
        .mapToDouble(ScoreBreakdown::getWeightedScore)
        .sum();

        if(!breakdowns.isEmpty() && Math.abs(breakdownsSum - score) > 0.000001){
            throw new IllegalArgumentException("Breakdowns total must be equal to the score!");
        }
        this.score = score;
        this.breakdowns = List.copyOf(breakdowns);
    }

    public Property getProperty() {
        return property;
    }

    public double getScore() {
        return score;
    }

    public List<ScoreBreakdown> getBreakdowns() {
        return breakdowns;
    }

    @Override
    public String toString() {
        return String.format(
                "Property ID: %d | District: %s | Price: %.2f | Score: %.2f/100",
                property.getId(),
                property.getDistrict(),
                property.getPrice(),
                score);
    }
}