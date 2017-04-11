/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp2;

import javax.swing.JOptionPane;

/**
 *
 * @author Chris
 */
public class Mp2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Vehicle myVehicle = new Vehicle("Honda", "NSX", "12345HONDA5431", 2017);
        Automobile myAuto = new Automobile("Honda", "Civic", "132434DFJKFJE", 2016, false, 5, 2.4f);
        Van myVan = new Van("Honda", "Odyssey", "1240OIFJ3R4UT09", 2015, 11.8f, 8);
        PassengerVan myPVan = new PassengerVan("Honda", "Odyssey", "FJ3IUFH345JT4", 2018, 10.6f, 8, 3, 7, true);
        CargoVan myCVan = new CargoVan("Mercedes", "Sprinter", "JFIJ3483TH8H5", 2016, 16.3f, 3, 2400f, 42.6f);
        
        JOptionPane.showMessageDialog(null, myVehicle, "Vehicle", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null, myAuto, "Automobile", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null, myVan, "Van", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null, myPVan, "Passenger Van", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null, myCVan, "Cargo Van", JOptionPane.PLAIN_MESSAGE);
    }
    
}
