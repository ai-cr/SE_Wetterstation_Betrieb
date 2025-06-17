package org.wetterstation;

import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class GUITest {

    // Test for Reqirement 3.1.1 (Es muss eine Benutzeroberfläche für die Dateneinsicht geben, welche die aktuellen Daten anzeigt)
    @Test
    void testUserInterfaceForDataView() {
        HashMap<String, Double> database = new HashMap<>();
        TemperaturSensor temperaturSensor = new TemperaturSensor(database);
        PowerManager powerManager = new PowerManager();
        Communicator communicator = new Communicator(database, powerManager);
        GUI gui = new GUI(communicator);
        double data = temperaturSensor.measureData();
        temperaturSensor.saveData(data);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        gui.displaySensorData();
        assertTrue(out.toString().contains(String.valueOf(data)));
    }
}
