package Panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RecordController {
    private static final String RECORDS_FILE = "records.bin";
    private static int idCounter = 1;
    @FXML
    private TableColumn<TravelRecord, Integer> idColumn;

    @FXML
    private TableColumn<TravelRecord, String> colorColumn;
    @FXML
    private TableColumn<TravelRecord, String> startTimeColumn;

    @FXML
    private TableColumn<TravelRecord, String> endTimeColumn;

    @FXML
    private TableColumn<TravelRecord, Number> distanceColumn;

    @FXML
    private TableColumn<TravelRecord, String> vehicleColumn;

    @FXML
    private TableColumn<TravelRecord, Number> fuelConsumedColumn;

    @FXML
    private TableView<TravelRecord> table;

    private static ObservableList<TravelRecord> travelRecords;

    @FXML
    public void initialize() {
        this.travelRecords = FXCollections.observableArrayList();
        loadRecords(RECORDS_FILE);
        this.table.setItems(this.travelRecords);

        this.startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        this.endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        this.distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
        this.vehicleColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));
        this.fuelConsumedColumn.setCellValueFactory(new PropertyValueFactory<>("fuelConsumed"));
        this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
    }

    public static void setRecord(LocalDateTime start, LocalDateTime end, int km1, String nameV1, double gasCCalculate1, String color) {
        TravelRecord newRecord = new TravelRecord(start, end, km1, nameV1, gasCCalculate1);
        newRecord.setId(idCounter++);
        newRecord.setColor(color);
        travelRecords.add(newRecord);
        saveRecords(RECORDS_FILE);
    }

    public static void saveRecords(String filename) {
        try {
            List<TravelRecordData> recordDataList = new ArrayList<>();
            for (TravelRecord record : travelRecords) {
                recordDataList.add(new TravelRecordData(record));
            }

            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(recordDataList);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void loadRecords(String filename) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<TravelRecordData> recordDataList = (List<TravelRecordData>) in.readObject();

            travelRecords = FXCollections.observableArrayList();
            for (TravelRecordData recordData : recordDataList) {
                TravelRecord record = new TravelRecord(
                        LocalDateTime.parse(recordData.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        LocalDateTime.parse(recordData.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        recordData.getDistance(),
                        recordData.getVehicleName(),
                        recordData.getFuelConsumed()
                );
                record.setId(recordData.getId());
                record.setColor(recordData.getColor());
                travelRecords.add(record);
            }

            table.setItems(travelRecords);
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }

    public static void deleteRecordsFile(String filename) {
        File file = new File(filename);
        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
    }



}

