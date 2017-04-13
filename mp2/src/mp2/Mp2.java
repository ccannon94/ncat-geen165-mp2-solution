/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp2;

import javax.swing.JFileChooser;
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
        String inputFileName = "";
        String outputFileName = "";
        
        if(args.length == 0){
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                inputFileName = chooser.getSelectedFile().getAbsolutePath();
            }
            
            int result2 = chooser.showSaveDialog(null);
            if(result2 == JFileChooser.APPROVE_OPTION){
                outputFileName = chooser.getSelectedFile().getAbsolutePath();
            }
        }else{
            inputFileName = args[0];
            outputFileName = args[1];
        }
        
        Fleet aggieFleet = new Fleet();
        aggieFleet.loadFleet(inputFileName);
        
        Automobile nsx = new Automobile("Acura", "NSX", "IJIORT48H3INTGR", 2018, true, 2, 0.7f);
        
        aggieFleet.saveFleet(outputFileName);
    }
    
}
