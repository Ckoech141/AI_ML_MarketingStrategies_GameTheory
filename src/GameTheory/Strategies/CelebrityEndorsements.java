package GameTheory.Strategies;

public class CelebrityEndorsements extends Strategy {
    public CelebrityEndorsements(String strategyName) {
        super(strategyName);
    }

    @Override
    public boolean makeMove() {
        return true; // Always cooperates
    }
}
