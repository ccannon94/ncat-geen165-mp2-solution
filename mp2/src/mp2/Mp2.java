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
        
        if(args.length == 0){
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                inputFileName = chooser.getSelectedFile().getAbsolutePath();
            }
        }else{
            inputFileName = args[0];
        }
        
        new MajorProgram2_UI().launchGUI(args);
    }
    
}
