package test.scoring;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Property;
import model.UserPreference;

class PriceScoringStrategyTest {

    private final PriceScoringStrategy strategy = new PriceScoringStrategy();

    private final UserPreference preference = new UserPreference(
            5_000_000, 40, 100, 3, true, true);

    private Property createProperty(double price) {
        return new Property(
                1, "Kadıköy", price,
                120, 3, 500, 35, true);
    }

    @Test
    void shouldReturn100WhenPriceBelow80Percent() {
        Property property = createProperty(3_000_000);

        double score = strategy.calculateScore(
                property, preference);

        assertEquals(100.0, score, 0.000001);
    }

    @Test
    void shouldReturn85WhenPriceIs90PercentOfBudget() {
        Property property = createProperty(4_500_000);

        double score = strategy.calculateScore(
                property, preference);

        assertEquals(85.0, score, 0.000001);
    }

    @Test
    void shouldReturn70AtMaximumBudget() {
        Property property = createProperty(5_000_000);

        double score = strategy.calculateScore(
                property, preference);

        assertEquals(70.0, score, 0.000001);
    }

    @Test
    void shouldReturnZeroAt150PercentOfBudget() {
        Property property = createProperty(7_500_000);

        double score = strategy.calculateScore(
                property, preference);

        assertEquals(0.0, score, 0.000001);
    }
}