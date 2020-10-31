package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;

import java.util.ArrayList;
import java.util.Collection;

public class ControlCommandOnLights implements ControlCommand {
    private final EventHandler eventHandler;
    private final ArrayList<String> lightsIds = new ArrayList<>();

    public ControlCommandOnLights(EventHandler eventHandler, Collection<String> lightsIds) {
        this.eventHandler = eventHandler;
        this.lightsIds.addAll(lightsIds);
    }

    @Override
    public void execute() {
        for (String lightId : lightsIds)
            eventHandler.executeEvent(new SensorEvent(SensorEventType.LIGHT_ON, lightId));
    }
}
