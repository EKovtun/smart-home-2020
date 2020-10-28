package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smart.devices.Alarm;
import ru.sbt.mipt.oop.smart.home.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

public class SecurityProcessorsHandlerDecorator implements EventProcessor {
    private final ArrayList<EventProcessor> processors = new ArrayList<>();

    public SecurityProcessorsHandlerDecorator(Collection<EventProcessor> processors) {
        this.processors.addAll(processors);
    }

    @Override
    public void processing(SensorEvent event, SmartHome smartHome) {
        Alarm alarm = smartHome.getAlarm();

        if (alarm == null || isAlarm(event)) {
            declare(event, smartHome);
            return;
        }

        if (alarm.isActivated()) alarm.activateAlert();
        if (alarm.isAlert()) {
            System.out.println("Sending sms");
            return;
        }

        declare(event, smartHome);
    }

    private void declare(SensorEvent event, SmartHome smartHome) {
        processors.forEach(processor -> processor.processing(event, smartHome));
    }

    private boolean isAlarm(SensorEvent event) {
        return event.getType() == SensorEventType.ALARM_DEACTIVATE || event.getType() == SensorEventType.ALARM_ACTIVATE;
    }
}
