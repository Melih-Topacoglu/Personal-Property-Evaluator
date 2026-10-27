import java.util.Map;

import model.Property;
import model.PropertyScore;
import model.UserPreference;

import scoring.ScoringStrategy;
import scoring.PriceScoringStrategy;
import scoring.CommuteScore;
import scoring.SizeScore;
import scoring.TransportScore;
import scoring.QuietAreaScoringStrategy;

import service.ScoringService;
import service.PropertyEvaluationService;

public class Main {

        public static void main(String[] args) {

                // 1. Configure scoring strategies and weights
                Map<ScoringStrategy, Double> weights = Map.of(
                                new PriceScoringStrategy(), 0.25,
                                new CommuteScore(), 0.30,
                                new TransportScore(), 0.20,
                                new SizeScore(), 0.15,
                                new QuietAreaScoringStrategy(), 0.10);

                // 2. Initialize services
                ScoringService scoringService = new ScoringService(weights);

                PropertyEvaluationService evaluationService = new PropertyEvaluationService(scoringService);

                // 3. Create a property
                Property property = new Property(
                                1,
                                "Kadıköy",
                                4_500_000,
                                120,
                                3,
                                500,
                                35,
                                true);

                // 4. Create user preferences
                UserPreference preference = new UserPreference(
                                5_000_000,
                                40,
                                100,
                                3,
                                true,
                                true);

                // 5. Evaluate the property
                PropertyScore result = evaluationService.evaluateProperty(
                                property,
                                preference);

                // 6. Display results
                System.out.println("========== PROPERTY EVALUATION ==========");

                System.out.println("\n--- PROPERTY INFORMATION ---");
                System.out.println(property);

                System.out.println("\n--- USER PREFERENCES ---");
                System.out.println(preference);

                System.out.println("\n--- EVALUATION RESULT ---");
                System.out.println(result);

                // 7. Verify expected total score
                double expectedScore = 90.0625;
                double actualScore = result.getScore();

                if (Math.abs(expectedScore - actualScore) < 0.000001) {
                        System.out.println("\nTEST PASSED");
                } else {
                        System.out.println("\nTEST FAILED");
                        System.out.printf(
                                        "Expected: %.4f | Actual: %.4f%n",
                                        expectedScore,
                                        actualScore);
                }
        }
}