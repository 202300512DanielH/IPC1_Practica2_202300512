package Panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;


public class GenerateTripController implements Initializable {
    private TripController tripController;

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

    @FXML
    private Node generateTripTabContent;

    private MainWindowsController mainWindowsController;

    private int pressCount = 0;

    private VehicleService vehicleService = new VehicleService();




    public GenerateTripController() {
        this.mainWindowsController = new MainWindowsController();  // Initialize mainWindowsController
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
        Vehicle[] vehiclesArray = vehicleService.createVehicles();
        ObservableList<String> vehicles = FXCollections.observableArrayList();
        for (Vehicle vehicle : vehiclesArray) {
            vehicles.add(vehicle.getVehicleName());
        }
        SelectedVehicles.setItems(vehicles);
    }

    private String getSelectedRouteDistance(String startPlace, String endPlace) {
        List<Route> routes = mainWindowsController.getLoadRoutesController().getAllRoutes();
        for (Route route : routes) {
            if ((route.getStart().equals(startPlace) && route.getEnd().equals(endPlace)) ||
                    (route.getStart().equals(endPlace) && route.getEnd().equals(startPlace))) {
                return route.getDistance();
            }
        }
        return null;
    }

    public void switchToTripView() {
        System.out.println("Entrando a switchToTripView");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Trip.fxml"));
            Parent root = loader.load();

            tripController = loader.getController();

            String selectedPlace1 = Places1.getValue();
            String selectedPlace2 = Places2.getValue();
            String selectedVehicleName = SelectedVehicles.getValue();
            String selectedRouteDistance = getSelectedRouteDistance(selectedPlace1, selectedPlace2);
            Vehicle selectedVehicleObject = vehicleService.getSelectedVehicle(selectedVehicleName);
            updateGasLabel(selectedVehicleObject, selectedPlace1, selectedPlace2);

            if (selectedRouteDistance != null) {
                tripController.setKM1(selectedRouteDistance);
            } else {
                System.out.println("No se encontró la ruta seleccionada.");
            }

            tripController.setBeg1(selectedPlace1);
            tripController.setFinal1(selectedPlace2);
            tripController.setNameV1(selectedVehicleName);

            Image vehicleImage = vehicleService.getVehicleImage(selectedVehicleName);
            tripController.setImage1(vehicleImage);

            generateTripTabContent = root;
            generateTripTab.setContent(generateTripTabContent); // Actualizar el contenido de la pestaña
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onGenerateAnewTripPressed() {
        pressCount++;
        if (pressCount <= 3) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Trip.fxml"));
                Parent root;

                // Si es la primera vez que se presiona el botón, cargar Trip.fxml y crear una nueva pestaña
                if (pressCount == 1) {
                    root = loader.load();
                    tripController = loader.getController();

                    // Crear una nueva pestaña y establecer su contenido
                    Tab newTripTab = new Tab("Nuevo Viaje");
                    newTripTab.setContent(root);

                    // Agregar la nueva pestaña al TabPane
                    mainWindowsController.getTabPane().getTabs().add(newTripTab);
                }
                // Si no es la primera vez que se presiona el botón, obtener el controlador de la pestaña existente
                else {
                    Tab existingTab = mainWindowsController.getTabPane().getTabs().get(0); // Asume que la pestaña de viaje es la primera pestaña
                    root = (Parent) existingTab.getContent();
                }

                String selectedPlace1 = Places1.getValue();
                String selectedPlace2 = Places2.getValue();
                String selectedVehicle = SelectedVehicles.getValue();
                if (selectedVehicle != null) {
                    SelectedVehicles.getItems().remove(selectedVehicle);
                }
                String selectedRouteDistance = getSelectedRouteDistance(selectedPlace1, selectedPlace2);

                Image vehicleImage = vehicleService.getVehicleImage(selectedVehicle);

                // Llenar los campos correspondientes en TripController dependiendo del valor de pressCount
                switch (pressCount) {
                    case 1:
                        tripController.setBeg1(selectedPlace1);
                        tripController.setFinal1(selectedPlace2);
                        tripController.setKM1(selectedRouteDistance);
                        tripController.setNameV1(selectedVehicle);
                        tripController.setImage1(vehicleImage);
                        break;
                    case 2:
                        tripController.setBeg2(selectedPlace1);
                        tripController.setFinal2(selectedPlace2);
                        tripController.setKM2(selectedRouteDistance);
                        tripController.setNameV2(selectedVehicle);
                        tripController.setImage2(vehicleImage);
                        break;
                    case 3:
                        tripController.setBeg3(selectedPlace1);
                        tripController.setFinal3(selectedPlace2);
                        tripController.setKM3(selectedRouteDistance);
                        tripController.setNameV3(selectedVehicle);
                        tripController.setImage3(vehicleImage);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            GenerateAnewTrip.setDisable(true);
            try {
                // Cargar GenerateTripAlert.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Panels/GenerateTripAlert.fxml"));
                Parent alertRoot = loader.load();

                Scene scene = new Scene(alertRoot);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setGenerateTripTabContent(Node generateTripTabContent) {
        this.generateTripTabContent = generateTripTabContent;
    }

    public double updateGasLabel(Vehicle vehicle, String selectedPlace1, String selectedPlace2) {
        System.out.println("Entrando a updateGasLabel");
        String selectedRouteDistance = getSelectedRouteDistance(selectedPlace1, selectedPlace2);
        double km1 = Double.parseDouble(selectedRouteDistance); // Convertir la distancia de la ruta seleccionada a double
        double gasRemaining = vehicle.getGasAmount() - (km1 / vehicle.getGasConsumption());
        System.out.println("Gasolina restante calculada: " + gasRemaining);
        tripController.setGas1Text(String.format("%.2f", gasRemaining));
        return gasRemaining;
    }

}