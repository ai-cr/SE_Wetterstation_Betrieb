package org.wetterstation;

import org.junit.jupiter.api.Test;

import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;

import static org.junit.jupiter.api.Assertions.*;

public class GUITest {

    @Test
    void guiTableUpdatesWithCurrentSensorData() throws Exception {
        Database database = Database.getInstance();
        TemperaturSensor tempSensor = new TemperaturSensor(database);
        WindstaerkeSensor windSensor = new WindstaerkeSensor(database);
        LuftdruckSensor pressureSensor = new LuftdruckSensor(database);
        PowerManager powerManager = PowerManager.getInstance();
        powerManager.powerOn();
        Communicator communicator = new Communicator(database, powerManager);
        GUI gui = new GUI(communicator);

        double temp = tempSensor.measureData();
        double wind = windSensor.measureData();
        double pressure = pressureSensor.measureData();
        tempSensor.saveData(temp);
        windSensor.saveData(wind);
        pressureSensor.saveData(pressure);

        gui.updateGUI();
        Thread.sleep(100);

        DefaultTableModel model = gui.getTableModel();
        int rowCount = model.getRowCount();

        assertEquals(3, rowCount);

        boolean tempFound = false, windFound = false, pressureFound = false;

        for (int i = 0; i < rowCount; i++) {
            String sensorName = model.getValueAt(i, 0).toString();
            Object value = model.getValueAt(i, 1);

            switch (sensorName) {
                case "Temperatur" -> {
                    assertEquals(temp, value);
                    tempFound = true;
                }
                case "Windstärke" -> {
                    assertEquals(wind, value);
                    windFound = true;
                }
                case "Luftdruck" -> {
                    assertEquals(pressure, value);
                    pressureFound = true;
                }
            }
        }

        assertTrue(tempFound && windFound && pressureFound);
    }
}
