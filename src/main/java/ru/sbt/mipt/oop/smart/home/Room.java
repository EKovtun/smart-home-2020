package ru.sbt.mipt.oop.smart.home;

import ru.sbt.mipt.oop.events.processors.Action;
import ru.sbt.mipt.oop.smart.devices.Actionable;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.Light;

import java.util.*;

public class Room implements Actionable {
    private final String name;
    private final Set<Door> doors = new HashSet<>();
    private final Set<Light> lights = new HashSet<>();

    public Room(String name, Collection<Door> doors, Collection<Light> lights) throws IllegalArgumentException {
        if (name == null) throw new IllegalArgumentException();
        this.name = name;
        if (doors != null) this.doors.addAll(doors);
        if (lights != null) this.lights.addAll(lights);
    }

    @Override
    public void execute(Action action) {
        action.act(this);
        doors.forEach(door -> door.execute(action));
        lights.forEach(light -> light.execute(action));
    }

    public String getName() {
        return name;
    }
}