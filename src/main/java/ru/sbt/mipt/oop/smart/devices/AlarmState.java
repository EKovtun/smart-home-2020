package ru.sbt.mipt.oop.smart.devices;

public interface AlarmState {
    boolean activate(String password);
    boolean deactivate(String password);
    boolean activateAlert();
}
