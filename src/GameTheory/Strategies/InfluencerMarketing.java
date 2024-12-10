package GameTheory.Strategies;

public class InfluencerMarketing extends Strategy {
    private boolean lastMove = true; // Start with cooperation

    public InfluencerMarketing(String strategyName) {
        super(strategyName);
    }

    @Override
    public boolean makeMove() {
        lastMove = !lastMove; // Alternate moves
        return lastMove;
    }
}
