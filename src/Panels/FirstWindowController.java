package Panels;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstWindowController {

    @FXML
    private Button Inibutton;

    @FXML
    private void handleButtonAction(javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene mainWindowScene = new Scene(FXMLLoader.load(getClass().getResource("MainWindows.fxml")));
        stage.setScene(mainWindowScene);
    }
}
