package org.wetterstation;

import java.util.ArrayList;


public class Wetterstation {
    private static final int TIME_BETWEEN_MEASUREMENTS = 1000;
    private final Database database = new Database();
    private final ArrayList<Sensor> sensors;
    private final Communicator communicator;
    private final GUI gui;
    private final PowerManager powerManager;

    public Wetterstation() {
        this.sensors = new ArrayList<Sensor>();
        this.sensors.add(new TemperaturSensor(this.database));
        this.sensors.add(new LuftdruckSensor(this.database));
        this.powerManager = new PowerManager();
        this.communicator = new Communicator(this.database, this.powerManager);
        this.gui = new GUI(this.communicator);
    }

    public void run() {
        this.powerManager.powerOn();
        Thread sensorThread = new Thread(() -> {
            while (true) {
                if (powerManager.getIsPoweredOn()) {
                    for (Sensor sensor : this.sensors) {
                        double data = sensor.measureData();
                        sensor.saveData(data);
                    }
                }
                try {
                    Thread.sleep(TIME_BETWEEN_MEASUREMENTS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        sensorThread.setDaemon(true);
        sensorThread.start();

        this.gui.promptPowerCommand();
    }

    public static void main(String[] args) {
        Wetterstation ws = new Wetterstation();
        ws.run();
    }
}
