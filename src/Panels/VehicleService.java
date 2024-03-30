package Panels;

import javafx.scene.image.Image;

public class VehicleService {
    private Vehicle[] vehicles;

    public VehicleService() {
        this.vehicles = createVehicles();
    }

    public Vehicle[] createVehicles() {
        Vehicle[] vehicles = new Vehicle[9];

        vehicles[0] = new Vehicle("Motocicleta 1", new Image("/Images/Moto.png"), 6, 0.1, 0);
        vehicles[1] = new Vehicle("Motocicleta 2", new Image("/Images/Moto.png"), 6, 0.1, 0);
        vehicles[2] = new Vehicle("Motocicleta 3", new Image("/Images/Moto.png"), 6, 0.1, 0);
        vehicles[3] = new Vehicle("Carro Estándar 1", new Image("/Images/Estándar.png"), 10, 0.3, 0);
        vehicles[4] = new Vehicle("Carro Estándar 2", new Image("/Images/Estándar.png"), 10, 0.3, 0);
        vehicles[5] = new Vehicle("Carro Estándar 3", new Image("/Images/Estándar.png"), 10, 0.3, 0);
        vehicles[6] = new Vehicle("Carro Premium 1", new Image("/Images/Premium.png"), 12, 0.45, 0);
        vehicles[7] = new Vehicle("Carro Premium 2", new Image("/Images/Premium.png"), 12, 0.45, 0);
        vehicles[8] = new Vehicle("Carro Premium 3", new Image("/Images/Premium.png"), 12, 0.45, 0);
        return vehicles;
    }

    public Image getVehicleImage(String vehicleName) {
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle.getVehicleName().equals(vehicleName)) {
                return vehicle.getImage();
            }
        }
        return null;
    }

    public Vehicle getSelectedVehicle(String vehicleName) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleName().equals(vehicleName)) {
                return vehicle;
            }
        }
        return null;
    }

    public double getGasAmount(String vehicleName) {
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle.getVehicleName().equals(vehicleName)) {
                return vehicle.getGasAmount();
            }
        }
        return -1;
    }

    public double getGasConsumption(String vehicleName) {
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle.getVehicleName().equals(vehicleName)) {
                return vehicle.getGasConsumption();
            }
        }
        return -1;
    }
}
