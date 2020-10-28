package ru.sbt.mipt.oop.events.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.Constants;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.events.processors.AlarmEventProcessor;
import ru.sbt.mipt.oop.events.processors.LightEventProcessor;
import ru.sbt.mipt.oop.events.processors.SecurityProcessorsHandlerDecorator;
import ru.sbt.mipt.oop.smart.devices.Alarm;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.home.Room;
import ru.sbt.mipt.oop.smart.home.SmartHome;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AlarmTests {
    SmartHome smartHome;
    Alarm alarm;
    Light light = new Light("1", false);

    SecurityProcessorsHandlerDecorator securityAlarmEvent = new SecurityProcessorsHandlerDecorator(Collections.singletonList(new AlarmEventProcessor()));
    SecurityProcessorsHandlerDecorator securityLightEvent = new SecurityProcessorsHandlerDecorator(Collections.singletonList(new LightEventProcessor()));

    @BeforeEach
    void initialization() {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room("hall", null, Collections.singletonList(light)));

        alarm = new Alarm(Constants.ALARM_DEVICE_ID);
        smartHome = new SmartHome(alarm, rooms);
    }

    @Test
    void actionAlarmActive_whenCodeIsValid() {
        // when
        securityAlarmEvent.processing(
                new SensorEvent(SensorEventType.ALARM_ACTIVATE, Constants.ALARM_DEVICE_ID),
                smartHome);

        boolean alarmActive = alarm.isActivated();
        //then
        assertTrue(alarmActive);
    }

    @Test
    void actionAlarmActivate_whenAlarmAlert() {
        // when
        securityAlarmEvent.processing(
                new SensorEvent(SensorEventType.ALARM_ACTIVATE, Constants.ALARM_DEVICE_ID),
                smartHome);

        securityLightEvent.processing(
                new SensorEvent(SensorEventType.LIGHT_ON, "1"),
                smartHome);

        securityAlarmEvent.processing(
                new SensorEvent(SensorEventType.ALARM_ACTIVATE, Constants.ALARM_DEVICE_ID),
                smartHome);

        boolean alarmActivated = alarm.isActivated();
        boolean alarmAlert = alarm.isAlert();
        //then
        assertTrue(alarmActivated);
        assertFalse(alarmAlert);
    }

    @Test
    void actionAlarmDeactivate_whenCodeIsValidAndAlarmIsDeactivated() {
        // when
        securityAlarmEvent.processing(
                new SensorEvent(SensorEventType.ALARM_DEACTIVATE, Constants.ALARM_DEVICE_ID),
                smartHome);

        boolean alarmActive = alarm.isActivated();
        //then
        assertFalse(alarmActive);
    }

    @Test
    void actionAlarmDeactivate_whenCodeIsValidAndAlarmIsActivated() {
        // when
        securityAlarmEvent.processing(
                new SensorEvent(SensorEventType.ALARM_ACTIVATE, Constants.ALARM_DEVICE_ID),
                smartHome);

        boolean alarmActive = alarm.isActivated();

        securityAlarmEvent.processing(
                new SensorEvent(SensorEventType.ALARM_DEACTIVATE, Constants.ALARM_DEVICE_ID),
                smartHome);

        boolean alarmDeactivated = !alarm.isActivated();
        //then
        assertTrue(alarmActive);
        assertTrue(alarmDeactivated);
    }

    @Test
    void actionAlarmDeactivate_whenAlarmAlert() {
        // when
        securityAlarmEvent.processing(
                new SensorEvent(SensorEventType.ALARM_ACTIVATE, Constants.ALARM_DEVICE_ID),
                smartHome);

        securityLightEvent.processing(
                new SensorEvent(SensorEventType.LIGHT_ON, "1"),
                smartHome);

        securityAlarmEvent.processing(
                new SensorEvent(SensorEventType.ALARM_DEACTIVATE, Constants.ALARM_DEVICE_ID),
                smartHome);

        boolean alarmDeactivate = !alarm.isActivated();
        //then
        assertTrue(alarmDeactivate);
    }

    @Test
    void alert_whenAlarmIsActivated() {
        // when
        securityAlarmEvent.processing(
                new SensorEvent(SensorEventType.ALARM_ACTIVATE, Constants.ALARM_DEVICE_ID),
                smartHome);

        securityLightEvent.processing(
                new SensorEvent(SensorEventType.LIGHT_ON, "1"),
                smartHome);

        boolean alarmAlert = alarm.isAlert();
        // then
        assertTrue(alarmAlert);
    }

    @Test
    void alert_actionsNotExecutedWhenAlert() {
        // when
        securityAlarmEvent.processing(
                new SensorEvent(SensorEventType.ALARM_ACTIVATE, Constants.ALARM_DEVICE_ID),
                smartHome);

        securityLightEvent.processing(
                new SensorEvent(SensorEventType.LIGHT_ON, "1"),
                smartHome);

        boolean light1IsOff = !light.isOn();
        // then
        assertTrue(light1IsOff);
    }

    @Test
    void alert_actionsExecutedWhenNotAlert() {
        // when
        securityAlarmEvent.processing(
                new SensorEvent(SensorEventType.ALARM_ACTIVATE, Constants.ALARM_DEVICE_ID),
                smartHome);

        securityLightEvent.processing(
                new SensorEvent(SensorEventType.LIGHT_ON, "1"),
                smartHome);

        boolean light1IsOff = !light.isOn();

        securityAlarmEvent.processing(
                new SensorEvent(SensorEventType.ALARM_DEACTIVATE, Constants.ALARM_DEVICE_ID),
                smartHome);

        securityLightEvent.processing(
                new SensorEvent(SensorEventType.LIGHT_ON, "1"),
                smartHome);

        boolean light1IsOn = light.isOn();
        // then
        assertTrue(light1IsOff);
        assertTrue(light1IsOn);
    }
}