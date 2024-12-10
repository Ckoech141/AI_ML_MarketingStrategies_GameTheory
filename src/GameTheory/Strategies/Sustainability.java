package GameTheory.Strategies;

public class Sustainability extends Strategy {
    private static final int DEFECT_THRESHOLD = 3;

    public Sustainability(String strategyName) {
        super(strategyName);
    }

    @Override
    public boolean makeMove() {
        long recentDefects = getOpponentMoves().stream().filter(move -> !move).count();
        return recentDefects < DEFECT_THRESHOLD; // Cooperate unless too many defects
    }
}
