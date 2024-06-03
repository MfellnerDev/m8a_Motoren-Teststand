package threading;

/**
 * A simple PID controller implementation. The coefficients kp, ki and kd
 * determine the correction according to the proportional, integral, and
 * derivative error terms.
 * <p>
 * There is no explicit notion of time; it is assumed that {@linkplain #step(float) steps}
 * happen in regular time intervals and the size of these time intervals needs
 * to be taken into account in the PID coefficients.
 *
 * @author Clemens Koza
 */
public class PidController {
    private float kp;
    private float ki;
    private float kd;

    private float previousError = 0;
    private float integral = 0;

    /**
     * Creates a new PID controller with the given coefficients.
     *
     * @param kp the coefficient for the proportional term
     * @param ki the coefficient for the integral term
     * @param kd the coefficient for the derivative term
     */
    public PidController(float kp, float ki, float kd) {
        setCoefficients(kp, ki, kd);
    }

    /**
     * Adjusts this controller's coefficients.
     *
     * @param kp the coefficient for the proportional term
     * @param ki the coefficient for the integral term
     * @param kd the coefficient for the derivative term
     */
    public void setCoefficients(float kp, float ki, float kd) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
    }

    /**
     * Given the current error, determines the appropriate correction amount
     * and updates the controller's state.
     *
     * @param error the current error that the PID controller observes
     * @return the correction amount according to the controller's coefficients
     */
    public float step(float error) {
        float proportional, derivative;

        proportional = error;
        integral += error;
        derivative = error - previousError;

        float correction = kp * proportional + ki * integral + kd * derivative;
        previousError = error;
        return correction;
    }
}
