package ru.sbt.mipt.oop.events.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.sbt.mipt.oop.events.SensorEventType;

import java.util.HashMap;

public class MapBasedSensorEventFactory implements SensorEventFactory {

    @Autowired
    @Qualifier("mapForMapBasedSensorEventFactory")
    // Если изменить HashMap на Map здесь и в бине (в MyConfiguration.class), то не может найти бин с таким именем
    // Почему?
    private HashMap<String, SensorEventType> mapTypes;

    @Override
    public SensorEventType getType(String typeName) {
        return mapTypes.get(typeName);
    }
}