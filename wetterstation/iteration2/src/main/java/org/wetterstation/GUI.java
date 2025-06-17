package org.wetterstation;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class GUI {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private static final int UPDATE_INTERVAL = 100;
    private final Communicator communicator;
    private static final JLabel temperaturLabel = new JLabel("Temperatur: ?");
    private static final JLabel luftdruckLabel = new JLabel("Luftdruck: ?");

    public GUI(Communicator communicator) {
        this.communicator = communicator;
    }

    public void promptPowerCommand() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
        new Timer(UPDATE_INTERVAL, (ActionEvent e) -> updateDisplay()).start();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Wetterstation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        JButton onButton = new JButton("Einschalten");
        JButton offButton = new JButton("Ausschalten");

        onButton.addActionListener(e -> communicator.powerOn());
        offButton.addActionListener(e -> communicator.powerOff());

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(temperaturLabel);
        panel.add(luftdruckLabel);
        panel.add(onButton);
        panel.add(offButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    void updateDisplay() {
        List<List<Object>> data = communicator.getSensorData();
        for (List<Object> entry : data) {
            String name = (String) entry.get(0);
            Object value = entry.get(1);
            // todo also use timestamp in next iteration
            switch (name) {
                case "Temperatur_Sensor" -> temperaturLabel.setText("Temperatur: " + value);
                case "Luftdruck_Sensor" -> luftdruckLabel.setText("Luftdruck: " + value);
            }
        }
    }
}
