/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris
 */
public class Fleet {
    private String fleetName;
    ArrayList<Vehicle> fleetList;

    public Fleet(){
        fleetName = "";
        fleetList = new ArrayList<>();
    }
    
    public Fleet(String fleetName){
        this.fleetName = fleetName;
        fleetList = new ArrayList<>();
    }
    
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
    
    public void loadFleet(String inputFileName){
        try {
            Scanner reader = new Scanner(new File(inputFileName));
            
            fleetName = reader.nextLine();
            
            while(reader.hasNext()){
                String vehicleType = reader.nextLine().trim();
                
                if(vehicleType == "A"){
                    Automobile newAuto = new Automobile();
                    
                    newAuto.setMake(reader.nextLine());
                    newAuto.setModel(reader.nextLine());
                    newAuto.setVin(reader.nextLine());
                    newAuto.setYear(Integer.parseInt(reader.nextLine().trim().trim()));
                    
                    String hybridString = reader.nextLine().trim();
                    if(hybridString.equals("1"))
                        newAuto.setHybrid(true);
                    else
                        newAuto.setHybrid(false);
                    
                    newAuto.setMaxPassengers(Integer.parseInt(reader.nextLine().trim()));
                    newAuto.setTrunkSpace(Float.parseFloat(reader.nextLine().trim()));
                    
                    fleetList.add(newAuto);
                }else if(vehicleType == "P"){
                    PassengerVan newPVan = new PassengerVan();
                    
                    newPVan.setMake(reader.nextLine());
                    newPVan.setModel(reader.nextLine());
                    newPVan.setVin(reader.nextLine());
                    newPVan.setYear(Integer.parseInt(reader.nextLine().trim()));
                    
                    newPVan.setClearanceHeight(Float.parseFloat(reader.nextLine().trim()));
                    newPVan.setNumWindows(Integer.parseInt(reader.nextLine().trim()));
                    
                    newPVan.setNumSeatRows(Integer.parseInt(reader.nextLine().trim()));
                    newPVan.setMaxPassengers(Integer.parseInt(reader.nextLine().trim()));
                    
                    String dvdString = reader.nextLine().trim();
                    if(dvdString.equals("1"))
                        newPVan.setDvdPlayer(true);
                    else
                        newPVan.setDvdPlayer(false);
                    
                    fleetList.add(newPVan);
                }else{
                    CargoVan newCVan = new CargoVan();
                    
                    newCVan.setMake(reader.nextLine());
                    newCVan.setModel(reader.nextLine());
                    newCVan.setVin(reader.nextLine());
                    newCVan.setYear(Integer.parseInt(reader.nextLine().trim()));
                    
                    newCVan.setClearanceHeight(Float.parseFloat(reader.nextLine().trim()));
                    newCVan.setNumWindows(Integer.parseInt(reader.nextLine().trim()));
                    
                    newCVan.setMaxLoad(Float.parseFloat(reader.nextLine().trim()));
                    newCVan.setClearanceHeight(Float.parseFloat(reader.nextLine().trim()));
                    
                    fleetList.add(newCVan);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fleet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Fleet input file not found");
        }
    }
    
    public void saveFleet(String outputFileName){
        try {
            PrintWriter writer = new PrintWriter(new File(outputFileName));
            
            writer.println(toString());
            
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fleet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Fleet output file not found");
        }
    }
    
    public ArrayList<Vehicle> getVehicleList(Class vehicleClass){
        ArrayList<Vehicle> sublist = new ArrayList<>();
        
        if(vehicleClass == Automobile.class){
            for(int i = 0; i < fleetList.size(); i++){
                if(fleetList.get(i) instanceof Automobile)
                    sublist.add(fleetList.get(i));
            }
        }else if(vehicleClass == PassengerVan.class){
            for(int i = 0; i < fleetList.size(); i++){
                if(fleetList.get(i) instanceof PassengerVan)
                    sublist.add(fleetList.get(i));
            }
        }else{
            for(int i = 0; i < fleetList.size(); i++){
                if(fleetList.get(i) instanceof CargoVan)
                    sublist.add(fleetList.get(i));
            }
        }
        
        return sublist;
    }
    
    public String toString(){
        String returnString = fleetName + System.lineSeparator();
        for(int i = 0; i < fleetList.size(); i++){
            returnString += fleetList.get(i).toString();
        }
        
        return returnString;
    }
}
