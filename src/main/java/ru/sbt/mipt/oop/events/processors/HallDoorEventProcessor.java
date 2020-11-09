package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.home.Room;
import ru.sbt.mipt.oop.smart.home.SmartHome;

public class HallDoorEventProcessor implements EventProcessor {
    @Override
    public void processing(SensorEvent event, SmartHome smartHome) {
        if (!isDoorClosedEvent(event)) return;
        smartHome.execute( device ->
                {
                    if (!(device instanceof Room)) return;
                    Room room = (Room) device;
                    if (!room.getName().equals("hall")) return;

                    room.execute(deviceInRoom ->
                    {
                        if (!(deviceInRoom instanceof Door)) return;
                        Door door = (Door) deviceInRoom;
                        if (((Door) deviceInRoom).getId().equals(event.getObjectId()))
                            turnOffAllLights(smartHome);
                    });
                }
        );
    }

    private boolean isDoorClosedEvent(SensorEvent event) {
        return event.getType() == SensorEventType.DOOR_CLOSED;
    }

    private void turnOffAllLights(SmartHome smartHome) {
        smartHome.execute(object ->
        {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(false);
                System.out.println("Light " + light.getId() + " was off.");
            }
        });
    }
}
