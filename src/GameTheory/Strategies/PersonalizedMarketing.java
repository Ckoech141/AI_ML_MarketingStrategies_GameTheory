package GameTheory.Strategies;

public class PersonalizedMarketing extends Strategy {

    public PersonalizedMarketing(String strategyName) {
        super(strategyName);
    }

    @Override
    public boolean makeMove() {
        if (getOpponentMoves().size() < 3) {
            return true; // Cooperate initially
        }

        // If the opponent defected in the last 3 rounds, defect
        long defects = getOpponentMoves().stream().filter(move -> !move).count();
        return defects < 2; // Cooperate if the opponent has defected less than twice in the last 3 moves
    }
}
