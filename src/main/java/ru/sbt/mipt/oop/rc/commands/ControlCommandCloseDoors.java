package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;

import java.util.ArrayList;
import java.util.Collection;

public class ControlCommandCloseDoors implements ControlCommand {
    private final EventHandler eventHandler;
    private final ArrayList<String> doorsIds = new ArrayList<>();

    public ControlCommandCloseDoors(EventHandler eventHandler, Collection<String> doorsIds) {
        this.eventHandler = eventHandler;
        this.doorsIds.addAll(doorsIds);
    }

    @Override
    public void execute() {
        for (String doorId : doorsIds)
            eventHandler.executeEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, doorId));
    }
}
