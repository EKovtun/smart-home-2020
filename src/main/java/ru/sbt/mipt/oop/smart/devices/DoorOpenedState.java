package ru.sbt.mipt.oop.smart.devices;

class DoorOpenedState implements DoorState {
    private final Door door;

    DoorOpenedState(Door door) {
        this.door = door;
    }

    @Override
    public boolean open() {
        return false;
    }

    @Override
    public boolean close() {
        door.setState(new DoorClosedState(door));
        return true;
    }

    @Override
    public boolean lock() {
        return false;
    }

    @Override
    public boolean unlock() {
        return false;
    }
}
