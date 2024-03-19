package Panels;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.util.List;

public class NewTripController {
    private List<String> places;
    @FXML
    private Button updateButton;

    @FXML
    private Button AnewTrip;

    @FXML
    private ComboBox<String> Places1; // Changed to String

    @FXML
    private ComboBox<String> Places2; // Changed to String

    @FXML
    private ComboBox<?> Vehicles;
    @FXML
    private ComboBox<String> comboBoxVehicles;

    @FXML
    private void handleUpdateButtonAction() {
        // Clear previous items and add new ones
        Platform.runLater(() -> {
            this.Places1.getItems().clear();
            this.Places2.getItems().clear();

            if (this.places != null) {
                this.Places1.getItems().addAll(this.places);
                this.Places2.getItems().addAll(this.places);
            }
        });
    }

    public void setPlaces(List<String> places) {
        this.places = places;
        System.out.println("Setting places: " + places);

        // Ensure updates to the ComboBoxes are made on the JavaFX thread
        Platform.runLater(() -> {
            this.Places1.getItems().addAll(places);
            this.Places2.getItems().addAll(places);
        });
    }

    public void initialize() {
        // Añade 3 motocicletas
        comboBoxVehicles.getItems().add("Motorcycle 1");
        comboBoxVehicles.getItems().add("Motorcycle 2");
        comboBoxVehicles.getItems().add("Motorcycle 3");

        // Añade 3 carros estándar
        comboBoxVehicles.getItems().add("Standard Car 1");
        comboBoxVehicles.getItems().add("Standard Car 2");
        comboBoxVehicles.getItems().add("Standard Car 3");

        // Añade 3 carros premium
        comboBoxVehicles.getItems().add("Premium Car 1");
        comboBoxVehicles.getItems().add("Premium Car 2");
        comboBoxVehicles.getItems().add("Premium Car 3");
    }

}