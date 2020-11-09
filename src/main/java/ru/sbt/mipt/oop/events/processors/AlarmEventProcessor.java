package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.Alarm;
import ru.sbt.mipt.oop.smart.home.SmartHome;

public class AlarmEventProcessor implements EventProcessor {
    @Override
    public void processing(SensorEvent event, SmartHome smartHome) {
        if (!isAlarm(event)) return;
        smartHome.execute( device ->
                {
                    if (!(device instanceof Alarm)) return;
                    Alarm alarm = (Alarm) device;
                    if (!alarm.getId().equals(event.getObjectId())) return;

                    switch (event.getType()) {
                        case ALARM_ACTIVATE:
                            if (alarm.activate(event.getType().getCode()))
                                System.out.println("Alarm " + alarm.getId() + " was activated.");
                            break;
                        case ALARM_DEACTIVATE:
                            if (alarm.deactivate(event.getType().getCode()))
                                System.out.println("Alarm " + alarm.getId() + " was deactivated.");
                            break;
                        default:
                            break;
                    }
                }
        );
    }

    private boolean isAlarm(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_ACTIVATE || event.getType() == SensorEventType.ALARM_DEACTIVATE;
    }
}
