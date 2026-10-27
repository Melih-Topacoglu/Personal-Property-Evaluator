package test.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.Test;

import model.Property;
import model.PropertyScore;
import model.UserPreference;
import scoring.*;
import service.ScoringService;
import service.PropertyEvaluationService;

class PropertyEvaluationServiceTest {

    @Test
    void shouldEvaluateSingleProperty() {

        Map<ScoringStrategy, Double> weights = Map.of(
                new PriceScoringStrategy(), 0.25,
                new CommuteScore(), 0.30,
                new TransportScore(), 0.20,
                new SizeScore(), 0.15,
                new QuietAreaScoringStrategy(), 0.10);

        ScoringService scoringService = new ScoringService(weights);

        PropertyEvaluationService evaluationService = new PropertyEvaluationService(scoringService);

        Property property = new Property(
                1, "Kadıköy", 4_500_000,
                120, 3, 500, 35, true);

        UserPreference preference = new UserPreference(
                5_000_000, 40, 100, 3, true, true);

        PropertyScore result = evaluationService.evaluateProperty(property, preference);

        assertNotNull(result);
        assertSame(property, result.getProperty());
        assertEquals(90.0625, result.getScore(), 0.000001);
    }
}