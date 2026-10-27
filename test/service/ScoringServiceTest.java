package test.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.Test;

import model.Property;
import model.UserPreference;
import scoring.*;
import service.ScoringService;

class ScoringServiceTest {

    @Test
    void shouldCalculateCorrectWeightedScore() {

        Map<ScoringStrategy, Double> weights = Map.of(
                new PriceScoringStrategy(), 0.25,
                new CommuteScore(), 0.30,
                new TransportScore(), 0.20,
                new SizeScore(), 0.15,
                new QuietAreaScoringStrategy(), 0.10);

        ScoringService service = new ScoringService(weights);

        Property property = new Property(
                1, "Kadıköy", 4_500_000,
                120, 3, 500, 35, true);

        UserPreference preference = new UserPreference(
                5_000_000, 40, 100, 3, true, true);

        double score = service.calculateTotalScore(
                property, preference);

        assertEquals(90.0625, score, 0.000001);
    }

    @Test
    void shouldRejectInvalidTotalWeight() {

        Map<ScoringStrategy, Double> weights = Map.of(
                new PriceScoringStrategy(), 0.50,
                new CommuteScore(), 0.30);

        assertThrows(
                IllegalArgumentException.class,
                () -> new ScoringService(weights));
    }

    @Test
    void shouldRejectNegativeWeight() {

        Map<ScoringStrategy, Double> weights = Map.of(
                new PriceScoringStrategy(), 1.10,
                new CommuteScore(), -0.10);

        assertThrows(
                IllegalArgumentException.class,
                () -> new ScoringService(weights));
    }

    @Test
    void shouldRejectEmptyStrategies() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new ScoringService(Map.of()));
    }
}