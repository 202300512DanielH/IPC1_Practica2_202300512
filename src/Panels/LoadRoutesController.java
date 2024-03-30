package Panels;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

import javafx.stage.FileChooser;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LoadRoutesController implements Initializable {

    private MainWindowsController mainWindowsController;

    @FXML
    private Button EditDistanceButton;
    @FXML
    private Button LoadRoutesButton;
    @FXML
    private TableView<Route> ShowInformation;
    @FXML
    private TableColumn<Route, String> colID;
    @FXML
    private TableColumn<Route, String> colStart;
    @FXML
    private TableColumn<Route, String> colEnd;
    @FXML
    private TableColumn<Route, String> colDistance;

    public LoadRoutesController() {
        // Constructor sin argumentos
    }

    public void setMainWindowsController(MainWindowsController mainWindowsController) {
        this.mainWindowsController = mainWindowsController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.colID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        this.colStart.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        this.colEnd.setCellValueFactory(cellData -> cellData.getValue().endProperty());
        this.colDistance.setCellValueFactory(cellData -> cellData.getValue().distanceProperty());
    }

    @FXML
    private void loadRoutes(ActionEvent event) throws IOException {
        // Comprueba si mainWindowsController es null y, si es así, crea una nueva instancia
        if (mainWindowsController == null) {
            mainWindowsController = new MainWindowsController();
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV files", "*.csv"));
        File csvFile = fileChooser.showOpenDialog(null);

        if (csvFile != null) {
            Path path = csvFile.toPath();

            try (BufferedReader reader = Files.newBufferedReader(path);
                 ObjectOutputStream out = new ObjectOutputStream(
                         new FileOutputStream("routes.dat")))
            {
                String line;
                int id = 1;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    String startLocation = data[0]; // assuming this is start
                    String endLocation = data[1]; // assuming this is end
                    String distance = data[2]; // assuming this is distance

                    Route route = new Route(Integer.toString(id++),
                            startLocation, endLocation, distance);

                    out.writeObject(route);
                    ShowInformation.getItems().add(route);
                }
                mainWindowsController.getGenerateTripController().fillComboBoxes(ShowInformation.getItems());
            }
        }
    }

    @FXML
    public void openLoadRoutesBisection(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Panels/LoadRoutesBisection.fxml"));
            Parent root = fxmlLoader.load();

            LoadRoutesBisectionController loadRoutesBisectionController = fxmlLoader.getController();
            loadRoutesBisectionController.setLoadRoutesController(this);

            Stage stage = new Stage();
            stage.setTitle("Editar Distancia de Rutas");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load LoadRoutesBisection.fxml file.");
            e.printStackTrace();
        }
    }
    public void updateRoute(String id, String newDistance) {
        for (Route route: ShowInformation.getItems()) {
            if (route.getId().equals(id)) {
                route.setDistance(newDistance);
                break;
            }
        }
    }


    public ObservableList<Route> getAllRoutes() {
        return ShowInformation.getItems();
    }


}