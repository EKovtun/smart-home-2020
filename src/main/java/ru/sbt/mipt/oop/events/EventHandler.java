package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.events.processors.EventProcessor;
import ru.sbt.mipt.oop.smart.home.SmartHome;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EventHandler {
    private final Set<EventProcessor> processors = new HashSet<>();

    public EventHandler(Collection<EventProcessor> processors) {
        this.processors.addAll(processors);
    }

    public void executeEvent(SensorEvent sensorEvent, SmartHome smartHome) {
        processors.forEach(processor -> processor.processing(sensorEvent, smartHome));
    }
}
