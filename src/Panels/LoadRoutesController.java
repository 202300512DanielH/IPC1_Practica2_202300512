package Panels;

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
}