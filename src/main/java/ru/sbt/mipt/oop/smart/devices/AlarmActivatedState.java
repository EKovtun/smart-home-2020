package ru.sbt.mipt.oop.smart.devices;

class AlarmActivatedState implements AlarmState {
    private final Alarm alarm;

    AlarmActivatedState(Alarm alarm) throws IllegalArgumentException {
        if (alarm == null) throw new IllegalArgumentException();
        this.alarm = alarm;
    }

    @Override
    public boolean activate(String password) {
        return false;
    }

    @Override
    public boolean deactivate(String password) {
        if (!alarm.getPassword().equals(password)) {
            activateAlert();
            return false;
        }

        alarm.changeState(new AlarmDeactivatedState(alarm));
        return true;
    }

    @Override
    public boolean activateAlert() {
        alarm.changeState(new AlarmAlertState(alarm));
        return true;
    }
}
