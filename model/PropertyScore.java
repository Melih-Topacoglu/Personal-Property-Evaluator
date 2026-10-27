package model;

import java.util.Objects;

public class PropertyScore {

    private final Property property;
    private final double score;

    public PropertyScore(Property property, double score) {
        this.property = Objects.requireNonNull(
                property, "Property cannot be null");

        if (!Double.isFinite(score) || score < 0 || score > 100) {
            throw new IllegalArgumentException(
                    "Score must be between 0 and 100");
        }

        this.score = score;
    }

    public Property getProperty() {
        return property;
    }

    public double getScore() {
        return score;
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