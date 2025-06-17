package org.wetterstation;

public abstract class Sensor {
    protected final String id;
    protected final Database database;

    public Sensor(String id, Database database)
    {
        this.id = id;
        this.database = database;
    }

    public abstract double measureData();
    public abstract Double sanitizeData(double data);
    public abstract void saveData(double data);
}
