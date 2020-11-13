package ru.sbt.mipt.oop.rc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.processors.*;
import ru.sbt.mipt.oop.rc.commands.*;
import ru.sbt.mipt.oop.smart.devices.Alarm;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.home.Room;
import ru.sbt.mipt.oop.smart.home.SmartHome;

import java.util.*;

class RemoteControlTests {
    private Collection<EventProcessor> generateEventProcessors() {
        ArrayList<EventProcessor> result = new ArrayList<>();
        result.add(new AlarmEventProcessor());
        result.add(new DoorEventProcessor());
        result.add(new HallDoorEventProcessor());
        result.add(new LightEventProcessor());
        return result;
    }

    @Test
    void controlCommandActivateAlarm() {
        // given
        Alarm alarm = new Alarm("1");
        SmartHome smartHome = new SmartHome(alarm, new ArrayList<>());
        EventHandler eventHandler = new EventHandler(generateEventProcessors(), smartHome);
        MyRemoteControl myRemoteControl = new MyRemoteControl("1", null);
        myRemoteControl.registerCommand("A", new ControlCommandActivateAlarm(eventHandler,"1"));
        // when
        boolean alarmIsDeactivate = !alarm.isActivated();
        myRemoteControl.onButtonPressed("A", "1");
        boolean alarmIsActivated = alarm.isActivated();
        // then
        assertTrue(alarmIsDeactivate);
        assertTrue(alarmIsActivated);
    }

    @Test
    void controlCommandActivateAlarmAlert() {
        // given
        Alarm alarm = new Alarm("1");
        SmartHome smartHome = new SmartHome(alarm, new ArrayList<>());
        EventHandler eventHandler = new EventHandler(generateEventProcessors(), smartHome);
        MyRemoteControl myRemoteControl = new MyRemoteControl("1", null);
        myRemoteControl.registerCommand("A", new ControlCommandActivateAlarm(eventHandler,"1"));
        myRemoteControl.registerCommand("B", new ControlCommandActivateAlarmAlert(eventHandler,"1"));
        // when
        boolean alarmIsNotAlert = !alarm.isAlert();
        myRemoteControl.onButtonPressed("A", "1");
        myRemoteControl.onButtonPressed("B", "1");
        boolean alarmIsAlert = alarm.isAlert();
        // then
        assertTrue(alarmIsNotAlert);
        assertTrue(alarmIsAlert);
    }

    @Test
    void controlCommandCloseDoors() {
        // given
        Door door1 = new Door("1", true);
        Door door2 = new Door("2", true);

        List<Room> rooms = Collections.singletonList(new Room("Hall", Arrays.asList(door1, door2), Collections.emptyList()));
        SmartHome smartHome = new SmartHome(null, rooms);

        EventHandler eventHandler = new EventHandler(generateEventProcessors(), smartHome);
        MyRemoteControl myRemoteControl = new MyRemoteControl("1", null);
        myRemoteControl.registerCommand("A", new ControlCommandCloseDoors(eventHandler,Arrays.asList("1", "2")));
        // when
        boolean door1Open = door1.isOpen();
        boolean door2Open = door2.isOpen();
        myRemoteControl.onButtonPressed("A", "1");
        boolean door1Closed = !door1.isOpen();
        boolean door2Closed = !door2.isOpen();

        // then
        assertTrue(door1Open);
        assertTrue(door2Open);
        assertTrue(door1Closed);
        assertTrue(door2Closed);
    }

    @Test
    void controlCommandOffLights() {
        // given
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", true);

        List<Room> rooms = Collections.singletonList(new Room("Hall", Collections.emptyList(), Arrays.asList(light1, light2)));
        SmartHome smartHome = new SmartHome(null, rooms);

        EventHandler eventHandler = new EventHandler(generateEventProcessors(), smartHome);
        MyRemoteControl myRemoteControl = new MyRemoteControl("1", null);
        myRemoteControl.registerCommand("A", new ControlCommandOffLights(eventHandler,Arrays.asList("1", "2")));
        // when
        boolean light1On = light1.isOn();
        boolean light2On = light2.isOn();
        myRemoteControl.onButtonPressed("A", "1");
        boolean light1Off = !light1.isOn();
        boolean light2Off = !light2.isOn();

        // then
        assertTrue(light1On);
        assertTrue(light2On);
        assertTrue(light1Off);
        assertTrue(light2Off);
    }

    @Test
    void controlCommandOnLights() {
        // given
        Light light1 = new Light("1", false);
        Light light2 = new Light("2", false);

        List<Room> rooms = Collections.singletonList(new Room("Hall", Collections.emptyList(), Arrays.asList(light1, light2)));
        SmartHome smartHome = new SmartHome(null, rooms);

        EventHandler eventHandler = new EventHandler(generateEventProcessors(), smartHome);
        MyRemoteControl myRemoteControl = new MyRemoteControl("1", null);
        myRemoteControl.registerCommand("A", new ControlCommandOnLights(eventHandler,Arrays.asList("1", "2")));
        // when
        boolean light1Off = !light1.isOn();
        boolean light2Off = !light2.isOn();
        myRemoteControl.onButtonPressed("A", "1");
        boolean light1On = light1.isOn();
        boolean light2On = light2.isOn();

        // then
        assertTrue(light1Off);
        assertTrue(light2Off);
        assertTrue(light1On);
        assertTrue(light2On);
    }
}