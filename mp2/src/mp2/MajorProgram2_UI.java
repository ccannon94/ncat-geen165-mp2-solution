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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    
    Label fleetLabel;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        fleetLabel = new Label("Please Load a Fleet");
        
        pane.setTop(fleetLabel);
        pane.setCenter(createControlBox());
        
        Scene mainMenu = new Scene(pane);
        
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public void launchGUI(){
        launch();
    }
    
    private HBox createControlBox(){
        HBox controlBox = new HBox();
        
        Button viewButton = new Button("View");
        viewButton.setOnAction(this);
        Button addButton = new Button("Add");
        addButton.setOnAction(this);
        Button loadButton = new Button("Load");
        loadButton.setOnAction(this);
        Button saveButton = new Button("Save");
        saveButton.setOnAction(this);
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(this);
        
        controlBox.getChildren().addAll(viewButton, addButton, loadButton, saveButton, exitButton);
        
        return controlBox;
    }
    
    @Override
    public void handle(Event event) {
        Button sourceButton = (Button) event.getSource();
        
        if(sourceButton != null && sourceButton.getText().equals("Load")){
            activeFleet = new Fleet();
            
            FileChooser loadChooser = new FileChooser();
            loadChooser.setTitle("Select an input Fleet file");
            loadChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            loadChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File inputFile = loadChooser.showOpenDialog(null);
            
            activeFleet.loadFleet(inputFile.getAbsolutePath());
            fleetLabel.setText(activeFleet.getFleetName());
        }
        
        if(sourceButton != null && sourceButton.getText().equals("View")){
            if(activeFleet == null){
                Alert noActiveFleetAlert = new Alert(AlertType.INFORMATION);
                noActiveFleetAlert.setTitle("No Active Fleet Detected");
                noActiveFleetAlert.setHeaderText("You have not loaded a Fleet into the application");
                noActiveFleetAlert.setContentText("Please load a fleet by selecting the 'Load' button before continuing");
                noActiveFleetAlert.showAndWait();
                
                return;
            }
            
            Stage viewStage = new Stage();
            
            BorderPane viewBorderPane = new BorderPane();
            
            Scene viewWindow = new Scene(viewBorderPane);
            
            viewStage.setScene(viewWindow);
            viewStage.show();
        }
    }
    
}
