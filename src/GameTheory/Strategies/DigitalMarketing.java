package GameTheory.Strategies;

public class DigitalMarketing extends Strategy {
    public DigitalMarketing(String strategyName) {
        super(strategyName);
    }

    @Override
    public boolean makeMove() {
        if (getOpponentMoves().isEmpty()) {
            return false; // Start by defecting
        }
        return getOpponentMoves().get(getOpponentMoves().size() - 1); // Mimic opponent's last move
    }
}
