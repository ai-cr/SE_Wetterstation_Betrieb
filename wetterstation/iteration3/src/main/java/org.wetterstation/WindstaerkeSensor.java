package org.wetterstation;

public class WindstaerkeSensor extends Sensor {
    private static final double MIN_WIND_SPEED = 0.0; // Wind cannot be negative
    private static final double MAX_WIND_SPEED = 1000.0; // Wind at such a speed will damage the sensor

    public WindstaerkeSensor(Database database)
    {
        super("Windstaerke_Sensor", database);
    }

    @Override
    public double measureData() {
        // here we simulate getting data from the sensor
        double data = MIN_WIND_SPEED + Math.random() * (MAX_WIND_SPEED - MIN_WIND_SPEED);
        return data;
    }

    @Override
    public Double sanitizeData(double data) {
        if (data < MIN_WIND_SPEED || data > MAX_WIND_SPEED) {
            return null;
        }
        return data;
    }

    @Override
    public void saveData(Double data) {
        this.database.saveData(this.id, data);
    }
}