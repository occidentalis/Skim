package team.gif.lib.control;

import edu.wpi.first.wpilibj.RobotController;
import team.gif.lib.math.MathUtil;

public class PIDController {

    private double kP, kI, kD, kF;
    private double P, I, D, F;
    private double input, output, setpoint;
    private double lastInput, lastOutput;
    private double minOut, maxOut;

    private boolean isInverted;

    public PIDController(double p, double i, double d, double f) {
        kP = p;
        kI = i;
        kD = d;
        kF = f;
    }

    public PIDController(double p, double i, double d) {
        this(p, i, d, 0);
        setOutputLimits(-1.0, 1.0);
    }

    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    public double compute() {
        double error = setpoint - input;

        P = kP * error;
        I += kI * error;
        D = kD * (input - lastInput);
        F = kF * setpoint;

        I = MathUtil.clamp(I, minOut, maxOut);

        output = P + I - D + F;

        output = MathUtil.clamp(output, minOut, maxOut);

        lastInput = input;
        lastOutput = output;

        return output;
    }

    public double compute(double input) {
        this.input = input;
        return compute();
    }

    public double compute(double input, double setpoint) {
        this.input = input;
        this.setpoint = setpoint;
        return compute();
    }

    public double getOutput() {
        return output;
    }

    public void setPID(double p, double i, double d, double f) {
        kP = Math.abs(p);
        kI = Math.abs(i);
        kD = Math.abs(d);
        kF = Math.abs(f);
        if (isInverted) {
            kP *= -1;
            kI *= -1;
            kD *= -1;
            kF *= -1;
        }
    }

    public void setPID(double p, double i, double d) {
        setPID(p, i, d, 0);
    }

    public void setOutputLimits(double min, double max) {
        if (min > max) return;
        minOut = min;
        maxOut = max;
        I = MathUtil.clamp(I, minOut, maxOut);
        output = MathUtil.clamp(output, minOut, maxOut);
    }

    public void setInverted(boolean invert) {
        isInverted = invert;
        setPID(kP, kI, kD, kF);
    }
}
