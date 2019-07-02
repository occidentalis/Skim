package team.gif.lib.math;

public class MathUtil {

    /**
     * Re-maps a value from one range to another. The value is not constrained within the bounds, but remains scaled.
     * Reversing the output bounds will also reverse the output.
     *
     * @param value value to be mapped
     * @param minIn minimum input bound
     * @param maxIn maximum input bound
     * @param minOut minimum output bound
     * @param maxOut maximum output bound
     * @return re-mapped value
     */
    public static double map(double value, double minIn, double maxIn, double minOut, double maxOut) {
        return (value - minIn) * (maxOut - minOut) / (maxIn - minIn) + minOut;
    }

    /**
     * Clamps {@code value} between {@code min} and {@code max}.
     *
     * @param value value to clamp
     * @param min minimum value
     * @param max maximum value
     * @return clamped value between {@code min} and {@code max}
     */
    public static double clamp(double value, double min, double max) {
        if (value > max) {
            return max;
        } else if (value < min){
            return min;
        } else {
            return value;
        }
    }

    /**
     * Wraps {@code value} between {@code min} and {@code max}.
     *
     * @param value value to wrap
     * @param min minimum value
     * @param max maximum value
     * @return wrapped value between {@code min} and {@code max}
     */
    public static double wrap(double value, double min, double max) {
        double range = max - min;
        while (value >= max) {
            value -= range;
        }
        while (value < min) {
            value += range;
        }
        return value;
    }

    public static double wrapRad(double angle) {
        return wrap(angle, 0, 2 * Math.PI);
    }

    public static double wrapHalfRad(double angle) {
        return wrap(angle, -Math.PI, Math.PI);
    }

    public static double wrapDeg(double angle) {
        return wrap(angle, 0, 360);
    }

    public static double wrapHalfDeg(double angle) {
        return wrap(angle, -180, 180);
    }

    /**
     * Finds smallest error for continuous rotary inputs.
     *
     * @param error current error of the controller
     * @param minIn minimum input value
     * @param maxIn maximum input value
     * @return
     */
    public static double absError(double error, double minIn, double maxIn) {
        double range = maxIn - minIn;
        error %= range;
        if (Math.abs(error) > range / 2) {
            if (error > 0) {
                return error - range;
            } else {
                return error + range;
            }
        }
        return error;
    }

}
