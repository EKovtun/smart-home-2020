package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.home.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

public class ControlCommandOnLights implements ControlCommand {
    private final SmartHome smartHome;
    private final ArrayList<String> lightsIds = new ArrayList<>();

    public ControlCommandOnLights(SmartHome smartHome, Collection<String> lightsIds) {
        this.smartHome = smartHome;
        this.lightsIds.addAll(lightsIds);
    }

    @Override
    public void execute() {
        smartHome.execute( device ->
                {
                    if (!(device instanceof Light)) return;
                    Light light = (Light) device;
                    if (!lightsIds.contains(light.getId())) return;
                    light.setOn(true);
                    System.out.println("Ligth " + light.getId() + " was on via remote control.");
                }
        );
    }
}
