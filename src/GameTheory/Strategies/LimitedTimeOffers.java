package GameTheory.Strategies;

public class LimitedTimeOffers extends Strategy {
    private static final int DEFECT_INTERVAL = 3;

    public LimitedTimeOffers(String strategyName) {
        super(strategyName);
    }

    @Override
    public boolean makeMove() {
        return (getOpponentMoves().size() + 1) % DEFECT_INTERVAL != 0; // Defect every DEFECT_INTERVAL rounds
    }
}
