package ru.sbt.mipt.oop.rc;

import rc.RemoteControl;
import ru.sbt.mipt.oop.rc.commands.ControlCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyRemoteControl implements RemoteControl {
    private final String id;
    private final Map<String, ControlCommand> commands;

    public MyRemoteControl(String id, Map<String, ControlCommand> commandsMap) {
        this.id = id;
        commands = Objects.requireNonNullElseGet(commandsMap, HashMap::new);
    }

    public void registerCommand(String buttonCode, ControlCommand command) {
        commands.put(buttonCode, command);
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (!rcId.equals(id) || !commands.containsKey(buttonCode)) return;
        commands.get(buttonCode).execute();
    }
}
