package Panels;

import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Route implements Serializable {
    private transient SimpleStringProperty id;
    private transient SimpleStringProperty start;
    private transient SimpleStringProperty end;
    private transient SimpleStringProperty distance;

    public void setDistance(String distance) {
        this.distance.set(distance);
    }

    public Route(String id, String start, String end, String distance) {
        this.id = new SimpleStringProperty(id);
        this.start = new SimpleStringProperty(start);
        this.end = new SimpleStringProperty(end);
        this.distance = new SimpleStringProperty(distance);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();

        s.writeUTF(id.get());
        s.writeUTF(start.get());
        s.writeUTF(end.get());
        s.writeUTF(distance.get());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();

        id = new SimpleStringProperty(s.readUTF());
        start = new SimpleStringProperty(s.readUTF());
        end = new SimpleStringProperty(s.readUTF());
        distance = new SimpleStringProperty(s.readUTF());
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getStart() {
        return start.get();
    }

    public SimpleStringProperty startProperty() {
        return start;
    }

    public String getEnd() {
        return end.get();
    }

    public SimpleStringProperty endProperty() {
        return end;
    }

    public String getDistance() {
        return distance.get();
    }

    public SimpleStringProperty distanceProperty() {
        return distance;
    }
}