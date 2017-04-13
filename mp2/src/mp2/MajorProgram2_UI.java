/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp2;

import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Chris
 */
public class MajorProgram2_UI extends Application implements EventHandler {

    Fleet activeFleet;
    boolean saved;
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

    public void launchGUI() {
        launch();
    }

    private HBox createControlBox() {
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

        if (sourceButton != null && sourceButton.getText().equals("Load")) {
            handleLoad();
        }

        if (sourceButton != null && sourceButton.getText().equals("View")) {
            handleView();
        }
        if(sourceButton != null && sourceButton.getText().equals("Save")){
            handleSave();
        }
        if (sourceButton != null && sourceButton.getText().equals("Exit")){
            handleExit();
        }
    }

    private void handleLoad() {
        activeFleet = new Fleet();

        FileChooser loadChooser = new FileChooser();
        loadChooser.setTitle("Select an input Fleet file");
        loadChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        loadChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File inputFile = loadChooser.showOpenDialog(null);

        activeFleet.loadFleet(inputFile.getAbsolutePath());
        saved = true;
        fleetLabel.setText(activeFleet.getFleetName());
    }

    private void handleView() {
        if (activeFleet == null) {
            alertNoActiveFleet();

            return;
        }

        Stage viewStage = new Stage();

        EventHandler backToMenu = new EventHandler() {
            @Override
            public void handle(Event event) {
                viewStage.close();
            }
        };
        
        BorderPane viewBorderPane = new BorderPane();

        ObservableList<String> vehicleTypes = FXCollections.observableArrayList();
        vehicleTypes.add("Automobiles - " + activeFleet.getVehicleList(Automobile.class).size());
        vehicleTypes.add("Cargo Vans - " + activeFleet.getVehicleList(CargoVan.class).size());
        vehicleTypes.add("Passenger Vans - " + activeFleet.getVehicleList(PassengerVan.class).size());
        ListView fleetListView = new ListView(vehicleTypes);
        
        Button backButton = new Button("Back");
        backButton.setOnAction(backToMenu);
        
        EventHandler backToList = new EventHandler() {
            @Override
            public void handle(Event event) {
                fleetListView.setItems(vehicleTypes);
                backButton.setOnAction(backToMenu);
            }
        }; 
        
        Button viewVehicleTypeButton = new Button("View");
        viewVehicleTypeButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                ObservableList<String> vehicleList = FXCollections.observableArrayList();

                int currentlySelectedIndex = fleetListView.getSelectionModel().getSelectedIndex();

                switch (currentlySelectedIndex) {
                    case 0:
                        ArrayList<Vehicle> autos = activeFleet.getVehicleList(Automobile.class);
                        for (Vehicle auto : autos) {
                            vehicleList.add(auto.toString());
                        }
                        fleetListView.setItems(vehicleList);
                        backButton.setOnAction(backToList);
                        break;
                    case 1:
                        ArrayList<Vehicle> cVans = activeFleet.getVehicleList(CargoVan.class);
                        for (Vehicle cVan : cVans) {
                            vehicleList.add(cVan.toString());
                        }
                        fleetListView.setItems(vehicleList);
                        backButton.setOnAction(backToList);
                        break;
                    case 2:
                        ArrayList<Vehicle> pVans = activeFleet.getVehicleList(PassengerVan.class);
                        for (Vehicle pVan : pVans) {
                            vehicleList.add(pVan.toString());
                        }
                        fleetListView.setItems(vehicleList);
                        backButton.setOnAction(backToList);
                        break;
                    default:
                        Alert noSelectedIndex = new Alert(AlertType.INFORMATION);
                        noSelectedIndex.setTitle("No Selected Vehicle Type");
                        noSelectedIndex.setHeaderText("You have not selected a vehicle type from the list");
                        noSelectedIndex.setContentText("Please select a vehicle type from the list to the left before continuing");
                        noSelectedIndex.showAndWait();

                        return;
                }
            }
        });
        
        VBox viewButtons = new VBox(viewVehicleTypeButton, backButton);

        viewBorderPane.setLeft(fleetListView);
        viewBorderPane.setRight(viewButtons);

        Scene viewWindow = new Scene(viewBorderPane);

        viewStage.setScene(viewWindow);
        viewStage.show();
    }
    
    private void handleSave(){
        if(activeFleet == null){
            alertNoActiveFleet();

            return;
        }
        
        FileChooser saveChooser = new FileChooser();
        saveChooser.setTitle("Select a location to save the Fleet");
        saveChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        saveChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File outputFile = saveChooser.showOpenDialog(null);
        
        activeFleet.saveFleet(outputFile.getAbsolutePath());
        saved = true;
    }
    
    private void handleExit(){
        
    }
    
    private void alertNoActiveFleet(){
        Alert noActiveFleetAlert = new Alert(AlertType.INFORMATION);
        noActiveFleetAlert.setTitle("No Active Fleet Detected");
        noActiveFleetAlert.setHeaderText("You have not loaded a Fleet into the application");
        noActiveFleetAlert.setContentText("Please load a fleet by selecting the 'Load' button before continuing");
        noActiveFleetAlert.showAndWait();
    }
}
