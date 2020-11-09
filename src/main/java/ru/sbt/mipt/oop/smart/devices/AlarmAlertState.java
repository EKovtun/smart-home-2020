package ru.sbt.mipt.oop.smart.devices;

class AlarmAlertState implements AlarmState {
    private final Alarm alarm;

    AlarmAlertState(Alarm alarm) throws IllegalArgumentException {
        if (alarm == null) throw new IllegalArgumentException();
        this.alarm = alarm;
    }

    @Override
    public boolean activate(String password) {
        if (checkPassword(password)) return false;
        alarm.changeState(new AlarmActivatedState(alarm));
        return true;
    }

    @Override
    public boolean deactivate(String password) {
        if (checkPassword(password)) return false;
        alarm.changeState(new AlarmDeactivatedState(alarm));
        return true;
    }

    @Override
    public boolean activateAlert() {
        return false;
    }

    private boolean checkPassword(String password) {
        if (password == null || password.trim().isEmpty()) return true;
        return !alarm.getPassword().equals(password);
    }
}
