package Panels;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Trip {
    private static int nextId = 1;

    private final ReadOnlyIntegerWrapper id = new ReadOnlyIntegerWrapper();
    private StringProperty start;
    private StringProperty end;
    private DoubleProperty distance;

    public Trip(String start, String end, double distance) {
        this.id.set(nextId);
        nextId++;

        this.start = new SimpleStringProperty(start);
        this.end = new SimpleStringProperty(end);
        this.distance = new SimpleDoubleProperty(distance);
    }

    public int getId() {
        return id.get();
    }

    public ReadOnlyIntegerProperty idProperty() {
        return id.getReadOnlyProperty();
    }

    public String getStart() {
        return start.get();
    }

    public StringProperty startProperty() {
        return start;
    }

    public String getEnd() {
        return end.get();
    }

    public StringProperty endProperty() {
        return end;
    }

    public double getDistance() {
        return distance.get();
    }

    public DoubleProperty distanceProperty() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance.set(distance);
    }
}