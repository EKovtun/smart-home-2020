package ru.sbt.mipt.oop.events.adapter;

import ru.sbt.mipt.oop.events.SensorEventType;

import java.util.Map;

public class MapBasedSensorEventFactory implements SensorEventFactory {
    private Map<String, SensorEventType> mapTypes;

    public MapBasedSensorEventFactory(Map<String, SensorEventType> mapTypes) {
        this.mapTypes = mapTypes;
    }

    public MapBasedSensorEventFactory() {}

    @Override
    public SensorEventType getType(String typeName) {
        return mapTypes.get(typeName);
    }
}