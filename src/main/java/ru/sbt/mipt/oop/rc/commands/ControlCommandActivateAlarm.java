package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;

public class ControlCommandActivateAlarm implements ControlCommand {
    private final EventHandler eventHandler;
    private final String alarmId;

    public ControlCommandActivateAlarm(EventHandler eventHandler, String alarmId) {
        this.eventHandler = eventHandler;
        this.alarmId = alarmId;
    }

    @Override
    public void execute() {
        eventHandler.executeEvent(new SensorEvent(SensorEventType.ALARM_ACTIVATE, alarmId));
    }
}
