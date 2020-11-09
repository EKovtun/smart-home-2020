package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.events.processors.Action;

public class Alarm implements Actionable {
    private final String id;
    private transient String password;
    private transient AlarmState state;

    public Alarm(String id) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException();
        this.id = id;
        this.state = new AlarmDeactivatedState(this);
    }

    public boolean activate(String password) {
        return state.activate(password);
    }

    public boolean deactivate(String password) {
        return state.deactivate(password);
    }

    public boolean activateAlert() {
        return state.activateAlert();
    }

    public boolean isAlert() {
        return state instanceof AlarmAlertState;
    }

    public boolean isActivated() {
        return state instanceof AlarmActivatedState;
    }

    public String getId() {
        return id;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }

    void setPassword(String password) {
        this.password = password;
    }

    String getPassword() {
        return password;
    }

    void changeState(AlarmState state) {
        if (state == null) return;
        this.state = state;
    }
}
