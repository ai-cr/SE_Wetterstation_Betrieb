package org.wetterstation;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class CommunicatorTest {

    // Test for Requirement 1.1.2 (WS muss sich über das Netzwerk einschalten lassen)
    @Test
    void testCommunicatorCanBePoweredOnViaNetwork() {
        PowerManager powerManager = PowerManager.getInstance();
        Communicator communicator = new Communicator(null, powerManager);
        communicator.powerOn();
        assertTrue(powerManager.getIsPoweredOn());
    }

    // Test for Requirement 1.2.2 (WS muss sich über das Netzwerk ausschalten lassen)
    @Test
    void testCommunicatorCanBePoweredOffViaNetwork() {
        PowerManager powerManager = PowerManager.getInstance();
        Communicator communicator = new Communicator(null, powerManager);
        communicator.powerOn();
        communicator.powerOff();
        assertFalse(powerManager.getIsPoweredOn());
    }

    // Test for Requirement 2.3 (Die Sensordaten müssen bei neuer Speicherung in der Datenbank von der Zentrale abgerufen werden können)
    @Test
    void testCommunicatorRetrievesSensorDataFromDatabase() {
        Database database = Database.getInstance();
        TemperaturSensor temperaturSensor = new TemperaturSensor(database);
        PowerManager powerManager = PowerManager.getInstance();
        Communicator communicator = new Communicator(database, powerManager);

        double data = temperaturSensor.measureData();
        temperaturSensor.saveData(data);

        assertNotNull(communicator.getSensorData());
    }

    // Test for Requirement 2.6 (Im Falle von fehlerhaften Sensordaten muss der Wartungsservice sofort kontaktiert werden (spätestens innerhalb 1 Minute))
    @Test
    void testCommunicatorContactsMaintenanceServiceOnInvalidSensorData() {
        Database database = Database.getInstance();
        WindstaerkeSensor sensor = new WindstaerkeSensor(database);
        PowerManager powerManager = PowerManager.getInstance();
        Communicator communicator = new Communicator(database, powerManager);

        double data = -1; // simulating invalid data
        Double sanitizedData = sensor.sanitizeData(data);
        sensor.saveData(data);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        if (sanitizedData == null) {
            communicator.contactWartungsdienst(sensor.id);
        }
        assertTrue(out.toString().contains("Wartungsdienst wurde kontaktiert für Sensor: " + sensor.id));
    }
}
