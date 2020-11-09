package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.events.processors.Action;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;

    public Door(String id, boolean isOpen) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException();

        this.id = id;
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    public String getId() {
        return id;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}