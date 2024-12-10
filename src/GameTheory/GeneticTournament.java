package GameTheory;

import GameTheory.Strategies.GeneticStrategy;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.*;

/**
 * GeneticTournament Class
 * Simulates a genetic algorithm-based tournament among strategies.
 * Poor performers are removed, and successful strategies mutate to create new ones.
 */
public class GeneticTournament {

    private HashMap<GeneticStrategy, Integer> points;

    /**
     * Constructor initializes the tournament with the provided strategies.
     *
     * @param strategies List of initial strategies
     */
    public GeneticTournament(List<GeneticStrategy> strategies) {
        this.points = new HashMap<>();
        strategies.forEach(s -> this.points.put(s, 0));
    }

    /**
     * Executes a genetic tournament over multiple rounds.
     * Each round includes competition, elimination of poor performers,
     * and the creation of new strategies via mutation.
     *
     * @param numRounds Number of tournament rounds
     * @return Map of strategies to scores achieved in the final round
     * @throws IOException If an error occurs during file operations
     */
    public HashMap<GeneticStrategy, Integer> executeGeneticTournamentRounds(int numRounds) throws IOException {
        HashMap<GeneticStrategy, Integer> finalScores = new HashMap<>();
        try (PrintWriter writer = new PrintWriter(new FileWriter("geneticRes.txt"))) {

            for (int round = 0; round < numRounds; round++) {
                System.out.println("Round " + round);

                // Run a single tournament round
                HashMap<GeneticStrategy, Integer> roundResults = tournamentRound(100);
                addNewPoints(roundResults);

                // Sort strategies based on performance
                List<Map.Entry<GeneticStrategy, Integer>> sortedEntries = sortEntries(this.points.entrySet());

                // Write initial state to file for round 0
                if (round == 0) {
                    this.points.forEach((strategy, score) -> writer.printf("%f %d%n", strategy.getWeight(), score));
                    writer.println("-------");
                }

                // Eliminate the bottom 50%
                List<Map.Entry<GeneticStrategy, Integer>> eliminated = sortedEntries.subList(sortedEntries.size() / 2, sortedEntries.size());
                eliminated.forEach(entry -> this.points.remove(entry.getKey()));

                // Write current state after elimination
                this.points.forEach((strategy, score) -> writer.printf("%f %d%n", strategy.getWeight(), score));

                // Save scores at the final round
                if (round == numRounds - 1) {
                    finalScores = new HashMap<>(this.points);
                }

                // Mutate surviving strategies and create new ones
                HashMap<GeneticStrategy, Integer> newStrategies = new HashMap<>();
                for (GeneticStrategy strategy : this.points.keySet()) {
                    GeneticStrategy newStrategy = strategy.mutateNew();
                    newStrategies.put(newStrategy, 0);
                }

                // Reset points and apply minor mutations
                this.points.replaceAll((strategy, score) -> {
                    strategy.mutate();
                    return 0;
                });

                // Add new strategies to the population
                this.points.putAll(newStrategies);
                writer.println("-------");
            }
        }

        return finalScores;
    }

    /**
     * Runs one round of the tournament, where each strategy competes against every other.
     *
     * @param numGames Number of games each strategy pair plays
     * @return Map of strategies to points earned during the round
     */
    private HashMap<GeneticStrategy, Integer> tournamentRound(int numGames) {
        HashMap<GeneticStrategy, Integer> roundPoints = new HashMap<>();
        List<GeneticStrategy> strategies = new ArrayList<>(this.points.keySet());

        for (int i = 0; i < strategies.size(); i++) {
            for (int j = i + 1; j < strategies.size(); j++) {
                GeneticStrategy s1 = strategies.get(i);
                GeneticStrategy s2 = strategies.get(j);

                Game game = new Game(s1, s2);
                List<Integer> results = game.executeGame(numGames);

                roundPoints.put(s1, roundPoints.getOrDefault(s1, 0) + results.get(0));
                roundPoints.put(s2, roundPoints.getOrDefault(s2, 0) + results.get(1));
            }
        }

        return roundPoints;
    }

    /**
     * Adds points from a round to the overall tournament points.
     *
     * @param newPoints Points from the latest round
     */
    private void addNewPoints(HashMap<GeneticStrategy, Integer> newPoints) {
        newPoints.forEach((strategy, newScore) -> {
            int currentScore = this.points.getOrDefault(strategy, 0);
            this.points.put(strategy, currentScore + newScore);
        });
    }

    /**
     * Sorts strategies by their scores in descending order.
     *
     * @param entries Set of entries to sort
     * @return A sorted list of entries
     */
    private List<Map.Entry<GeneticStrategy, Integer>> sortEntries(Set<Map.Entry<GeneticStrategy, Integer>> entries) {
        List<Map.Entry<GeneticStrategy, Integer>> sortedEntries = new ArrayList<>(entries);
        sortedEntries.sort((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()));
        return sortedEntries;
    }
}
