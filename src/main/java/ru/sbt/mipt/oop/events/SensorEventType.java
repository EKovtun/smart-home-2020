package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.Constants;

public enum SensorEventType {
    LIGHT_ON,
    LIGHT_OFF,
    DOOR_OPEN,
    DOOR_CLOSED,
    DOOR_LOCKED,
    DOOR_UNLOCKED,
    ALARM_ACTIVATE(Constants.ALARM_PASSWORD),
    ALARM_DEACTIVATE(Constants.ALARM_PASSWORD),
    ALARM_ALERT;

    private final String code;

    SensorEventType(String code) {
        this.code = code;
    }

    SensorEventType() {
        this.code = null;
    }

    public String getCode() {
        return code;
    }
}