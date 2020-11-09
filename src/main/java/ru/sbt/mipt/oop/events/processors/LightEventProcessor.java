package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.home.SmartHome;

public class LightEventProcessor implements EventProcessor {
    @Override
    public void processing(SensorEvent event, SmartHome smartHome) {
        if (!isLight(event)) return;
        smartHome.execute( device ->
                {
                    if (!(device instanceof Light)) return;
                    Light light = (Light) device;
                    if (!light.getId().equals(event.getObjectId())) return;

                    switch (event.getType()) {
                        case LIGHT_ON:
                            light.setOn(true);
                            System.out.println("Light " + light.getId() + " was on.");
                            break;
                        case LIGHT_OFF:
                            light.setOn(false);
                            System.out.println("Light " + light.getId() + " was off.");
                            break;
                        default:
                            break;
                    }
                }
        );
    }

    private boolean isLight(SensorEvent event) {
        return event.getType() == SensorEventType.LIGHT_ON || event.getType() == SensorEventType.LIGHT_OFF;
    }
}
