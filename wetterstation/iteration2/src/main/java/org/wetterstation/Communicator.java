package org.wetterstation;

import java.util.List;

public class Communicator {
    private final Database database;
    private final PowerManager powerManager;

    public Communicator(Database database, PowerManager powerManager) {
        this.database = database;
        this.powerManager = powerManager;
    }

    public List<List<Object>> getSensorData() {
        return this.database.getNewestData();
    }

    public void powerOff() {
        this.powerManager.powerOff();
    }

    public void powerOn() {
        this.powerManager.powerOn();
    }
}
