package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;

public class ControlCommandActivateAlarmAlert implements ControlCommand {
    private final EventHandler eventHandler;
    private final String alarmId;

    public ControlCommandActivateAlarmAlert(EventHandler eventHandler, String alarmId) {
        this.eventHandler = eventHandler;
        this.alarmId = alarmId;
    }

    @Override
    public void execute() {
        eventHandler.executeEvent(new SensorEvent(SensorEventType.ALARM_ALERT, alarmId));
    }
}
