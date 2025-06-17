package org.wetterstation;

public class TemperaturSensor extends Sensor {
    private static final double MIN_TEMPERATURE = -273.15; // Lowest temperature possible
    private static final double MAX_TEMPERATURE = 300.0;  // If the temperature is higher than this, the sensor will melt

    public TemperaturSensor(Database database)
    {
        super("Temperatur_Sensor", database);
    }

    @Override
    public double measureData() {
        // simulaiton of getting data from sensor
        double data = MIN_TEMPERATURE + Math.random() * (MAX_TEMPERATURE - MIN_TEMPERATURE);
        return data;
    }

    @Override
    public Double sanitizeData(double data) {
        if (data < MIN_TEMPERATURE || data > MAX_TEMPERATURE) {
            return null;  // TODO: in next iteration, contact Wartungsdienst
        }
        return data;
    }

    @Override
    public void saveData(double data) {
        data = sanitizeData(data);
        this.database.saveData(this.id, data);
    }
}