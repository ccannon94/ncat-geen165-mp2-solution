/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp2;

import java.io.File;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Chris
 */
public class MajorProgram2_UI extends Application implements EventHandler{
    Fleet activeFleet;
    
    Scene mainMenu;
    Label fleetLabel;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        fleetLabel = new Label("Please Load a Fleet");
        
        pane.setTop(fleetLabel);
        pane.setCenter(createControlBox());
        
        mainMenu = new Scene(pane);
        
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public void launchGUI(){
        launch();
    }
    
    private HBox createControlBox(){
        HBox controlBox = new HBox();
        
        Button viewButton = new Button("View");
        Button addButton = new Button("Add");
        Button loadButton = new Button("Load");
        Button saveButton = new Button("Save");
        Button exitButton = new Button("Exit");
        
        controlBox.getChildren().addAll(viewButton, addButton, loadButton, saveButton, exitButton);
        
        return controlBox;
    }
    
    @Override
    public void handle(Event event) {
        Button sourceButton = (Button) event.getSource();
        
        if(sourceButton != null && sourceButton.getText().equals("Load")){
            FileChooser loadChooser = new FileChooser();
            loadChooser.setTitle("Select an input Fleet file");
            loadChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            loadChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File inputFile = loadChooser.showOpenDialog(null);
            
            activeFleet.loadFleet(inputFile.getAbsolutePath());
            fleetLabel.setText(activeFleet.getFleetName());
        }
    }
    
}
