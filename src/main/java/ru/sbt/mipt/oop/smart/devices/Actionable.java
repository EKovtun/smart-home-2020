package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.events.processors.Action;

public interface Actionable {
    void execute(Action action);
}
