package Panels;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import java.io.IOException;
import java.util.List;

public class MainWindowController implements TabOpener {

    @FXML
    private TabPane mainTabPane;

    @FXML
    public void initialize() {
        addTab("SecondWindow.fxml", "Second Window", null);
    }

    private void addTab(String fxmlFile, String tabTitle, List<String> places) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (fxmlFile.equals("NewTrip.fxml")) {
            NewTripController newTripController = (NewTripController) loader.getController();
            newTripController.setPlaces(places);
        }

        if (loader.getController() instanceof SecondWindow) {
            SecondWindow secondWindow = (SecondWindow) loader.getController();
            secondWindow.setTabOpener(this);
        }

        Tab newTab = new Tab(tabTitle);
        newTab.setContent(root);
        mainTabPane.getTabs().add(newTab);
    }

    @Override
    public void openTab(String fxmlFile, String tabTitle, List<String> places) {
        addTab(fxmlFile, tabTitle, places);
    }
}