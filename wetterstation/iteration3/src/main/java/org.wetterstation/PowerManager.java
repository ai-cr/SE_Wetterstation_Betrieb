package org.wetterstation;


/*
 * PowerManager, simuliert die Stromversorgung der Wetterstation.
 */
public final class PowerManager {
    private static PowerManager INSTANCE;
    private boolean isPoweredOn = false;

    private PowerManager () {}

    public static PowerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PowerManager();
        }
        return INSTANCE;
    }

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
