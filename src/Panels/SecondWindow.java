package Panels;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import javafx.scene.control.TabPane;
import javafx.event.ActionEvent;

public class SecondWindow {
    @FXML
    private TableView<Trip> table;
    @FXML
    private TableColumn<Trip, String> startColumn;
    @FXML
    private TableColumn<Trip, String> endColumn;
    @FXML
    private TableColumn<Trip, Double> distanceColumn;
    @FXML
    private TabPane tabPane;

    private TabOpener tabOpener;

    private Set<String> uniquePlaces = new HashSet<>();

    public void setTabOpener(TabOpener tabOpener) {
        this.tabOpener = tabOpener;
    }


    @FXML
    private Button Editdistance;

    @FXML
    private Button Uploadtrips;


    @FXML

    // Create list of routes
    private List<Trip> trips = new ArrayList<>();


    @FXML
    private TableColumn<Trip, Integer> idColumn;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
    }

    // Rest of your code...

    @FXML
    private void handleButtonEditdistance() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditDistance.fxml"));
            Parent root = loader.load();
            EditDistanceController controller = loader.getController();
            System.out.println("In SecondWindow:");
            for (Trip trip : this.trips) {
                System.out.println("ID: " + trip.getId() + ", Distance: " + trip.getDistance());
            }
            controller.initData(this.trips);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Distance");
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonUploadtrips() {
        // Create a FileChooser object
        FileChooser fileChooser = new FileChooser();

        // Set extension filter to .csv files only
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog and get the selected file
        File file = fileChooser.showOpenDialog(null);

        // Process the file. E.g., read the file.
        if (file != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    Trip trip = new Trip(values[0], values[1], Double.parseDouble(values[2]));

                    // Add trip to the table as well as to the list 'trips'
                    table.getItems().add(trip);
                    trips.add(trip);   // This is the missing line

                    uniquePlaces.add(values[0]);
                    uniquePlaces.add(values[1]);
                }
                //handleButtonAddNewTripTab();
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*handleButtonAddNewTripTab(new ArrayList<>(this.uniquePlaces));  // Here is the change.
            System.out.println("Unique places: " + uniquePlaces);*/

        }

        handleButtonAddNewTripTab(new ArrayList<>(this.uniquePlaces));
        System.out.println("Unique places: " + uniquePlaces);
    }

    @FXML
    private void handleButtonAddNewTripTab() {
        if (tabOpener != null) {
            tabOpener.openTab("NewTrip.fxml", "New Trip", new ArrayList<>(this.uniquePlaces));
        }
    }
    private void handleButtonAddNewTripTab(List<String> places) {  // Here is another change.
        if (tabOpener != null) {
            tabOpener.openTab("NewTrip.fxml", "New Trip", places);
        }
    }

}