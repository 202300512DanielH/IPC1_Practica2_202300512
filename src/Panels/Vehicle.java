package Panels;
import javafx.scene.image.Image;

public class Vehicle {
    private String vehicleName;
    private Image image;
    private double gasAmount;
    private double gasConsumption;
    private double distanceTraveled;

    public Vehicle(String vehicleName, Image image, double gasAmount, double gasConsumption, double distanceTraveled) {
        this.vehicleName = vehicleName;
        this.image = image;
        this.gasAmount = gasAmount;
        this.gasConsumption = gasConsumption;
        this.distanceTraveled = distanceTraveled;
    }

    // Getters and setters for each attribute

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getGasAmount() {
        return gasAmount;
    }

    public void setGasAmount(double gasAmount) {
        this.gasAmount = gasAmount;
    }

    public double getGasConsumption() {
        return gasConsumption;
    }

    public void setGasConsumption(double gasConsumption) {
        this.gasConsumption = gasConsumption;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }
}