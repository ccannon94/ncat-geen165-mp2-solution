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
}
