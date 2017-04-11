/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp2;

import java.util.ArrayList;

/**
 *
 * @author Chris
 */
public class Fleet {
    private String fleetName;
    ArrayList<Vehicle> fleetList;

    /**
     * @return the fleetName
     */
    public String getFleetName() {
        return fleetName;
    }

    /**
     * @param fleetName the fleetName to set
     */
    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }
    
    public int getNumVehicles(){
        return fleetList.size();
    }
    
    public Vehicle getVehcile(int index){
        return fleetList.get(index);
    }
    
    public void addVehicle(Vehicle item){
        fleetList.add(item);
    }
    
    public void setVehicle(int index, Vehicle item){
        fleetList.set(index, item);
    }
    
    public Vehicle removeVehicle(int index){
        return fleetList.remove(index);
    }
}
