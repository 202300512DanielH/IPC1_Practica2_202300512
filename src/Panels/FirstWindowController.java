package Panels;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class FirstWindowController {

    @FXML
    private Button Inibutton;

    @FXML
    private void handleButtonAction() {
        try {
            // Close the current (first) window
            Stage currentStage = (Stage) Inibutton.getScene().getWindow();
            currentStage.close();
            // Load the fxml file of SecondWindow
            Parent secondWindow = FXMLLoader.load(getClass().getResource("SecondWindow.fxml"));

            // Create a new stage
            Stage newStage = new Stage();

            // Set the scene to the stage
            newStage.setScene(new Scene(secondWindow));

            // Show the new stage/window
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
