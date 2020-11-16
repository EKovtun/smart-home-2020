package ru.sbt.mipt.oop.smart.home.utils;

import com.google.gson.*;
import ru.sbt.mipt.oop.smart.devices.Alarm;
import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.home.SmartHome;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeReaderJsonFile implements SmartHomeReader {
    private final String filename;

    public SmartHomeReaderJsonFile(String filename) throws IllegalArgumentException {
        if (filename == null) throw new IllegalArgumentException();
        this.filename = filename;
    }

    @Override
    public SmartHome load() {
        Gson gson = createGson();
        String json;

        try {
            json = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return gson.fromJson(json, SmartHome.class);
    }

    private static class UserAlarmActionDeserializer implements JsonDeserializer<Alarm> {
        public Alarm deserialize(JsonElement json, Type typeOfT,
                                 JsonDeserializationContext context) throws JsonParseException {
            return new Alarm(json.getAsJsonObject().get("id").getAsString());
        }
    }

    private static class UserDoorActionDeserializer implements JsonDeserializer<Door> {
        public Door deserialize(JsonElement json, Type typeOfT,
                                 JsonDeserializationContext context) throws JsonParseException {
            return new Door(
                    json.getAsJsonObject().get("id").getAsString(),
                    json.getAsJsonObject().get("isOpen").getAsBoolean()
            );
        }
    }

    private Gson createGson() {
        //return (new GsonBuilder()).registerTypeAdapter(SmartDevice.class, new SmartDeviceAdapter()).setPrettyPrinting().create();
        var gson = new GsonBuilder();
        gson.registerTypeAdapter(Alarm.class, new UserAlarmActionDeserializer());
        gson.registerTypeAdapter(Door.class, new UserDoorActionDeserializer());
        return gson.setPrettyPrinting().create();
    }
}
