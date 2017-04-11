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
public class PassengerVan extends Van{
    private int numSeatRows;
    private int maxPassengers;
    private boolean dvdPlayer;

    public PassengerVan(){
        //super constructor called for clarity
        super();
        numSeatRows = maxPassengers = 0;
        dvdPlayer = false;
    }
    
    public PassengerVan(int numSeatRows, int maxPassengers, boolean dvdPlayer){
        //called for clarity, not necessary
        super();
        this.numSeatRows = numSeatRows;
        this.maxPassengers = maxPassengers;
        this.dvdPlayer = dvdPlayer;
    }
    
    public PassengerVan(String make, String model, String vin, int year, float clearanceHeight, int numWindows, int numSeatRows, int maxPassengers, boolean dvdPlayer){
        super(make, model, vin, year, clearanceHeight, numWindows);
        this.numSeatRows = numSeatRows;
        this.maxPassengers = maxPassengers;
        this.dvdPlayer = dvdPlayer;
    }
    
    /**
     * @return the numSeatRows
     */
    public int getNumSeatRows() {
        return numSeatRows;
    }

    /**
     * @param numSeatRows the numSeatRows to set
     */
    public void setNumSeatRows(int numSeatRows) {
        this.numSeatRows = numSeatRows;
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
     * @return the dvdPlayer
     */
    public boolean isDvdPlayer() {
        return dvdPlayer;
    }

    /**
     * @param dvdPlayer the dvdPlayer to set
     */
    public void setDvdPlayer(boolean dvdPlayer) {
        this.dvdPlayer = dvdPlayer;
    }
    
    @Override
    public String toString(){
        return "P" + System.lineSeparator() + make + System.lineSeparator() + model + System.lineSeparator() + vin + System.lineSeparator() + year + System.lineSeparator() + clearanceHeight + System.lineSeparator() + numWindows + System.lineSeparator() + numSeatRows + System.lineSeparator() + maxPassengers + System.lineSeparator() + dvdPlayer + System.lineSeparator();
    }
}
