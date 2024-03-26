package Panels;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class TripController {
    @FXML
    private Button GoBack1;

    @FXML
    private Button GoBack2;

    @FXML
    private Button GoBack3;

    @FXML
    private Button StartAll;

    @FXML
    private Label Beg1;

    @FXML
    private Label Beg2;

    @FXML
    private Label Beg3;

    @FXML
    private Label DistTrav1;

    @FXML
    private Label DistTrav2;

    @FXML
    private Label DistTrav3;

    @FXML
    private Label Final1;

    @FXML
    private Label Final2;

    @FXML
    private Label Final3;

    @FXML
    private Label Gas1;

    @FXML
    private Label Gas2;

    @FXML
    private Label Gas3;

    @FXML
    private ImageView Image1;

    @FXML
    private ImageView Image2;

    @FXML
    private ImageView Image3;

    @FXML
    private Label KM1;

    @FXML
    private Label KM2;

    @FXML
    private Label KM3;

    @FXML
    private Button Rech1;

    @FXML
    private Button Rech2;

    @FXML
    private Button Rech3;

    @FXML
    private Button Start1;

    @FXML
    private Button Start2;

    @FXML
    private Button Start3;

    @FXML
    private Label nameV1;

    @FXML
    private Label nameV2;

    @FXML
    private Label nameV3;

    public void setBeg1(String beg1) {
        Beg1.setText(beg1);
    }

    public void setFinal1(String final1) {
        Final1.setText(final1);
    }

    public void setKM1(String km1) {
        KM1.setText(km1);
    }

    public void setNameV1(String nameV1) {
        this.nameV1.setText(nameV1);
    }

    public void setImage1(Image image1) {Image1.setImage(image1);}




    public void setBeg2(String beg2) {
        Beg2.setText(beg2);
    }

    public void setFinal2(String final2) {
        Final2.setText(final2);
    }

    public void setKM2(String km2) {
        KM2.setText(km2);
    }

    public void setNameV2(String nameV2) {
        this.nameV2.setText(nameV2);
    }

    public void setImage2(Image image2) {Image2.setImage(image2);}



    public void setBeg3(String beg3) {
        Beg3.setText(beg3);
    }

    public void setFinal3(String final3) {
        Final3.setText(final3);
    }

    public void setKM3(String km3) {
        KM3.setText(km3);
    }

    public void setNameV3(String nameV3) {
        this.nameV3.setText(nameV3);
    }

    public void setImage3(Image image3) {
        Image3.setImage(image3);
    }
    @FXML
    public void initialize() {
        GoBack1.setVisible(false);
        GoBack2.setVisible(false);
        GoBack3.setVisible(false);

        Rech1.setVisible(false);
        Rech1.setVisible(false);
        Rech1.setVisible(false);

        Start1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(Image1);
                transition.setDuration(Duration.seconds(4)); // Tiempo de animación
                transition.setToX(Image1.getTranslateX() - 286.0000458); // Desplazar 286.0000458 px a la izquierda
                transition.setOnFinished(e -> GoBack1.setVisible(true));
                transition.play();
            }
        });

        Start2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(Image2);
                transition.setDuration(Duration.seconds(4)); // Tiempo de animación
                transition.setToX(Image2.getTranslateX() - 286.0000458); // Desplazar 286.0000458 px a la izquierda

                transition.play();
            }
        });

        GoBack1.setOnAction(this::handleGoBack1Action);
        Start3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(Image3);
                transition.setDuration(Duration.seconds(4)); // Tiempo de animación
                transition.setToX(Image3.getTranslateX() - 286.0000458); // Desplazar 286.0000458 px a la izquierda

                transition.play();
            }
        });

        StartAll.setOnAction(event -> {
            startTransition(Image1).handle(event);
            startTransition(Image2).handle(event);
            startTransition(Image3).handle(event);
        });

    }

    private EventHandler<ActionEvent> startTransition(ImageView image) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(image);
                transition.setDuration(Duration.seconds(4)); // Duración de la animación
                transition.setToX(image.getTranslateX() - 286.0000458); // Desplazamiento de la imagen
                transition.play();
            }
        };
    }

    @FXML
    private void handleGoBack1Action(ActionEvent event) {
        Image1.setScaleX(-1);  // Reflejo de la imagen
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(4), Image1);
        translateTransition.setToX(Image1.getTranslateX() + 286.0000458);  // Mover la imagen a la derecha
        translateTransition.play();

        // Cuando la animación de retroceso ha terminado, oculte el botón GoBack1 y vuelva a su orientación original
        translateTransition.setOnFinished(e -> {
            GoBack1.setVisible(false);
            Image1.setScaleX(1);
        });
    }

}
