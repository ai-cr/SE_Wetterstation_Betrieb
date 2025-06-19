package org.wetterstation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class GUI {
    private static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
    private static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
    private static final int UPDATE_INTERVAL = 1000;

    private final Communicator communicator;
    private final DefaultTableModel tableModel;

    public GUI(Communicator communicator) {
        this.communicator = communicator;
        tableModel = new DefaultTableModel(new Object[]{"Sensor", "Wert", "Einheit", "Zeit", "Zustand"}, 0);
    }

    public void showGUI() {
        SwingUtilities.invokeLater(this::createGUI);
        new Timer(UPDATE_INTERVAL, (ActionEvent e) -> updateGUI()).start();
    }

    private void createGUI() {
        JFrame frame = new JFrame("Wetterstation - Betrieb");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new BorderLayout());

        JTable dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);

        JButton onButton = new JButton("Einschalten");
        JButton offButton = new JButton("Ausschalten");

        onButton.addActionListener(e -> communicator.powerOn());
        offButton.addActionListener(e -> communicator.powerOff());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(onButton);
        buttonPanel.add(offButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    void updateGUI() {
        List<List<Object>> data = communicator.getSensorData();

        SwingUtilities.invokeLater(() -> {
            tableModel.setRowCount(0); // Clear old data

            for (List<Object> entry : data) {
                String name = (String) entry.get(0);
                Object value = entry.get(1);
                Object timestamp = entry.get(2);

                String label = name;
                String einheit = "";
                String zustand = "OK";

                if (value == null) {
                    value = "N/A";
                    zustand = "!Wartungsdienst kontaktiert!";
                }

                switch (name) {
                    case "Temperatur_Sensor" -> {
                        label = "Temperatur";
                        einheit = "°C";
                    }
                    case "Windstaerke_Sensor" -> {
                        label = "Windstärke";
                        einheit = "km/h";
                    }
                    case "Luftdruck_Sensor" -> {
                        label = "Luftdruck";
                        einheit = "hPa";
                    }
                }

                tableModel.addRow(new Object[]{label, value, einheit, timestamp, zustand});
            }
        });
    }

    // For testing
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

}
