package GameTheory;

import GameTheory.Strategies.Strategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Game Class
 * Represents a two-player game based on the Prisoner's Dilemma framework.
 * Each round, two strategies compete by choosing to either "cooperate" or "defect."
 *
 * Reward Structure:
 * ----------------| Cooperate     | Defect
 * Cooperate       | 2, 2         | -1, 3
 * Defect          | 3, -1        | 0, 0
 */
public class Game {

    private final Strategy s1;
    private final Strategy s2;

    // Payoff constants
    private static final int BOTH_COOPERATE = 2;
    private static final int BOTH_DEFECT = 0;
    private static final int S1_DEFECT_S2_COOPERATE = 3;
    private static final int S1_COOPERATE_S2_DEFECT = -1;

    /**
     * Constructor to initialize a game between two strategies.
     *
     * @param s1 The first strategy
     * @param s2 The second strategy
     */
    public Game(Strategy s1, Strategy s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    /**
     * Executes the game between two strategies for a specified number of iterations.
     *
     * @param numIterations The number of iterations to simulate
     * @return A list containing the total scores of both strategies
     */
    public List<Integer> executeGame(int numIterations) {
        if (numIterations <= 0) {
            throw new IllegalArgumentException("Number of iterations must be greater than 0.");
        }

        // Play the game for the given number of iterations
        for (int i = 0; i < numIterations; i++) {
            playRound();
        }

        // Collect final scores
        List<Integer> scores = Arrays.asList(s1.getPoints(), s2.getPoints());

        // Reset the strategies for potential reuse
        s1.reset();
        s2.reset();

        return scores;
    }

    /**
     * Simulates a single round of the game between the two strategies.
     */
    private void playRound() {
        // Get moves from both strategies
        boolean s1Move = s1.makeMove();
        boolean s2Move = s2.makeMove();

        // Record opponent's move
        s1.recordOpponentMove(s2Move);
        s2.recordOpponentMove(s1Move);

        // Determine and apply payoffs
        if (s1Move && s2Move) {
            s1.updateScore(BOTH_COOPERATE);
            s2.updateScore(BOTH_COOPERATE);
        } else if (s1Move) {
            s1.updateScore(S1_COOPERATE_S2_DEFECT);
            s2.updateScore(S1_DEFECT_S2_COOPERATE);
        } else if (s2Move) {
            s1.updateScore(S1_DEFECT_S2_COOPERATE);
            s2.updateScore(S1_COOPERATE_S2_DEFECT);
        } else {
            s1.updateScore(BOTH_DEFECT);
            s2.updateScore(BOTH_DEFECT);
        }
    }
}
