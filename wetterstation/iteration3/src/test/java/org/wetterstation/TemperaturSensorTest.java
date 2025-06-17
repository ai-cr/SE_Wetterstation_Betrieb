package org.wetterstation;

import org.junit.jupiter.api.Test;
import org.wetterstation.Database;
import org.wetterstation.Sensor;
import org.wetterstation.TemperaturSensor;

import static org.junit.jupiter.api.Assertions.*;


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
        assertEquals(data, database.getNewestData().getLast().get(1));
    }

    // Test for Requirement 2.5 (Sensordaten müssen auf Fehler überprüft werden)
    @Test
    void testSensorDataSanityCheck() {
        Database database = new Database();
        Sensor sensor = new TemperaturSensor(database);
        double data = - 274; // Simulating an invalid temperature
        assertNull(sensor.sanitizeData(data));
    }

}