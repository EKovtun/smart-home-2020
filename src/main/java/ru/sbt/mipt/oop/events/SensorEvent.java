package ru.sbt.mipt.oop.events;

public class SensorEvent {
    private final SensorEventType type;
    private final String objectId;

    public SensorEvent(SensorEventType type, String objectId) throws IllegalArgumentException {
        if (type == null) throw new IllegalArgumentException();
        this.type = type;
        this.objectId = objectId;
    }

    public SensorEventType getType() {
        return this.type;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public String toString() {
        return "SensorEvent{type=" + this.type + ", objectId='" + this.objectId + '\'' + '}';
    }
}