package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.smart.devices.Alarm;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.home.Room;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeWriter;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeWriterJsonFile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class HomeBuilder {
    public static void main(String[] args) {
        Room bathroom = new Room("bathroom",
                Arrays.asList(
                        new Door("11", false),
                        new Door("12", false)
                ),
                Arrays.asList(
                        new Light("1", false),
                        new Light("2", false)
                ));

        Room hall = new Room("hall",
                Arrays.asList(
                        new Door("13", false),
                        new Door("14", false)
                ),
                Arrays.asList(
                        new Light("3", false),
                        new Light("4", false)
                ));

        Room kitchen = new Room("kitchen",
                Arrays.asList(
                        new Door("15", false),
                        new Door("16", false)
                ),
                Arrays.asList(
                        new Light("5", false),
                        new Light("6", false)
                ));

        Room bedroom = new Room("bedroom",
                Arrays.asList(
                        new Door("17", false),
                        new Door("18", false)
                ),
                Arrays.asList(
                        new Light("7", false),
                        new Light("8", false)
                ));

        ArrayList<Room> roomsList = new ArrayList<>();
        roomsList.add(bathroom);
        roomsList.add(hall);
        roomsList.add(kitchen);
        roomsList.add(bedroom);

        SmartHome smartHome = new SmartHome(new Alarm(Constants.ALARM_DEVICE_ID), roomsList);

        SmartHomeWriter smartHomeReaderWriter = new SmartHomeWriterJsonFile(
                Constants.OUTPUT_SMART_HOME_JSON_FILE_NAME);
        boolean saveSuccessful = smartHomeReaderWriter.save(smartHome);
        if (!saveSuccessful)
            System.out.println("Error save smart home");
    }
}