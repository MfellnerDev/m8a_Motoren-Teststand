package threading;

/**
 * The system under test; uses a PID controller to control a motor's speed in RPM; that
 * is simulated by the {@link #actualRpm} Attribute.
 *
 * @author Clemens Koza, Manuel Fellner
 * @version 03.06.2024
 */
public class SystemUnderTest implements Runnable {
    private final PidController controller = new PidController(0.08f, 0.05f, 0.001f);

    private RpmChannel rpmChannel;

    private float targetRpm;
    private float actualRpm;

    private boolean running;

    /**
     * Creates a system under test that is running at the given desired speed.
     *
     * @param initialRpm the initial value for actual and target rpm
     */
    public SystemUnderTest(RpmChannel rpmChannel, float initialRpm) {
        this(initialRpm, initialRpm);
        this.rpmChannel = rpmChannel;
        this.running = true;
    }

    /**
     * Creates a system under test with the given initial actual and target
     * speed.
     *
     * @param initialRpm the initial value for actual rpm
     * @param targetRpm  the initial value for target rpm
     */
    public SystemUnderTest(float initialRpm, float targetRpm) {
        this.actualRpm = initialRpm;
        this.targetRpm = targetRpm;
    }

    public void setTargetRpm(float targetRpm) {
        this.targetRpm = targetRpm;
    }

    public float getTargetRpm() {
        return targetRpm;
    }

    public float getActualRpm() {
        return actualRpm;
    }

    /**
     * Simulates one "step" in which the difference between actual and target
     * rpm is taken, put into the controller, and used to adjust the current
     * speed.
     */
    public void step() {
        float error = targetRpm - actualRpm;
        float correction = controller.step(error);
        actualRpm += correction;
    }

    @Override
    public void run() {
        for (int t = 0; running; t++) {
            if (t == 500) {
                setTargetRpm(700);
            } else if (t == 2500) {
                setTargetRpm(300);
            }
            step();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }

            this.rpmChannel.addRpm(actualRpm);
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
