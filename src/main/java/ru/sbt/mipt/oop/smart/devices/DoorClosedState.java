package ru.sbt.mipt.oop.smart.devices;

class DoorClosedState implements DoorState {
    private final Door door;
    private boolean lock = false;

    DoorClosedState(Door door) {
        this.door = door;
    }

    @Override
    public boolean open() {
        if (lock) return false;
        door.setState(new DoorOpenedState(door));
        return true;
    }

    @Override
    public boolean close() {
        return false;
    }

    @Override
    public boolean lock() {
        if (lock) return false;
        lock = true;
        return true;
    }

    @Override
    public boolean unlock() {
        if (!lock) return false;
        lock = false;
        return true;
    }
}
