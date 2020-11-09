package ru.sbt.mipt.oop.smart.devices;

class AlarmDeactivatedState implements AlarmState {
    private final Alarm alarm;

    AlarmDeactivatedState(Alarm alarm) throws IllegalArgumentException {
        if (alarm == null) throw new IllegalArgumentException();
        this.alarm = alarm;
    }

    @Override
    public boolean activate(String password) {
        if (password == null || password.trim().isEmpty()) return false;

        if (alarm.getPassword() == null) {
            alarm.setPassword(password);
            alarm.changeState(new AlarmActivatedState(alarm));
            return true;
        }

        if (alarm.getPassword().equals(password)) {
            alarm.changeState(new AlarmActivatedState(alarm));
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean deactivate(String password) {
        return false;
    }

    @Override
    public boolean activateAlert() {
        return false;
    }
}
