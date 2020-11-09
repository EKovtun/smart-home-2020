package ru.sbt.mipt.oop.smart.home.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.smart.home.SmartHome;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SmartHomeWriterJsonFile implements SmartHomeWriter {
    private final String filename;

    public SmartHomeWriterJsonFile(String filename) throws IllegalArgumentException {
        if (filename == null) throw new IllegalArgumentException();
        this.filename = filename;
    }

    @Override
    public boolean save(SmartHome smartHome) {
        Gson gson = createGson();
        String jsonString = gson.toJson(smartHome);
        Path path = Paths.get(filename);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private Gson createGson() {
        //return (new GsonBuilder()).registerTypeAdapter(SmartDevice.class, new SmartDeviceAdapter()).setPrettyPrinting().create();
        return (new GsonBuilder()).setPrettyPrinting().create();
    }
}
