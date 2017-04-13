package mp2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chris
 */
public class Automobile extends Vehicle{
    private boolean hybrid;
    private int maxPassengers;
    private float trunkSpace;

    public Automobile(){
        //explicitly calling super class constructor. Not necessary, but here for clarity
        super();
        hybrid = false;
        maxPassengers = 0;
        trunkSpace = 0.0f;
    }
    
    public Automobile(boolean hybrid, int maxPassengers, float trunkSpace){
        //explicitly calling for clarity, not necessary
        super();
        this.hybrid = hybrid;
        this.maxPassengers = maxPassengers;
        this.trunkSpace = trunkSpace;
    }
    
    public Automobile(String make, String model, String vin, int year, boolean hybrid, int maxPassengers, float trunkSpace){
        //Now necessary as we are passing values to superclass methods
        super(make, model, vin, year);
        this.hybrid = hybrid;
        this.maxPassengers = maxPassengers;
        this.trunkSpace = trunkSpace;
    }
    
    /**
     * @return the hybrid
     */
    public boolean isHybrid() {
        return hybrid;
    }

    /**
     * @param hybrid the hybrid to set
     */
    public void setHybrid(boolean hybrid) {
        this.hybrid = hybrid;
    }

    /**
     * @return the maxPassengers
     */
    public int getMaxPassengers() {
        return maxPassengers;
    }

    /**
     * @param maxPassengers the maxPassengers to set
     */
    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    /**
     * @return the trunkSpace
     */
    public float getTrunkSpace() {
        return trunkSpace;
    }

    /**
     * @param trunkSpace the trunkSpace to set
     */
    public void setTrunkSpace(float trunkSpace) {
        this.trunkSpace = trunkSpace;
    }
    
    public int hybridAsInt(){
        if(hybrid)
            return 1;
        return 0;
    }
    
    @Override
    public String toString(){
        return "A" + System.lineSeparator() + make + System.lineSeparator() + model + System.lineSeparator() + vin + System.lineSeparator() + year + System.lineSeparator() + hybridAsInt() + System.lineSeparator() + maxPassengers + System.lineSeparator() + trunkSpace + System.lineSeparator();
    }
}
