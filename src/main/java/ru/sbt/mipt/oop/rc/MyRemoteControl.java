package ru.sbt.mipt.oop.rc;

import rc.RemoteControl;
import ru.sbt.mipt.oop.rc.commands.ControlCommand;

import java.util.HashMap;

public class MyRemoteControl implements RemoteControl {
    private final String id;
    private final HashMap<String, ControlCommand> commands = new HashMap<>();

    public MyRemoteControl(String id) {
        this.id = id;
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
