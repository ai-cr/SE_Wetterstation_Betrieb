package org.wetterstation;

import java.util.List;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
    private static final String URL = "jdbc:sqlite:sensor_data.db";


    public Database() {
        try {
            java.sql.Connection conn = DriverManager.getConnection(URL);
            if (conn != null) {
                System.out.println("Keine Datenbank gefunden. Es wird eine Datenbank erstellt..");
                String stmt = "CREATE TABLE IF NOT EXISTS SensorData (" +
                              "id INTEGER PRIMARY KEY," +
                              "name text NOT NULL," +
                              "value REAL," +
                              "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP);";
                conn.createStatement().execute(stmt);
                System.out.println("Datenbank erfolgreich erstellt!");
            }
            else {
                System.out.println("Verbindung zur bestehenden Datenbank erfolgreich hergestellt!");
            }
        } catch (SQLException e) {
            System.err.println("Verbindung zur Datenbank Fehlgeschlagen: " + e.getMessage());
        }
    }

    public void saveData(String name, double value) {
        try (java.sql.Connection conn = DriverManager.getConnection(URL)) {
            String sql = "INSERT INTO SensorData (name, value) VALUES (?, ?)";
            var pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setDouble(2, value);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Konnte Daten nicht in Datenbank speichern: " + e.getMessage());
        }
    }

    public List<List<Object>> getNewestData() {
        // get the newest data from the database, for every different sensor name
        List<List<Object>> data = new ArrayList<>();
        try (java.sql.Connection conn = DriverManager.getConnection(URL)) {
            String sql = "SELECT name, value, timestamp FROM SensorData " +
                         "WHERE id IN (SELECT MAX(id) FROM SensorData GROUP BY name)";
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                double value = rs.getDouble("value");
                String timestamp = rs.getString("timestamp");
                data.add(List.of(name, value, timestamp));
            }
        } catch (SQLException e) {
            System.err.println("Konnte Daten nicht abrufen: " + e.getMessage());
        }
        return data;
    }
}
