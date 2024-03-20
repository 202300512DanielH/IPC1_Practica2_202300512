package Panels;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadRoutesBisectionController {

    @FXML
    private Button AplyChanges;

    @FXML
    private Button CloseBisection;

    @FXML
    private TextField IDCheck;

    @FXML
    private TextField NewDistance;

    private LoadRoutesController loadRoutesController;

    public void setLoadRoutesController(LoadRoutesController loadRoutesController) {
        this.loadRoutesController = loadRoutesController;
    }

    @FXML
    public void applyChanges(ActionEvent event) {
        String id = IDCheck.getText();
        String newDistance = NewDistance.getText();

        ObservableList<Route> routes = loadRoutesController.getAllRoutes();

        Boolean idExists = routes.stream().anyMatch(route -> route.getId().equals(id));

        if (idExists) {
            loadRoutesController.updateRoute(id, newDistance);
            Stage currentStage = (Stage) AplyChanges.getScene().getWindow();
            currentStage.close();
        } else {
            try {
                // Show LoadRoutesAlert.fxml
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Panels/LoadRoutesAlert.fxml"));
                Parent alertRoot = fxmlLoader.load();

                Stage alertStage = new Stage();
                alertStage.setTitle("Route Alert");
                alertStage.setScene(new Scene(alertRoot));
                alertStage.show();

            } catch (IOException e) {
                System.err.println("Failed to load LoadRoutesAlert.fxml file.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void closeBisection(ActionEvent event) {
        // consigue la ventana (Stage) actual a partir del botón
        Stage currentStage = (Stage) CloseBisection.getScene().getWindow();
        // cierra la ventana
        currentStage.close();
    }

}
