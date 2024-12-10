package GameTheory.Strategies;

import java.util.List;

public class BrandLoyalty extends Strategy {

    public BrandLoyalty(String strategyName) {
        super(strategyName);
    }

    @Override
    public boolean makeMove() {
        if (getOpponentMoves().size() < 2) {
            return true; // Cooperate initially
        }

        // If the opponent has defected in the last two moves, defect
        long defects = getOpponentMoves().stream().filter(move -> !move).count();
        return defects < 1; // Cooperate unless the opponent defected once in the last two moves
    }

    /**
     * Returns the opponent's move history.
     * @return List<Boolean> representing the opponent's moves
     */
    @Override
    public List<Boolean> getOpponentMoves() {
        return super.getOpponentMoves(); // Call the superclass implementation to return the move history
    }
}
