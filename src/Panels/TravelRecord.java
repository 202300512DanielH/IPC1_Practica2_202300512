package Panels;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class TravelRecord implements Serializable {
    private Integer id;
    private String color;
    private SimpleStringProperty startTime;
    private SimpleStringProperty endTime;
    private SimpleIntegerProperty distance;
    private SimpleStringProperty vehicleName;
    private SimpleDoubleProperty fuelConsumed;

    public TravelRecord(LocalDateTime start, LocalDateTime end, int km1, String nameV1, double gasCCalculate1) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.startTime = new SimpleStringProperty(start.format(formatter));
        this.endTime = new SimpleStringProperty(end.format(formatter));
        this.distance = new SimpleIntegerProperty(km1);
        this.vehicleName = new SimpleStringProperty(nameV1);
        this.fuelConsumed = new SimpleDoubleProperty(gasCCalculate1);
    }

    public String getStartTime() {
        return startTime.get();
    }

    public String getEndTime() {
        return endTime.get();
    }

    public int getDistance() {
        return distance.get();
    }

    public String getVehicleName() {
        return vehicleName.get();
    }

    public double getFuelConsumed() {
        return fuelConsumed.get();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
