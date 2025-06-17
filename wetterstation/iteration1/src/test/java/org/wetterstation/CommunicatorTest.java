package org.wetterstation;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class CommunicatorTest {

    // Test for Requirement 1.1.2 (WS muss sich über das Netzwerk einschalten lassen)
    @Test
    void testCommunicatorCanBePoweredOnViaNetwork() {
        PowerManager powerManager = new PowerManager();
        Communicator communicator = new Communicator(null, powerManager);
        communicator.powerOn();
        assertTrue(powerManager.getIsPoweredOn());
    }

    // Test for Requirement 1.2.2 (WS muss sich über das Netzwerk ausschalten lassen)
    @Test
    void testCommunicatorCanBePoweredOffViaNetwork() {
        PowerManager powerManager = new PowerManager();
        Communicator communicator = new Communicator(null, powerManager);
        communicator.powerOn();
        communicator.powerOff();
        assertFalse(powerManager.getIsPoweredOn());
    }

    // Test for Requirement 2.3 (Die Sensordaten müssen bei neuer Speicherung in der Datenbank von der Zentrale abgerufen werden können)
    @Test
    void testCommunicatorRetrievesSensorDataFromDatabase() {
        HashMap<String, Double> database = new HashMap<>();
        TemperaturSensor temperaturSensor = new TemperaturSensor(database);
        PowerManager powerManager = new PowerManager();
        Communicator communicator = new Communicator(database, powerManager);

        double data = temperaturSensor.measureData();
        temperaturSensor.saveData(data);

        assertNotNull(communicator.getSensorData());
    }
}
