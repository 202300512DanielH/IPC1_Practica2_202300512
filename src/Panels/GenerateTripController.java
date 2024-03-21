package Panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GenerateTripController implements Initializable {

    @FXML
    private Button GenerateAnewTrip;

    @FXML
    private ComboBox<String> Places1;

    @FXML
    private ComboBox<String> Places2;

    @FXML
    private ComboBox<String> SelectedVehicles;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab generateTripTab;

    private MainWindowsController mainWindowsController;

    public GenerateTripController() {
        // Constructor sin argumentos
    }

    public void setMainWindowsController(MainWindowsController mainWindowsController) {
        this.mainWindowsController = mainWindowsController;
    }

    public void setTabPane(TabPane tabPane) {
        this.tabPane = tabPane;
    }

    public void setGenerateTripTab(Tab generateTripTab) {
        this.generateTripTab = generateTripTab;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Deja vacío el método initialize
        fillVehicleComboBox();
    }

    public void postInitialize() {
        if (tabPane != null) {
            tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
                if (generateTripTab == newTab) {
                    LoadRoutesController loadRoutesController = mainWindowsController.getLoadRoutesController();
                    if (loadRoutesController != null) {
                        List<Route> routes = loadRoutesController.getAllRoutes();
                        fillComboBoxes(routes);
                    } else {
                        System.err.println("LoadRoutesController is null in GenerateTripController");
                    }
                }

            });
        } else {
            System.err.println("TabPane is null in GenerateTripController");
        }
    }

    public void fillComboBoxes(List<Route> routes) {
        // Limpiar los ComboBox
        Places1.getItems().clear();
        Places2.getItems().clear();

        // Llenar Places1 con todos los lugares de inicio y fin
        for (Route route : routes) {
            if (!Places1.getItems().contains(route.getStart())) {
                Places1.getItems().add(route.getStart());
            }
            if (!Places1.getItems().contains(route.getEnd())) {
                Places1.getItems().add(route.getEnd());
            }
        }

        // Añadir un listener a Places1 para actualizar Places2 cuando se selecciona un lugar
        Places1.valueProperty().addListener((obs, oldValue, newValue) -> {
            Places2.getItems().clear(); // Limpiar Places2
            for (Route route : routes) {
                // Si el lugar seleccionado en Places1 es el lugar de inicio de la ruta, añadir el lugar final a Places2
                if (route.getStart().equals(newValue)) {
                    Places2.getItems().add(route.getEnd());
                }
                // Si el lugar seleccionado en Places1 es el lugar final de la ruta, añadir el lugar de inicio a Places2
                else if (route.getEnd().equals(newValue)) {
                    Places2.getItems().add(route.getStart());
                }
            }
        });
    }

    private void fillVehicleComboBox() {
        ObservableList<String> vehicles = FXCollections.observableArrayList(
                "Moto 1", "Moto 2", "Moto 3",
                "Carro 1", "Carro 2", "Carro 3",
                "Vehículo Premium 1", "Vehículo Premium 2", "Vehículo Premium 3"
        );
        SelectedVehicles.setItems(vehicles);
    }


}