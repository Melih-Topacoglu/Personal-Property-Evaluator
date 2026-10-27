package test.scoring;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Property;
import model.UserPreference;
import scoring.CommuteScore;

class CommuteScoringStrategyTest {

        private final CommuteScore strategy = new CommuteScore();

        private Property createProperty(int commuteTime) {
                return new Property(
                                1, "Kadıköy", 4_500_000,
                                120, 3, 500, commuteTime, true);
        }

        private UserPreference createPreference(int maxCommuteTime) {
                return new UserPreference(
                                5_000_000, maxCommuteTime,
                                100, 3, true, true);
        }

        @Test
        void shouldReturn100ForShortCommute() {
                double score = strategy.calculateScore(
                                createProperty(20),
                                createPreference(40));

                assertEquals(100.0, score, 0.000001);
        }

        @Test
        void shouldReturn70AtMaximumCommute() {
                double score = strategy.calculateScore(
                                createProperty(40),
                                createPreference(40));

                assertEquals(70.0, score, 0.000001);
        }

        @Test
        void shouldReturnZeroForExcessiveCommute() {
                double score = strategy.calculateScore(
                                createProperty(60),
                                createPreference(40));

                assertEquals(0.0, score, 0.000001);
        }

        @Test
        void shouldHandleZeroMaximumCommute() {
                assertEquals(
                                100.0,
                                strategy.calculateScore(
                                                createProperty(0),
                                                createPreference(0)),
                                0.000001);

                assertEquals(
                                0.0,
                                strategy.calculateScore(
                                                createProperty(10),
                                                createPreference(0)),
                                0.000001);
        }
}