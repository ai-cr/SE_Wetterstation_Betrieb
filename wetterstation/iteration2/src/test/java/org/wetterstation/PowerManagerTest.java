package org.wetterstation;

import org.junit.jupiter.api.Test;
import org.wetterstation.PowerManager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PowerManagerTest {

    // Test case for Requirement 1.1.1 (WS muss sich manuell einschalten lassen)
    @Test
    void testPowerManagerCanBePoweredOnViaPowerOnFoo() {
        PowerManager powerManager = new PowerManager();
        powerManager.powerOn();
        assertTrue(powerManager.getIsPoweredOn());
    }

    // Test case for Requirement 1.2.1 (WS muss sich manuell ausschalten lassen)
    @Test
    void testPowerManagerCanBePoweredOffViaPowerOffFoo() {
        PowerManager powerManager = new PowerManager();
        powerManager.powerOn();
        powerManager.powerOff();
        assertFalse(powerManager.getIsPoweredOn());
    }
}