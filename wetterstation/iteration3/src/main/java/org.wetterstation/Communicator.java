package org.wetterstation;

import java.util.List;

/*
 * Communicator class, this simulates a network interface for the Zentrale of the Wetterstation.
 * It provides methods to interact with the database and power management system.
 * It allows for retrieving sensor data, powering on/off the system, and contacting the Wartungsdienst.
 */
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

    public void contactWartungsdienst(String sensor_id) {
        // Simulation of contacting the Wartungsdienst
        System.out.println("Wartungsdienst wurde kontaktiert für Sensor: " + sensor_id);
    }
}
