package ru.sbt.mipt.oop.events.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;

import java.util.ArrayList;
import java.util.Collection;

public class EventHandlerAdapter implements com.coolcompany.smarthome.events.EventHandler {
    private final EventHandler eventHandler;
    private final Collection<SensorEventFactory> sensorEventFactories = new ArrayList<>();

    public EventHandlerAdapter(EventHandler eventHandler, Collection<SensorEventFactory> sensorEventFactories) {
        this.eventHandler = eventHandler;
        this.sensorEventFactories.addAll(sensorEventFactories);
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEventType ownEventType = adapts(event.getEventType());
        if (ownEventType == null) return;
        SensorEvent ownEvent = new SensorEvent(ownEventType, event.getObjectId());
        eventHandler.executeEvent(ownEvent);
    }

    private SensorEventType adapts(String eventTypeName) {
        for(var factory: sensorEventFactories) {
            var sensorEventType = factory.getType(eventTypeName);
            if (sensorEventType != null) return sensorEventType;
        }
        return null;
    }
}
