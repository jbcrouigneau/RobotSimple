package robot;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Battery {

    private final long CHARGE_TOP = 1000;
    private final long CHARGE_STEP = 10;
    private float chargeLevel;

    public Battery() {
        chargeLevel = 100;
    }

    public void charge() {
        chargeLevel = chargeFunction(chargeLevel);
    }

    private float chargeFunction(float charge) {
        return charge+CHARGE_STEP;
    }

    public void recharger(long dureeEnMs) {
        for (int i = 0; i < dureeEnMs/CHARGE_TOP; i++) {
            charge();
        }
    }

    public float getChargeLevel(){
        return chargeLevel;
    }

    public void use(double energy) throws InsufficientChargeException {
        if (chargeLevel < energy) throw new InsufficientChargeException();
        chargeLevel -= energy;
    }

    public long timeToSufficientCharge(double neededEnergy) {
        int clock = 0;
        float charge = chargeLevel;
        while (charge<neededEnergy) {
            charge = chargeFunction(charge);
            clock++;
        }
        return clock*CHARGE_TOP;
    }

    public boolean canDeliver(double neededEnergy) {
        return (chargeLevel >= neededEnergy);
    }
}
