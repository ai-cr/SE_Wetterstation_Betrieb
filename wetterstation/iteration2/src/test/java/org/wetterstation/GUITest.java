package org.wetterstation;

import org.junit.jupiter.api.Test;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class GUITest {

    @Test
    void guiDisplaysCurrentSensorData() throws Exception {
        Database database = new Database();
        TemperaturSensor temperaturSensor = new TemperaturSensor(database);
        LuftdruckSensor luftdruckSensor = new LuftdruckSensor(database);
        PowerManager powerManager = new PowerManager();
        Communicator communicator = new Communicator(database, powerManager);
        GUI gui = new GUI(communicator);

        double temp = temperaturSensor.measureData();
        double pressure = luftdruckSensor.measureData();
        temperaturSensor.saveData(temp);
        luftdruckSensor.saveData(pressure);

        SwingUtilities.invokeAndWait(gui::updateDisplay);

        assertEquals("Temperatur: " + temp, getLabelText("temperaturLabel"));
        assertEquals("Luftdruck: " + pressure, getLabelText("luftdruckLabel"));
    }

    private String getLabelText(String fieldName) {
        try {
            var field = GUI.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return ((JLabel) field.get(null)).getText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
