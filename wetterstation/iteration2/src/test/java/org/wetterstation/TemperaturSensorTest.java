package org.wetterstation;

import org.wetterstation.Sensor;
import org.wetterstation.TemperaturSensor;
import org.wetterstation.Database;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TemperaturSensorTest {

    // Test for Requirement 2.1 (Sensoren müssen Daten messen können)
    @Test
    void testMeasureDataReturnsData() {
        Sensor sensor = new TemperaturSensor(new Database());
        assertNotNull(sensor.measureData());
    }

    // Test for Requirement 2.2 (Sensor Daten müssen in lokaler Datenbank gespeichert werden)
    @Test
    void testSaveDataStoresDataInDatabase() {
        Database database = new Database();
        Sensor sensor = new TemperaturSensor(database);
        double data = sensor.measureData();
        sensor.saveData(data);
        assertEquals(data, database.getNewestData().get(1).get(1));
    }

}