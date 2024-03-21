package Panels;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstWindowController {

    @FXML
    private Button Inibutton;

    private MainWindowsController mainWindowsController; // Declarar el controlador

    public void setMainWindowsController(MainWindowsController mainWindowsController) {
        this.mainWindowsController = mainWindowsController;
    }

    @FXML
    private void handleButtonAction(javafx.event.ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Panels/MainWindows.fxml"));

        // Comprueba si mainWindowsController es null y, si es así, crea una nueva instancia
        if (mainWindowsController == null) {
            mainWindowsController = new MainWindowsController();
        }

        fxmlLoader.setController(mainWindowsController); // Establecer el controlador

        Scene mainWindowScene = new Scene(fxmlLoader.load());
        stage.setScene(mainWindowScene);

        // Inicializar el controlador manualmente
        mainWindowsController.initialize();

    }
}
