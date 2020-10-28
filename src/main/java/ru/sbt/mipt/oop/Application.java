package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.RandomEventGenerator;
import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.processors.*;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeReader;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeReaderJsonFile;

import java.util.Arrays;
import java.util.Collections;

public class Application {
    private final SmartHomeReader smartHomeReader;

    public Application(SmartHomeReader smartHomeReader) {
        this.smartHomeReader = smartHomeReader;
    }

    public static void main(String... args) {
        SmartHomeReader smartHomeReader = new SmartHomeReaderJsonFile(Constants.INPUT_SMART_HOME_JSON_FILE_NAME);
        Application application = new Application(smartHomeReader);

        application.start();
    }

    public void start() {
        SmartHome smartHome = smartHomeReader.load();
        if (smartHome == null) {
            System.out.println("Error load smart home");
            return;
        }

        SecurityProcessorsHandlerDecorator securityProcessorsHandlerDecorator = new SecurityProcessorsHandlerDecorator(Arrays.asList(
                new AlarmEventProcessor(),
                new LightEventProcessor(),
                new DoorEventProcessor(),
                new HallDoorEventProcessor()
        ));

        MainLoop mainLoop = new MainLoop(new EventHandler(Collections.singletonList(securityProcessorsHandlerDecorator)), new RandomEventGenerator());
        mainLoop.run(smartHome);
    }
}