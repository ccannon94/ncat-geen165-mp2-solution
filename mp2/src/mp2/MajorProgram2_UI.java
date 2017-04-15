/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp2;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
        if (sourceButton != null && sourceButton.getText().equals("Add")) {
            handleAdd();
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
    
    private void handleAdd(){
        if(activeFleet == null){
            alertNoActiveFleet();
            
            return;
        }
        
        Stage addStage = new Stage();
        
        BorderPane addBorderPane = new BorderPane();
        
        Label addControlsLabel = new Label("Select a Vehicle type to add:");
        ObservableList<String> vehicleTypeList = FXCollections.observableArrayList("Automobile", "Cargo Van", "Passenger Van");
        ListView vehicleTypesListView = new ListView(vehicleTypeList);
        
        Button selectTypeButton = new Button("Select");
        selectTypeButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                int selectedIndex = vehicleTypesListView.getSelectionModel().getSelectedIndex();
                
                switch(selectedIndex){
                    case 0:
                        presentAddAutoView();
                        break;
                    case 1:
                        presentAddCargoVanView();
                        break;
                    case 2:
                        presentAddPassengerVanView();
                        break;
                    default:
                        Alert noSelectedIndex = new Alert(AlertType.INFORMATION);
                        noSelectedIndex.setTitle("No Selected Vehicle Type");
                        noSelectedIndex.setHeaderText("You have not selected a vehicle type from the list");
                        noSelectedIndex.setContentText("Please select a vehicle type from the list to the left before continuing");
                        noSelectedIndex.showAndWait();

                        break;
                }
            }

            private void presentAddAutoView() {
                Stage addAutoStage = new Stage();
                
                BorderPane addAutoPane = new BorderPane();
                
                GridPane addAutoGrid = new GridPane();
                Label makeLabel = new Label("Make: ");
                Label modelLabel = new Label("Model: ");
                Label vinLabel = new Label("VIN: ");
                Label yearLabel = new Label("Year: ");
                Label hybridLabel = new Label("Hybrid: ");
                Label maxPassengersLabel = new Label("Maximum Passengers: ");
                Label trunkSpaceLabel = new Label("Trunk Space: ");
                
                TextField makeTextField = new TextField();
                TextField modelTextField = new TextField();
                TextField vinTextField = new TextField();
                TextField yearTextField = new TextField();
                TextField maxPassengersTextField = new TextField();
                TextField trunkSpaceTextField = new TextField();
                
                ComboBox hybridComboBox = new ComboBox();
                hybridComboBox.getItems().addAll(
                        "Yes",
                        "No"
                );
                
                addAutoGrid.addRow(0, makeLabel, makeTextField);
                addAutoGrid.addRow(1, modelLabel, modelTextField);
                addAutoGrid.addRow(2, vinLabel, vinTextField);
                addAutoGrid.addRow(3, yearLabel, yearTextField);
                addAutoGrid.addRow(4, hybridLabel, hybridComboBox);
                addAutoGrid.addRow(5, maxPassengersLabel, maxPassengersTextField);
                addAutoGrid.addRow(6, trunkSpaceLabel, trunkSpaceTextField);
                
                Button enterButton = new Button("Enter");
                enterButton.setOnAction(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        Automobile newAuto = new Automobile();
                        try{
                            newAuto.setMake(makeTextField.getText());
                            newAuto.setModel(modelTextField.getText());
                            newAuto.setVin(vinTextField.getText());
                            newAuto.setYear(Integer.parseInt(yearTextField.getText().trim()));
                            newAuto.setMaxPassengers(Integer.parseInt(maxPassengersTextField.getText().trim()));
                            newAuto.setTrunkSpace(Float.parseFloat(trunkSpaceTextField.getText().trim()));

                            
                            if(hybridComboBox.getSelectionModel().getSelectedIndex() == 0){
                                newAuto.setHybrid(true);
                            }else if(hybridComboBox.getSelectionModel().getSelectedIndex() == 1){
                                newAuto.setHybrid(false);
                            }else{
                                throw new Exception("No hybrid value selected");
                            }
                            
                            activeFleet.addVehicle(newAuto);
                            saved = false;
                            
                            addAutoStage.close();
                        }catch(Exception e){
                            Alert automobileCreationError = new Alert(AlertType.INFORMATION);
                            automobileCreationError.setTitle("Automobile not created");
                            automobileCreationError.setHeaderText("There was an error creating your automobile");
                            automobileCreationError.setContentText("Please make sure you have entered an appropriate value for each property before continuing");
                            automobileCreationError.showAndWait();
                        }
                    }
                });
                
                addAutoPane.setTop(new Label("Add Automobile"));
                addAutoPane.setCenter(addAutoGrid);
                addAutoPane.setBottom(enterButton);
                
                Scene addAutoScene = new Scene(addAutoPane);
                addAutoStage.setScene(addAutoScene);
                addAutoStage.show();
            }

            private void presentAddCargoVanView() {
                Stage addCargoStage = new Stage();
                
                BorderPane addCargoPane = new BorderPane();
                
                GridPane addCargoGrid = new GridPane();
                Label makeLabel = new Label("Make: ");
                Label modelLabel = new Label("Model: ");
                Label vinLabel = new Label("VIN: ");
                Label yearLabel = new Label("Year: ");
                Label clearanceHeightLabel = new Label("Clearance Height: ");
                Label numWindowsLabel = new Label("Number of Windows: ");
                Label maxLoadLabel = new Label("Max Load: ");
                Label cargoAreaLabel = new Label("Cargo Area: ");
                
                TextField makeTextField = new TextField();
                TextField modelTextField = new TextField();
                TextField vinTextField = new TextField();
                TextField yearTextField = new TextField();
                TextField clearanceHeightTextField = new TextField();
                TextField numWindowsTextField = new TextField();
                TextField maxLoadTextField = new TextField();
                TextField cargoAreaTextField = new TextField();
                
                addCargoGrid.addRow(0, makeLabel, makeTextField);
                addCargoGrid.addRow(1, modelLabel, modelTextField);
                addCargoGrid.addRow(2, vinLabel, vinTextField);
                addCargoGrid.addRow(3, yearLabel, yearTextField);
                addCargoGrid.addRow(4, clearanceHeightLabel, clearanceHeightTextField);
                addCargoGrid.addRow(5, numWindowsLabel, numWindowsTextField);
                addCargoGrid.addRow(6, maxLoadLabel, maxLoadTextField);
                addCargoGrid.addRow(7, cargoAreaLabel, cargoAreaTextField);
                
                Button enterButton = new Button("Enter");
                enterButton.setOnAction(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        CargoVan newCargo = new CargoVan();
                        try{
                            newCargo.setMake(makeTextField.getText());
                            newCargo.setModel(modelTextField.getText());
                            newCargo.setVin(vinTextField.getText());
                            newCargo.setYear(Integer.parseInt(yearTextField.getText().trim()));
                            newCargo.setClearanceHeight(Float.parseFloat(clearanceHeightTextField.getText().trim()));
                            newCargo.setNumWindows(Integer.parseInt(numWindowsTextField.getText().trim()));
                            newCargo.setMaxLoad(Float.parseFloat(maxLoadTextField.getText().trim()));
                            newCargo.setCargoArea(Float.parseFloat(cargoAreaTextField.getText().trim()));
                            
                            activeFleet.addVehicle(newCargo);
                            saved = false;
                            
                            addCargoStage.close();
                        }catch(Exception e){
                            Alert cargoVanCreationError = new Alert(AlertType.INFORMATION);
                            cargoVanCreationError.setTitle("Cargo van not created");
                            cargoVanCreationError.setHeaderText("There was an error creating your cargo van");
                            cargoVanCreationError.setContentText("Please make sure you have entered an appropriate value for each property before continuing");
                            cargoVanCreationError.showAndWait();
                        }
                    }
                });
                
                addCargoPane.setTop(new Label("Add Cargo Van"));
                addCargoPane.setCenter(addCargoGrid);
                addCargoPane.setBottom(enterButton);
                
                Scene addCargoScene = new Scene(addCargoPane);
                addCargoStage.setScene(addCargoScene);
                addCargoStage.show();
            }

            private void presentAddPassengerVanView() {
            }
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                addStage.close();
            }
        });
        
        VBox selectVehicleVBox = new VBox(addControlsLabel, vehicleTypesListView);
        HBox addControlsHBox = new HBox(selectTypeButton, backButton);
        
        addBorderPane.setTop(fleetLabel);
        addBorderPane.setCenter(selectVehicleVBox);
        addBorderPane.setBottom(addControlsHBox);
        
        Scene addScene = new Scene(addBorderPane);
        
        addStage.setScene(addScene);
        addStage.show();
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
        if(activeFleet != null && !saved){
            Alert unsavedChangesAlert = new Alert(AlertType.CONFIRMATION);
            unsavedChangesAlert.setTitle("Unsaved changes detected");
            unsavedChangesAlert.setHeaderText("Would you like to save your changes?");
            unsavedChangesAlert.setContentText("Select 'OK' to save your changes, press 'cancel' to continue exiting");
            
            Optional<ButtonType> result = unsavedChangesAlert.showAndWait();
            if (result.get() == ButtonType.OK){
                handleSave();
                System.exit(0);
            } else {
                System.exit(0);
            }
        }
        System.exit(0);
    }
    
    private void alertNoActiveFleet(){
        Alert noActiveFleetAlert = new Alert(AlertType.INFORMATION);
        noActiveFleetAlert.setTitle("No Active Fleet Detected");
        noActiveFleetAlert.setHeaderText("You have not loaded a Fleet into the application");
        noActiveFleetAlert.setContentText("Please load a fleet by selecting the 'Load' button before continuing");
        noActiveFleetAlert.showAndWait();
    }
}
