/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chris
 */
public class CargoVan extends Van{
    private float maxLoad;
    private float cargoArea;

    public CargoVan(){
        //super constructor called for clarity only
        super();
        maxLoad = 0.0f;
        cargoArea = 0.0f;
    }
    
    /**
     * @return the maxLoad
     */
    public float getMaxLoad() {
        return maxLoad;
    }

    /**
     * @param maxLoad the maxLoad to set
     */
    public void setMaxLoad(float maxLoad) {
        this.maxLoad = maxLoad;
    }

    /**
     * @return the cargoArea
     */
    public float getCargoArea() {
        return cargoArea;
    }

    /**
     * @param cargoArea the cargoArea to set
     */
    public void setCargoArea(float cargoArea) {
        this.cargoArea = cargoArea;
    }
}
