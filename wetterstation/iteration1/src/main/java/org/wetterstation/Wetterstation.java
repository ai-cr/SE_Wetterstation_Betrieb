package org.wetterstation;

import java.util.HashMap;
import java.util.Map;  // for the first iteration, simulating the database as simple HashMap
import java.util.ArrayList;


public class Wetterstation {
    private final Map<String,Double> database = new HashMap<>();
    private final ArrayList<Sensor> sensors;
    private final Communicator communicator;
    private final GUI gui;
    private final PowerManager powerManager;

    public Wetterstation() {
        this.sensors = new ArrayList<Sensor>();
        this.sensors.add(new TemperaturSensor(this.database));
        this.powerManager = new PowerManager();
        this.communicator = new Communicator(this.database, this.powerManager);
        this.gui = new GUI(this.communicator);
    }

    public void run() {
        while (true) {
            if (this.powerManager.getIsPoweredOn()) {
                for (Sensor sensor : this.sensors) {
                    double value = sensor.measureData();
                    sensor.saveData(value);
                }
                this.gui.displaySensorData();
            }
            this.gui.promptPowerCommand();
        }
    }

    public static void main(String[] args) {
        Wetterstation ws = new Wetterstation();
        ws.run();
    }
}
