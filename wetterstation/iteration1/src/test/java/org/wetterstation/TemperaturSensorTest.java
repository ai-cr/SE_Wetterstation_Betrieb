package org.wetterstation;

import java.util.HashMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TemperaturSensorTest {

    @Test
    void testGetName() {
        Sensor sensor = new TemperaturSensor(new HashMap<>());
        assertEquals("Temperatur_Sensor", sensor.id);
    }

    // Test for Requirement 2.1 (Sensoren müssen Daten messen können)
    @Test
    void testMeasureDataReturnsData() {
        Sensor sensor = new TemperaturSensor(new HashMap<>());
        assertNotNull(sensor.measureData());
    }

    // Test for Requirement 2.2 (Sensor Daten müssen in lokaler Datenbank gespeichert werden)
    @Test
    void testSaveDataStoresDataInDatabase() {
        HashMap<String, Double> database = new HashMap<>();
        Sensor sensor = new TemperaturSensor(database);
        double data = sensor.measureData();
        sensor.saveData(data);
        assertEquals(data, database.get(sensor.id));
    }

}