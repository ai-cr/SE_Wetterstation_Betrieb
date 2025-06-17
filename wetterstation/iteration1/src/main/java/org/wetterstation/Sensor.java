package org.wetterstation;

import java.util.Map;  // for the first iteration, database is simulated as a simple HashMap

public abstract class Sensor {
    protected String id;
    protected Map<String, Double> database;

    public Sensor(String id, Map<String, Double> database)
    {
        this.id = id;
        this.database = database;
    }

    public abstract double measureData();
    public abstract void saveData(double data);
}
