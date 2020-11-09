package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.smart.home.SmartHome;

public interface EventProcessor {
    void processing(SensorEvent event, SmartHome smartHome);
}
