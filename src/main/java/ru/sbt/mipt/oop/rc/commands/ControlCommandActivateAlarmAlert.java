package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.smart.devices.Alarm;

public class ControlCommandActivateAlarmAlert implements ControlCommand {
    private final Alarm alarm;

    public ControlCommandActivateAlarmAlert(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        if (alarm.activateAlert())
            System.out.println("Alarm " + alarm.getId() + " alert was activated via remote control.");
    }
}
