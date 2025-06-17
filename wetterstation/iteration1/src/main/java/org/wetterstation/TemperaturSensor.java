package org.wetterstation;

import java.util.Map;  // for the first iteration, database is simulated as simple HashMap

public class TemperaturSensor extends Sensor {
    public TemperaturSensor(Map<String,Double> database)
    {
        super("Temperatur_Sensor", database);
    }

    @Override
    public double measureData() {
        // here we simulate getting data from the sensor
        double data = Math.random() * 100;
        return data;
    }

    @Override
    public void saveData(double data) {
        this.database.put(this.id, data);
    }
}