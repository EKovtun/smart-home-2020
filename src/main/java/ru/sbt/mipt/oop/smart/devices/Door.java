package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.events.processors.Action;

public class Door implements Actionable {
    private final String id;
    private DoorState state;

    public Door(String id, boolean isOpen) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException();
        this.id = id;
        this.state = isOpen? new DoorOpenedState(this) : new DoorClosedState(this);
    }

    public boolean setOpen(boolean open) {
        return open ? state.open() : state.close();
    }

    public boolean setLock(boolean lock) {
        return lock ? state.lock() : state.unlock();
    }

    public String getId() {
        return id;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }

    void setState(DoorState state) {
        this.state = state;
    }
}