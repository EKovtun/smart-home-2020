package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.events.adapter.MapBasedSensorEventFactory;
import ru.sbt.mipt.oop.events.adapter.EventHandlerAdapter;
import ru.sbt.mipt.oop.events.adapter.SensorEventFactory;
import ru.sbt.mipt.oop.events.processors.*;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeReaderJsonFile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyConfiguration {
    @Bean
    EventProcessor alarmEventProcessor(){
        return new AlarmEventProcessor();
    }

    @Bean
    EventProcessor doorEventProcessor(){
        return new DoorEventProcessor();
    }

    @Bean
    EventProcessor hallDoorEventProcessor(){
        return new HallDoorEventProcessor();
    }

    @Bean
    EventProcessor lightEventProcessor(){
        return new LightEventProcessor();
    }

    @Bean(name = "mapForMapBasedSensorEventFactory")
    // Если изменить HashMap на Map, то падает в MapBasedSensorEventFactory при попытке найти этот бин по имени
    // Почему?
    HashMap<String, SensorEventType> mapSensorEventTypeForFactory() {
        HashMap<String, SensorEventType> map = new HashMap<>();
        map.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        map.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        map.put("DoorIsLocked", SensorEventType.DOOR_LOCKED);
        map.put("DoorIsUnlocked", SensorEventType.DOOR_UNLOCKED);
        map.put("LightIsOn", SensorEventType.LIGHT_ON);
        map.put("LightIsOff", SensorEventType.LIGHT_OFF);
        return map;
    }

    @Bean
    SensorEventFactory sensorEventFactory () {
        return new MapBasedSensorEventFactory();
    }

    @Bean
    EventHandler eventHandler(Collection<EventProcessor> eventProcessors, SmartHome smartHome) {
        return new EventHandler(eventProcessors, smartHome);
    }

    @Bean
    EventHandlerAdapter eventHandlerAdapter(EventHandler eventHandler, Collection<SensorEventFactory> factories) {
        return new EventHandlerAdapter(eventHandler, factories);
    }

    @Bean
    SensorEventsManager sensorEventsManager(EventHandlerAdapter eventHandlerAdapter) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(eventHandlerAdapter);
        return sensorEventsManager;
    }

    @Bean
    SmartHome smartHome() {
        return new SmartHomeReaderJsonFile(Constants.INPUT_SMART_HOME_JSON_FILE_NAME).load();
    }
}
