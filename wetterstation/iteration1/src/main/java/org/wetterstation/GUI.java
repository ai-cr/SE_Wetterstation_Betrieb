package org.wetterstation;

import java.util.Map;
import java.util.Scanner;

public class GUI {
    private final Communicator communicator;

    public GUI(Communicator communicator) {
        this.communicator = communicator;
    }

    public void displaySensorData() {
        Map<String,Double> sensorData = this.communicator.getSensorData();
        System.out.println(sensorData);
    }

    public void promptPowerCommand() {
        Scanner scanner = new Scanner(System.in);
        boolean validUserInput = false;
        while (!validUserInput) {
            System.out.println("Zum einschalten 'ON' eingeben, zum ausschalten 'OFF' eingeben.");
            String input = scanner.nextLine().trim().toUpperCase();
            System.out.println("USER HAS INPUT: " + input);
            switch (input) {
                case "ON":
                    this.communicator.powerOn();
                    validUserInput = true;
                    break;
                case "OFF":
                    this.communicator.powerOff();
                    validUserInput = true;
                    break;
                default:
                    System.out.println("Entweder 'ON' oder 'OFF' eingeben!");
            }
        }
    }
}
