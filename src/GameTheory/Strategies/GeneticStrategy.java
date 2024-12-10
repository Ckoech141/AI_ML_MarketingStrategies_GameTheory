package GameTheory.Strategies;

import java.util.List;

public abstract class GeneticStrategy extends Strategy {
    protected double weight;  // The weight parameter to control strategy behavior

    // Constructor to initialize with a weight
    public GeneticStrategy(String companyName, double weight) {
        super(companyName);  // Call to parent class constructor to set company name
        this.weight = weight;
    }

    /**
     * Abstract method for mutation. This will be implemented by subclasses
     * to define how the strategy evolves.
     */
    public abstract GeneticStrategy mutate();

    // Getter and setter for weight
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public GeneticStrategy mutateNew() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
