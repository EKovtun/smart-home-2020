package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.smart.devices.Actionable;

public interface Action {
    void act(Actionable device);
}