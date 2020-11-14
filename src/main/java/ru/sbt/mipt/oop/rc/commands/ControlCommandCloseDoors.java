package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.home.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

public class ControlCommandCloseDoors implements ControlCommand {
    private final SmartHome smartHome;
    private final ArrayList<String> doorsIds = new ArrayList<>();

    public ControlCommandCloseDoors(SmartHome smartHome, Collection<String> doorsIds) {
        this.smartHome = smartHome;
        this.doorsIds.addAll(doorsIds);
    }

    @Override
    public void execute() {
        smartHome.execute( device ->
                {
                    if (!(device instanceof Door)) return;
                    Door door = (Door) device;
                    if (!doorsIds.contains(door.getId())) return;
                    if (door.setOpen(false))
                        System.out.println("Door " + door.getId() + " was closed via remote control.");
                }
        );
    }
}
