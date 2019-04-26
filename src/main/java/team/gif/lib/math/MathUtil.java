package team.gif.lib.math;

public class MathUtil {

    /**
     * Constrains {@code x} to be between {@code -bound} and {@code +bound}.
     *
     * @param x value to constrain
     * @param bound magnitude of bounds
     * @return value between {@code -bound} and {@code +bound}
     */
    public static double constrain(double x, double bound) {
        return constrain(x, -bound, bound);
    }

    /**
     * Constrains {@code x} to be between {@code min} and {@code max}.
     *
     * @param x value to constrain
     * @param min lower bound
     * @param max upper bound
     * @return value between {@code min} and {@code max}
     */
    public static double constrain(double x, double min, double max) {
        if (x > max) {
            return max;
        } else if (x < min){
            return min;
        } else {
            return x;
        }
    }

    /**
     * Re-maps a value from one range to another. The value is not constrained within the bounds, but remains scaled.
     * Reversing the output bounds will also reverse the output.
     *
     * @param x value to be mapped
     * @param inMin minimum input bound
     * @param inMax maximum input bound
     * @param outMin minimum output bound
     * @param outMax maximum output bound
     * @return re-mapped value
     */
    public static double map(double x, double inMin, double inMax, double outMin, double outMax) {
        return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
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
     * Adjusts {@code x} so that it is between {@code min} and {@code 2pi}.
     *
     * @param x value to wrap
     * @param min minimum bound
     * @param max maximum bound
     * @return wrapped value within bounds
     */
    public static double wrap(double x, double min, double max) {
        while (x >= max) {
            x -= max - min;
        }
        while (x < min) {
            x += max - min;
        }
        return x;
    }

}
