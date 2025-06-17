package org.wetterstation;

public class LuftdruckSensor extends Sensor {
    private static final double MIN_AIR_PRESSURE = 0.0;     // Lowest air pressure possible
    private static final double MAX_AIR_PRESSURE = 2000.0;  // If the air pressure is higher than this, the sensor will break

    public LuftdruckSensor(Database database)
    {
        super("Luftdruck_Sensor", database);
    }

    @Override
    public double measureData() {
        // simulaiton of getting data from sensor
        double data = MIN_AIR_PRESSURE + Math.random() * (MAX_AIR_PRESSURE - MIN_AIR_PRESSURE);
        return data;
    }

    @Override
    public Double sanitizeData(double data) {
        if (data < MIN_AIR_PRESSURE || data > MAX_AIR_PRESSURE) {
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