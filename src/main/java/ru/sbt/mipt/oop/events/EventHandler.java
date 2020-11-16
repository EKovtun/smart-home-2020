package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.events.processors.EventProcessor;
import ru.sbt.mipt.oop.smart.home.SmartHome;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EventHandler {
    private final Set<EventProcessor> processors = new HashSet<>();
    private final SmartHome smartHome;

    public EventHandler(Collection<EventProcessor> processors, SmartHome smartHome) {
        this.processors.addAll(processors);
        this.smartHome = smartHome;
    }

    public void executeEvent(SensorEvent sensorEvent) {
        processors.forEach(processor -> processor.processing(sensorEvent, smartHome));
    }
}
