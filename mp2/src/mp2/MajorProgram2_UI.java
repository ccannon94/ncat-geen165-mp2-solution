/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp2;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Chris
 */
public class MajorProgram2_UI extends Application implements EventHandler{
    Scene mainMenu;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        Label fleetLabel = new Label();
        
        pane.setCenter(createControlBox());
        
        mainMenu = new Scene(pane);
        
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public void launchGUI(String[] args){
        System.out.println("Launching GUI");
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
