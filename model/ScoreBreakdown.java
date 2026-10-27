package model;

public class ScoreBreakdown {
    private final String criterion;
    private final double rawScore;
    private final double weight;
    private final double weightedScore;

    public ScoreBreakdown(String criterion, double rawScore, double weight){

        if(criterion == null || criterion.isBlank()){
            throw new IllegalArgumentException("Criteration can not be blank or null!");
        }

        if(!Double.isFinite(rawScore) || rawScore < 0 || rawScore > 100){
            throw new IllegalArgumentException("Raw score must be between 0-100!");
        }

        if(!Double.isFinite(weight) || weight < 0.0 || weight > 1.0){
            throw new IllegalArgumentException("Weight must be a finite value between 0-1!");
        }

        this.criterion = criterion;
        this.rawScore = rawScore;
        this.weight = weight;

        this.weightedScore = rawScore * weight;
    }
    
    public String getCriterion() {
        return criterion;
    }

    public double getRawScore() {
        return rawScore;
    }

    public double getWeight() {
        return weight;
    }

    public double getWeightedScore() {
        return weightedScore;
    }

    @Override
    public String toString() {
        return String.format(
            "%s | Raw Score: %.2f | Weight: %.0f%% | Contribution: %.2f",
            criterion,
            rawScore,
            weight * 100,
            weightedScore
        );
    }
}
