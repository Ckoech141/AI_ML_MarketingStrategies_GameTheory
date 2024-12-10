package GameTheory.Strategies;

public class GeneticOneMove extends GeneticStrategy {

    public GeneticOneMove(String companyName, double weight) {
        super(companyName, weight);
    }

    @Override
    public boolean makeMove() {
        // Example logic: decision based on weight
        return Math.random() < this.weight;
    }

    @Override
    public GeneticStrategy mutate() {
        // Example mutation logic: slightly adjust weight
        double newWeight = this.weight + (Math.random() - 0.5) * 0.1;
        newWeight = Math.max(0, Math.min(1, newWeight)); // Ensure weight stays in [0, 1]
        return new GeneticOneMove(this.companyName, newWeight);
    }
}
