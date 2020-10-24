package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.adapter.DoorSensorEventFactory;
import ru.sbt.mipt.oop.events.adapter.EventHandlerAdapter;
import ru.sbt.mipt.oop.events.adapter.LightSensorEventFactory;
import ru.sbt.mipt.oop.events.adapter.SensorEventFactory;
import ru.sbt.mipt.oop.events.processors.*;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.utils.SmartHomeReaderJsonFile;

import java.util.Collection;

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

    @Bean
    SensorEventFactory lightSensorEventFactory () {
        return new LightSensorEventFactory();
    }

    @Bean
    SensorEventFactory doorSensorEventFactory () {
        return new DoorSensorEventFactory();
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
