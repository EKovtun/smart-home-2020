package ru.sbt.mipt.oop.smart.devices;

interface DoorState {
    boolean open();
    boolean close();
    boolean lock();
    boolean unlock();
}
