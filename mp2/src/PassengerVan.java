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
}
