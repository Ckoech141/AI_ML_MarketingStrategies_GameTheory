package GameTheory.Strategies;

import java.util.ArrayList;
import java.util.List;

public abstract class Strategy {
    protected List<Boolean> opponentMoveHistory;  // Keeps track of the opponent's moves
    protected String companyName;  // Name of the company for the strategy
    protected int totalPoints;  // Holds the total score of the strategy

    // Constructor
    public Strategy(String companyName) {
        this.opponentMoveHistory = new ArrayList<>();
        this.companyName = companyName;
        this.totalPoints = 0;  // Initialize the total points to 0
    }
    protected int score = 0;

    public void updateScore(int points) {
    this.score += points;
}


    // Abstract method to be implemented by subclasses to define strategy behavior
    public abstract boolean makeMove();

    // Add an opponent's move to the history
    public void addOpponentMove(boolean opponentMove) {
        this.opponentMoveHistory.add(opponentMove);
    }
    public List<Boolean> getOpponentMoves() {
    return this.opponentMoveHistory;
}


    // Clears the strategy history (opponent moves)
    public void clearStrategy() {
        this.opponentMoveHistory.clear();
        this.totalPoints = 0;  // Optionally reset points when clearing strategy
    }

    @Override
    public String toString() {
        return companyName + " - " + this.getClass().getSimpleName();
    }

    public String getStrategyName() {
        return toString();
    }

    // Adds points to the total score of the strategy
    public void addPoints(int points) {
        this.totalPoints += points;
    }

    // Returns the total points accumulated by this strategy
    public int getTotalPoints() {
        return this.totalPoints;
    }

    // Resets the total points for a new round of the game
    public void reset() {
        this.totalPoints = 0;  // Reset the points to 0
        this.opponentMoveHistory.clear();  // Clear the opponent's move history
    }

    // Add outcome to the strategy's record (this is a generic placeholder)
    public void addOutcome(int outcome) {
        this.totalPoints += outcome;  // For now, just add the outcome to points
    }

    // Getter for the total points (optional, as getTotalPoints is already defined)
    public int getPoints() {
        return this.totalPoints;
    }

    public void recordOpponentMove(boolean s2Move) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
