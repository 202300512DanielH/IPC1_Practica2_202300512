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

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;
import java.time.LocalDateTime;

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

    private int km1Int;
    private int km2Int;
    private int km3Int;

    private double initialPosition1;
    private double totalDistanceTraveled1 = 0;

    private double initialPosition2;
    private double totalDistanceTraveled2 = 0;

    private double initialPosition3;
    private double totalDistanceTraveled3 = 0;




    private double totalGasAmount2 = 0;
    private double initialGasAmount2 = 0;
    private double gasCCalculate2 = 0;
    private double totalGasAmount3 = 0;
    private double initialGasAmount3 = 0;
    private double gasCCalculate3 = 0;


    public void setBeg1(String beg1) {
        Beg1.setText(beg1);
    }

    public void setFinal1(String final1) {
        Final1.setText(final1);
    }

    public void setKM1(String km1) {
        km1Int = Integer.parseInt(km1);
        KM1.setText(km1);
    }
    public void setNameV1(String nameV1) {
        this.nameV1.setText(nameV1);
        initialGasAmount1 = vehicleService.getGasAmount(nameV1);
    }

    public void setImage1(Image image1) {Image1.setImage(image1);}

    public void setGas1Text(String text) {
        System.out.println("Entrando a setGas1Text");
        Gas1.setText(text);
        System.out.println("Estableciendo el valor de Gas1 a: " + text);
    }




    public void setBeg2(String beg2) {
        Beg2.setText(beg2);
    }

    public void setFinal2(String final2) {
        Final2.setText(final2);
    }

    public void setKM2(String km2) {
        km2Int = Integer.parseInt(km2);
        KM2.setText(km2);
    }

    public void setNameV2(String nameV2) {
        this.nameV2.setText(nameV2);
        initialGasAmount2= vehicleService.getGasAmount(nameV2);
    }

    public void setImage2(Image image2) {Image2.setImage(image2);}



    public void setBeg3(String beg3) {
        Beg3.setText(beg3);
    }

    public void setFinal3(String final3) {
        Final3.setText(final3);
    }

    public void setKM3(String km3) {
        km3Int = Integer.parseInt(km3);
        KM3.setText(km3);
    }
    public void setNameV3(String nameV3) {

        this.nameV3.setText(nameV3);
        initialGasAmount3= vehicleService.getGasAmount(nameV3);
    }

    public void setImage3(Image image3) {
        Image3.setImage(image3);
    }


    private VehicleService vehicleService;
    private GenerateTripController generateTripController;
    private String selectedPlace1;
    private String selectedPlace2;

    public TripController() {
        this.vehicleService = new VehicleService();
        this.generateTripController = new GenerateTripController();
    }

    public TripController(VehicleService vehicleService, GenerateTripController generateTripController, String selectedPlace1, String selectedPlace2) {
        this.vehicleService = vehicleService;
        this.generateTripController = generateTripController;
        this.selectedPlace1 = selectedPlace1;
        this.selectedPlace2 = selectedPlace2;
    }

    private double totalGasAmount1 = 0;
    private double initialGasAmount1 = 0;
    private double gasCCalculate1 = 0;

    private TranslateTransition transition1 = new TranslateTransition();
    private Timeline timeline1 = new Timeline();
    private Duration remainingDuration1;

    @FXML
    public void initialize() {
        GoBack1.setVisible(false);
        GoBack2.setVisible(false);
        GoBack3.setVisible(false);

        Rech1.setVisible(false);
        Rech2.setVisible(false);
        Rech3.setVisible(false);

        Start1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDateTime startTime = LocalDateTime.now();
                // Reset initial position when start
                initialPosition1 = Image1.getTranslateX();

                transition1.setNode(Image1);
                remainingDuration1 = Duration.seconds(km1Int);
                transition1.setDuration(remainingDuration1); // Set the duration to be the remaining duration
                transition1.setToX(Image1.getTranslateX() - 286.0000458); // Desplace 286.0000458 px a la izquierda

                double kmPerPixel = km1Int / 286.0000458;

                timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> {
                    double localDistanceTraveled =
                            Math.abs((Image1.getTranslateX() - initialPosition1) * kmPerPixel);
                    DistTrav1.setText(String.format("%.2f", totalDistanceTraveled1 + localDistanceTraveled));

                    String vehicleName = nameV1.getText();
                    double gasConsumption = vehicleService.getGasConsumption(vehicleName);
                    gasCCalculate1= localDistanceTraveled * gasConsumption;
                    totalGasAmount1 = initialGasAmount1- localDistanceTraveled * gasConsumption;
                    Gas1.setText(String.format("%.2f", totalGasAmount1));

                    if (totalGasAmount1 <= 0) {
                        Gas1.setText("Sin gasolina");
                        transition1.stop();
                        timeline1.stop();
                        Rech1.setVisible(true);
                    }

                }));
                timeline1.setCycleCount(Animation.INDEFINITE);

                transition1.play();
                timeline1.play();

                transition1.setOnFinished(e -> {
                    LocalDateTime endTime = LocalDateTime.now();
                    timeline1.stop();
                    totalDistanceTraveled1 += km1Int;
                    GoBack1.setVisible(true);

                    totalGasAmount1=initialGasAmount1- gasCCalculate1;
                    initialGasAmount1=initialGasAmount1- gasCCalculate1;
                    remainingDuration1 = null;
                    RecordController.setRecord(startTime, endTime, km1Int, nameV1.getText(), gasCCalculate1, "Ida");

                });
            }
        });
        GoBack1.setOnAction(this::handleGoBack1Action);

        Start2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDateTime startTime = LocalDateTime.now();
                // Reset initial position when start
                initialPosition2 = Image2.getTranslateX();

                TranslateTransition transition = new TranslateTransition();
                transition.setNode(Image2);
                transition.setDuration(Duration.seconds(km2Int)); // Tiempo de animación
                transition.setToX(Image2.getTranslateX() - 286.0000458); // Desplazar 286.0000458 px a la izquierda

                double kmPerPixel = km2Int / 286.0000458;

                Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> {
                    double localDistanceTraveled =
                            Math.abs((Image2.getTranslateX() - initialPosition2) * kmPerPixel);
                    DistTrav2.setText(String.format("%.2f", totalDistanceTraveled2 + localDistanceTraveled));

                    String vehicleName = nameV2.getText();
                    double gasConsumption = vehicleService.getGasConsumption(vehicleName);
                    gasCCalculate2= localDistanceTraveled * gasConsumption;
                    totalGasAmount2 = initialGasAmount2- localDistanceTraveled * gasConsumption;
                    Gas2.setText(String.format("%.2f", totalGasAmount2));

                    if (totalGasAmount2 <= 0) {
                        Gas2.setText("Sin gasolina");
                        transition.stop();
                        timeline.stop();
                        Rech2.setVisible(true);
                    }
                }));
                timeline.setCycleCount(Animation.INDEFINITE);

                transition.play();
                timeline.play();

                transition.setOnFinished(e -> {
                    LocalDateTime endTime = LocalDateTime.now();
                    timeline.stop();
                    totalDistanceTraveled2 += km2Int;
                    GoBack2.setVisible(true);

                    totalGasAmount2=initialGasAmount2- gasCCalculate2;
                    initialGasAmount2=initialGasAmount2- gasCCalculate2;
                    RecordController.setRecord(startTime, endTime, km2Int, nameV2.getText(), gasCCalculate2,"Ida");
                });
            }
        });
        GoBack2.setOnAction(this::handleGoBack2Action);


        Start3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDateTime startTime = LocalDateTime.now();
                // Reset initial position when start
                initialPosition3 = Image3.getTranslateX();

                TranslateTransition transition = new TranslateTransition();
                transition.setNode(Image3);
                transition.setDuration(Duration.seconds(km3Int)); // Tiempo de animación
                transition.setToX(Image3.getTranslateX() - 286.0000458); // Desplazar 286.0000458 px a la izquierda

                double kmPerPixel = km3Int / 286.0000458;

                Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> {
                    double localDistanceTraveled =
                            Math.abs((Image3.getTranslateX() - initialPosition3) * kmPerPixel);
                    DistTrav3.setText(String.format("%.2f", totalDistanceTraveled3 + localDistanceTraveled));

                    String vehicleName = nameV3.getText();
                    double gasConsumption = vehicleService.getGasConsumption(vehicleName);
                    gasCCalculate3= localDistanceTraveled * gasConsumption;
                    totalGasAmount3 = initialGasAmount3- localDistanceTraveled * gasConsumption;
                    Gas3.setText(String.format("%.2f", totalGasAmount3));

                    if (totalGasAmount3 <= 0) {
                        Gas3.setText("Sin gasolina");
                        transition.stop();
                        timeline.stop();
                        Rech3.setVisible(true);
                    }
                }));
                timeline.setCycleCount(Animation.INDEFINITE);

                transition.play();
                timeline.play();

                transition.setOnFinished(e -> {
                    LocalDateTime endTime = LocalDateTime.now();
                    timeline.stop();
                    totalDistanceTraveled3 += km3Int;
                    GoBack3.setVisible(true);

                    totalGasAmount3=initialGasAmount3- gasCCalculate3;
                    initialGasAmount3=initialGasAmount3- gasCCalculate3;
                    RecordController.setRecord(startTime, endTime, km3Int, nameV3.getText(), gasCCalculate3,"Ida");
                });
            }
        });
        GoBack3.setOnAction(this::handleGoBack3Action);

        StartAll.setOnAction(event -> {
            Start1.fire();
            Start2.fire();
            Start3.fire();
        });
    }


    @FXML
    private void handleGoBack1Action(ActionEvent event) {
        LocalDateTime startTime = LocalDateTime.now();
        Image1.setScaleX(-1);  // Reflejo de la imagen

        // Reset initial position when go back
        initialPosition1 = Image1.getTranslateX();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(km1Int), Image1);
        translateTransition.setToX(Image1.getTranslateX() + 286.0000458);  // Mover la imagen a la derecha

        double kmPerPixel = km1Int / 286.0000458;

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> {
            double localDistanceTraveled =
                    Math.abs((Image1.getTranslateX() - initialPosition1) * kmPerPixel);
            DistTrav1.setText(String.format("%.2f", totalDistanceTraveled1 + localDistanceTraveled));

            String vehicleName = nameV1.getText();
            double gasConsumption = vehicleService.getGasConsumption(vehicleName);
            totalGasAmount1 = initialGasAmount1 - localDistanceTraveled * gasConsumption;
            Gas1.setText(String.format("%.2f", totalGasAmount1));

            if (totalGasAmount1 <= 0) {
                translateTransition.stop();
                timeline.stop();
                Rech1.setVisible(true);
            }


        }));
        timeline.setCycleCount(Animation.INDEFINITE);

        translateTransition.play();
        timeline.play();

        translateTransition.setOnFinished(e -> {
            LocalDateTime endTime = LocalDateTime.now();
            timeline.stop();
            GoBack1.setVisible(false);
            Image1.setScaleX(1);
            totalDistanceTraveled1 += km1Int;
            totalGasAmount1=initialGasAmount1- gasCCalculate1;
            initialGasAmount1=initialGasAmount1- gasCCalculate1;
            RecordController.setRecord(startTime, endTime, km1Int, nameV1.getText(), gasCCalculate1, "Regreso");
        });
    }
    @FXML
    private void handleGoBack2Action(ActionEvent event) {
        LocalDateTime startTime = LocalDateTime.now();
        Image2.setScaleX(-1);

        initialPosition2 = Image2.getTranslateX();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(km2Int), Image2);
        translateTransition.setToX(Image2.getTranslateX() + 286.0000458);  // Mover la imagen a la derecha

        double kmPerPixel = km2Int / 286.0000458;

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> {
            double localDistanceTraveled =
                    Math.abs((Image2.getTranslateX() - initialPosition2) * kmPerPixel);
            DistTrav2.setText(String.format("%.2f", totalDistanceTraveled2 + localDistanceTraveled));

            String vehicleName = nameV2.getText();
            double gasConsumption = vehicleService.getGasConsumption(vehicleName);
            totalGasAmount2 = initialGasAmount2 - localDistanceTraveled * gasConsumption;
            Gas2.setText(String.format("%.2f", totalGasAmount2));

            if (totalGasAmount2 <= 0) {
                translateTransition.stop();
                timeline.stop();
                Rech2.setVisible(true);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

        translateTransition.play();
        timeline.play();

        translateTransition.setOnFinished(e -> {
            LocalDateTime endTime = LocalDateTime.now();
            timeline.stop();
            GoBack2.setVisible(false);
            Image2.setScaleX(1);
            totalDistanceTraveled2 += km2Int;
            totalGasAmount2=initialGasAmount2- gasCCalculate2;
            initialGasAmount2=initialGasAmount2- gasCCalculate2;
            RecordController.setRecord(startTime, endTime, km2Int, nameV2.getText(), gasCCalculate2, "Regreso");
        });
    }
    @FXML
    private void handleGoBack3Action(ActionEvent event) {
        LocalDateTime startTime = LocalDateTime.now();
        Image3.setScaleX(-1);  // Reflejo de la imagen

        // Reset initial position when go back
        initialPosition3 = Image3.getTranslateX();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(km3Int), Image3);
        translateTransition.setToX(Image3.getTranslateX() + 286.0000458);  // Mover la imagen a la derecha

        double kmPerPixel = km3Int / 286.0000458;

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> {
            double localDistanceTraveled =
                    Math.abs((Image3.getTranslateX() - initialPosition3) * kmPerPixel);
            DistTrav3.setText(String.format("%.2f", totalDistanceTraveled3 + localDistanceTraveled));

            String vehicleName = nameV3.getText();
            double gasConsumption = vehicleService.getGasConsumption(vehicleName);
            totalGasAmount3 = initialGasAmount3 - localDistanceTraveled * gasConsumption;
            Gas3.setText(String.format("%.2f", totalGasAmount3));

            if (totalGasAmount3 <= 0) {
                translateTransition.stop();
                timeline.stop();
                Rech3.setVisible(true);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

        translateTransition.play();
        timeline.play();

        translateTransition.setOnFinished(e -> {
            LocalDateTime endTime = LocalDateTime.now();
            timeline.stop();
            GoBack3.setVisible(false);
            Image3.setScaleX(1);
            totalDistanceTraveled3 += km3Int;
            totalGasAmount3=initialGasAmount3- gasCCalculate3;
            initialGasAmount3=initialGasAmount3- gasCCalculate3;
            RecordController.setRecord(startTime, endTime, km3Int, nameV3.getText(), gasCCalculate3, "Regreso");
        });
    }

}
