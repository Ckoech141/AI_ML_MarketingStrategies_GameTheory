package GameTheory;

import GameTheory.Strategies.Strategy;
import java.util.*;

/**
 * Tournament Class
 * Simulates a multi-round tournament among a list of strategies.
 */
public class Tournament {

    private final List<Strategy> strategies;

    /**
     * Constructor initializes the tournament with the provided strategies.
     *
     * @param strategies List of strategies participating in the tournament
     */
    public Tournament(List<Strategy> strategies) {
        this.strategies = strategies;
    }

    /**
     * Executes multiple rounds of the tournament, where each strategy competes against every other.
     *
     * @param rounds Number of games each strategy pair plays per matchup
     * @return A map of each strategy to the total points it scored
     */
    public Map<Strategy, Integer> executeTournamentRounds(int rounds) {
        if (rounds <= 0) {
            throw new IllegalArgumentException("Number of rounds must be greater than 0.");
        }

        // Initialize scores for each strategy
        Map<Strategy, Integer> scores = new HashMap<>();
        strategies.forEach(strategy -> scores.put(strategy, 0));

        // Pairwise competition
        for (int i = 0; i < strategies.size(); i++) {
            for (int j = i + 1; j < strategies.size(); j++) {
                Strategy s1 = strategies.get(i);
                Strategy s2 = strategies.get(j);

                // Play the specified number of rounds between two strategies
                playRounds(s1, s2, rounds, scores);
            }
        }

        return scores;
    }

    /**
     * Plays multiple rounds between two strategies and updates their scores.
     *
     * @param s1      First strategy
     * @param s2      Second strategy
     * @param rounds  Number of rounds to play
     * @param scores  Map to track cumulative scores of all strategies
     */
    private void playRounds(Strategy s1, Strategy s2, int rounds, Map<Strategy, Integer> scores) {
        for (int round = 0; round < rounds; round++) {
            // Both strategies decide their moves
            boolean s1Move = s1.makeMove();
            boolean s2Move = s2.makeMove();

            // Calculate payoffs for this round
            int s1Payoff = getPayoff(s1Move, s2Move);
            int s2Payoff = getPayoff(s2Move, s1Move);

            // Update each strategy's individual points
            s1.addPoints(s1Payoff);
            s2.addPoints(s2Payoff);

            // Record opponent moves
            s1.addOpponentMove(s2Move);
            s2.addOpponentMove(s1Move);
        }

        // Add cumulative points from this matchup to the total scores
        scores.put(s1, scores.get(s1) + s1.getTotalPoints());
        scores.put(s2, scores.get(s2) + s2.getTotalPoints());

        // Reset strategies for the next matchup
        s1.reset();
        s2.reset();
    }

    /**
     * Determines the payoff for a strategy based on the moves of both players.
     *
     * @param move1 Move of the first strategy (true for cooperate, false for defect)
     * @param move2 Move of the second strategy (true for cooperate, false for defect)
     * @return Payoff for the first strategy
     */
    private int getPayoff(boolean move1, boolean move2) {
        if (move1 && move2) return 3;  // Both cooperate
        if (move1) return 0;          // First cooperates, second defects
        if (move2) return 5;          // First defects, second cooperates
        return 1;                     // Both defect
    }
}
