package org.wetterstation;

import java.util.Map; // for the first iteration, simulating the database as a simple HashMap


public class Communicator {
    private final Map<String, Double> database;
    private final PowerManager powerManager;

    public Communicator(Map<String,Double> database, PowerManager powerManager) {
        this.database = database;
        this.powerManager = powerManager;
    }

    public Map<String,Double> getSensorData() {
        return this.database;
    }

    public void powerOff() {
        this.powerManager.powerOff();
    }

    public void powerOn() {
        this.powerManager.powerOn();
    }
}
