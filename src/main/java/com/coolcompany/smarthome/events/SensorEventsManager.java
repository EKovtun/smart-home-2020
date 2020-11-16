package com.coolcompany.smarthome.events;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;

import java.util.ArrayList;
import java.util.Collection;

public class SensorEventsManager {
    private final String[] eventTypes = new String[] { "LightIsOn", "LightIsOff", "DoorIsOpen", "DoorIsClosed", "DoorIsLocked", "DoorIsUnlocked" };

    private Collection<EventHandler> handlers = new ArrayList<>();

    public void registerEventHandler(EventHandler handler) {
        this.handlers.add(handler);
    }

    public void start() {
        CCSensorEvent event = getNextSensorEvent();
        while (event != null) {
            for (EventHandler handler : handlers) {
                handler.handleEvent(event);
            }
            event = getNextSensorEvent();
        }
    }

    private CCSensorEvent getNextSensorEvent() {
        if (Math.random() < 0.05) return null;

        int index = (int) (6 * Math.random());
        int offset = index > 1 ? 11 : 1;
        String sensorEventType = eventTypes[index];
        String objectId = "" + ((int) (8 * Math.random()) + offset);

        return new CCSensorEvent(sensorEventType, objectId);
    }


}
