/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chris
 */
public class Van extends Vehicle{
    protected float clearanceHeight;
    protected int numWindows;

    public Van(){
        //not necessary, but nice for clarity
        super();
        clearanceHeight = 0.0f;
        numWindows = 0;
    }
    
    public Van(float clearanceHeight, int numWindows){
        //super noarg for clarity
        super();
        this.clearanceHeight = clearanceHeight;
        this.numWindows = numWindows;
    }
    
    public Van(String make, String model, String vin, int year, float clearanceHeight, int numWindows){
        super(make, model, vin, year);
        this.clearanceHeight = clearanceHeight;
        this.numWindows = numWindows;
    }
    
    /**
     * @return the clearanceHeight
     */
    public float getClearanceHeight() {
        return clearanceHeight;
    }

    /**
     * @param clearanceHeight the clearanceHeight to set
     */
    public void setClearanceHeight(float clearanceHeight) {
        this.clearanceHeight = clearanceHeight;
    }

    /**
     * @return the numWindows
     */
    public int getNumWindows() {
        return numWindows;
    }

    /**
     * @param numWindows the numWindows to set
     */
    public void setNumWindows(int numWindows) {
        this.numWindows = numWindows;
    }
    
    @Override
    public String toString(){
        return "V" + System.lineSeparator() + make + System.lineSeparator() + model + System.lineSeparator() + vin + System.lineSeparator() + year + System.lineSeparator() + clearanceHeight + System.lineSeparator() + numWindows;
    }
}
