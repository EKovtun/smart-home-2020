package ru.sbt.mipt.oop.events.adapter;

import ru.sbt.mipt.oop.events.SensorEventType;

public class DoorSensorEventFactory implements SensorEventFactory {
    @Override
    public SensorEventType getType(String typeName) {
        switch (typeName) {
            case "DoorIsOpen":
                return SensorEventType.DOOR_OPEN;
            case "DoorIsClosed":
                return SensorEventType.DOOR_CLOSED;
            case "DoorIsLocked":
                return SensorEventType.DOOR_LOCKED;
            case "DoorIsUnlocked":
                return SensorEventType.DOOR_UNLOCKED;
            default:
                return null;
        }
    }
}
