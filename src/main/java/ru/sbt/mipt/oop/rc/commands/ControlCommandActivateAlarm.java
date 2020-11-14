package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.smart.devices.Alarm;

public class ControlCommandActivateAlarm implements ControlCommand {
    private final Alarm alarm;
    private final String password;

    public ControlCommandActivateAlarm(Alarm alarm, String password) {
        this.alarm = alarm;
        this.password = password;
    }

    @Override
    public void execute() {
        if (alarm.activate(password))
            System.out.println("Alarm " + alarm.getId() + " was activated via remote control.");
    }
}
