package org.wetterstation;

import org.junit.jupiter.api.Test;
import org.wetterstation.Database;
import org.wetterstation.WindstaerkeSensor;
import org.wetterstation.Sensor;

import static org.junit.jupiter.api.Assertions.*;


public class WindstaerkeSensorTest {

    // Test for Requirement 2.1 (Sensoren müssen Daten messen können)
    @Test
    void testMeasureDataReturnsData() {
        Sensor sensor = new WindstaerkeSensor(Database.getInstance());
        assertNotNull(sensor.measureData());
    }

    // Test for Requirement 2.2 (Sensor Daten müssen in lokaler Datenbank gespeichert werden)
    @Test
    void testSaveDataStoresDataInDatabase() {
        Database database = Database.getInstance();
        Sensor sensor = new WindstaerkeSensor(database);
        double data = sensor.measureData();
        sensor.saveData(data);
        assertEquals(data, database.getNewestData().getLast().get(1));
    }

    // Test for Requirement 2.5 (Sensordaten müssen auf Fehler überprüft werden)
    @Test
    void testSensorDataSanityCheck() {
        Database database = Database.getInstance();
        Sensor sensor = new WindstaerkeSensor(database);
        double data = -1; // Simulating an invalid Wind speed
        assertNull(sensor.sanitizeData(data));
    }

}