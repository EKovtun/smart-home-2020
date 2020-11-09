package ru.sbt.mipt.oop.smart.home;

import java.util.*;

import ru.sbt.mipt.oop.events.processors.Action;
import ru.sbt.mipt.oop.smart.devices.Actionable;
import ru.sbt.mipt.oop.smart.devices.Alarm;

public class SmartHome implements Actionable {
    private final Set<Room> rooms = new HashSet<>();
    private final Alarm alarm;

    public SmartHome(Alarm alarm, Collection<Room> rooms) throws IllegalArgumentException {
        if (alarm == null || rooms == null) throw new IllegalArgumentException();
        this.rooms.addAll(rooms);
        this.alarm = alarm;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
        alarm.execute(action);
        rooms.forEach(room -> room.execute(action));
    }
}