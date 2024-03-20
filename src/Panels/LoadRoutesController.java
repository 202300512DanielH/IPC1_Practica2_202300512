package Panels;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import javafx.stage.FileChooser;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LoadRoutesController {
    @FXML
    private Button EditDistanceButton;
    @FXML
    private Button LoadRoutesButton;
    @FXML
    private TableView<Route> ShowInformation;

    // Nuevos campos
    @FXML
    private TableColumn<Route, String> colID;
    @FXML
    private TableColumn<Route, String> colStart;
    @FXML
    private TableColumn<Route, String> colEnd;
    @FXML
    private TableColumn<Route, String> colDistance;

    @FXML
    public void initialize() {
        // Configuración de los cellValueFactory para las columnas
        this.colID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        this.colStart.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        this.colEnd.setCellValueFactory(cellData -> cellData.getValue().endProperty());
        this.colDistance.setCellValueFactory(cellData -> cellData.getValue().distanceProperty());
    }

    @FXML
    private void loadRoutes(ActionEvent event) throws IOException {
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
            stage.setTitle("Load Routes Bisection");
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