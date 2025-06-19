package org.wetterstation;

import java.nio.file.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DatabaseTest {

    // Test for Requirement 2.4 (Sofern keine Datenbank existiert (Erstinbetriebnahme) muss diese erstellt werden.
    @Test
    void testDatabaseGetsCreatedAtFirstUse() {
        Path dbPath = Paths.get("sensor_data.db");
        Database database = Database.getInstance();
        assertTrue(Files.exists(dbPath));
    }
}
