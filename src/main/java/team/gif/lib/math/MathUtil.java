package team.gif.lib.math;

public class MathUtil {

    /**
     * Re-maps a value from one range to another. The value is not constrained within the bounds, but remains scaled.
     * Reversing the output bounds will also reverse the output.
     *
     * @param value value to be mapped
     * @param minIn minimum input
     * @param maxIn maximum input
     * @param minOut minimum output
     * @param maxOut maximum output
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
     * @return clamped value
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
     * @return wrapped value
     */
    public static double wrap(double value, double min, double max) {
        double range = max - min;
        value %= range;
        if (value >= max) {
            return value - range;
        } else if (value < min) {
            return value + range;
        } else {
            return value;
        }
    }

    public static double wrapRad(double angle) {
        return wrap(angle, 0, 2*Math.PI);
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

}
