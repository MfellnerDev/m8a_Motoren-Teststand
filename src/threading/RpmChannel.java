package threading;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Thread-Safe Rpm data storage class
 *
 * @author Manuel Fellner
 * @version 03.06.2024
 */
public class RpmChannel {
    private LinkedBlockingDeque<Float> rpmValues;

    public RpmChannel()  {
        this.rpmValues = new LinkedBlockingDeque<>();
    }

    public void addRpm (float newRpm)  {
        this.rpmValues.push(newRpm);
    }

    public float getRpm () throws InterruptedException {
        return this.rpmValues.take();
    }

    public int getRpmChannelLength()  {
        return this.rpmValues.size();
    }
}
