package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.RandomEventGenerator;
import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smart.home.SmartHome;

public class MainLoop {
    private final RandomEventGenerator randomEventGenerator;
    private final EventHandler eventHandler;

    public MainLoop(EventHandler eventHandler, RandomEventGenerator randomEventGenerator) throws IllegalArgumentException {
        if (eventHandler == null || randomEventGenerator == null) throw new IllegalArgumentException();
        this.eventHandler = eventHandler;
        this.randomEventGenerator = randomEventGenerator;
    }

    public void run(SmartHome smartHome) {
        // Активируем сигнализацию
        eventHandler.executeEvent(new SensorEvent(SensorEventType.ALARM_ACTIVATE, Constants.ALARM_DEVICE_ID), smartHome);
        // Входим в цикл обработки событий
        while(true) {
            SensorEvent event = randomEventGenerator.getNextSensorEvent();
            if (event == null) return;
            System.out.println("Got event: " + event);
            eventHandler.executeEvent(event, smartHome);
        }
    }
}
