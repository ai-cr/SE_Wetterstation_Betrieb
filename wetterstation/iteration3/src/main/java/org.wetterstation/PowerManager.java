package org.wetterstation;


/*
 * PowerManager, simuliert die Stromversorgung der Wetterstation.
 */
public class PowerManager {
    private boolean isPoweredOn = false;

    public void powerOff() {
        this.isPoweredOn = false;
    }

    public void powerOn() {
        this.isPoweredOn = true;
    }

    public boolean getIsPoweredOn() {
        return this.isPoweredOn;
    }
}
