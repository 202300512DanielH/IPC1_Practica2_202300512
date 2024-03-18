package Panels;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

public class EditDistanceController {
    @FXML
    private Button Cancel;

    @FXML
    private TextField IDget;

    @FXML
    private Button OkButton;

    @FXML
    private TextField newDistance;

    private List<Route> routes;

    private List<Trip> trips;

    public void initData(List<Trip> existingTrips) {
        this.trips = new ArrayList<>(existingTrips);
    }

    @FXML
    public void initialize() {
        Cancel.setOnAction(event -> ((Stage)Cancel.getScene().getWindow()).close());

        OkButton.setOnAction(event -> {
            boolean idFound = false;
            int id = Integer.parseInt(IDget.getText());
            int distance = Integer.parseInt(newDistance.getText());

            System.out.println("Before editing:");
            for (Trip trip : trips) {
                System.out.println("ID: " + trip.getId() + ", Distance: " + trip.getDistance());
            }


            for (Trip trip : trips) {
                if (trip.getId() == id) {
                    trip.setDistance(distance);
                    idFound = true;

                    break;
                }
            }
            System.out.println("After editing:");
            for (Trip trip : trips) {
                System.out.println("ID: " + trip.getId() + ", Distance: " + trip.getDistance());
            }


            if(!idFound) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IDnoFound.fxml"));
                    Parent parent = fxmlLoader.load();

                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.setTitle("ID Not Found");
                    stage.showAndWait();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(idFound) { // Esta línea debería estar aquí, se cierra la ventana sólo si se encontró el ID.
                ((Stage)OkButton.getScene().getWindow()).close();
            }
        });
    }





}