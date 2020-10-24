package ru.sbt.mipt.oop.events.adapter;

import ru.sbt.mipt.oop.events.SensorEventType;

public class LightSensorEventFactory implements SensorEventFactory {
    @Override
    public SensorEventType getType(String typeName) {
        switch (typeName) {
            case "LightIsOn":
                return SensorEventType.LIGHT_ON;
            case "LightIsOff":
                return SensorEventType.LIGHT_OFF;
            default:
                return null;
        }
    }
}
