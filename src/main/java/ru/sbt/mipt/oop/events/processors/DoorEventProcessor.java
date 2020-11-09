package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.home.SmartHome;

public class DoorEventProcessor implements EventProcessor {
    @Override
    public void processing(SensorEvent event, SmartHome smartHome) {
        if (!isDoor(event)) return;
        smartHome.execute( device ->
                {
                    if (!(device instanceof Door)) return;
                    Door door = (Door) device;
                    if (!door.getId().equals(event.getObjectId())) return;

                    switch (event.getType()) {
                        case DOOR_OPEN:
                            door.setOpen(true);
                            System.out.println("Door " + door.getId() + " was opened.");
                            break;
                        case DOOR_CLOSED:
                            door.setOpen(false);
                            System.out.println("Door " + door.getId() + " was closed.");
                            break;
                        default:
                            break;
                    }
                }
        );
    }

    private boolean isDoor(SensorEvent event) {
        return event.getType() == SensorEventType.DOOR_OPEN || event.getType() == SensorEventType.DOOR_CLOSED;
    }
}
