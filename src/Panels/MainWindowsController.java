package Panels;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainWindowsController {

    @FXML
    private LoadRoutesController loadRoutesController;
    @FXML
    private GenerateTripController generateTripController;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab generateTripTab;

    @FXML
    public void initialize() {
        // Establecer MainWindowsController para LoadRoutesController y GenerateTripController
        if (loadRoutesController != null) {
            loadRoutesController.setMainWindowsController(this);
        }

        if (generateTripController != null) {
            generateTripController.setMainWindowsController(this);
        }

        // Asegurarse de que postInitialize() se llame después de que FXMLLoader haya terminado de inyectar todos los campos @FXML
        Platform.runLater(this::postInitialize);
    }

    public void postInitialize() {
        if (generateTripController != null && tabPane != null && generateTripTab != null) {
            generateTripController.setTabPane(tabPane);
            generateTripController.setGenerateTripTab(generateTripTab);
            generateTripController.setGenerateTripTabContent(generateTripTab.getContent());
        }
    }

    public LoadRoutesController getLoadRoutesController() {
        return loadRoutesController;
    }

    public void setLoadRoutesController(LoadRoutesController loadRoutesController) {
        this.loadRoutesController = loadRoutesController;
    }

    public void setGenerateTripController(GenerateTripController generateTripController) {
        this.generateTripController = generateTripController;
    }

    public GenerateTripController getGenerateTripController() {
        return generateTripController;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public Tab getGenerateTripTab() {
        return generateTripTab;
    }
}
