package Panels;

import java.io.Serializable;

public class TravelRecordData implements Serializable {
    private Integer id;
    private String color;
    private String startTime;
    private String endTime;
    private Integer distance;
    private String vehicleName;
    private Double fuelConsumed;

    public TravelRecordData(TravelRecord record) {
        this.id = record.getId();
        this.color = record.getColor();
        this.startTime = record.getStartTime();
        this.endTime = record.getEndTime();
        this.distance = record.getDistance();
        this.vehicleName = record.getVehicleName();
        this.fuelConsumed = record.getFuelConsumed();
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getDistance() {
        return distance;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public double getFuelConsumed() {
        return fuelConsumed;
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
