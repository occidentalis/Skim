package team.gif.lib.control;

import team.gif.lib.math.Vector2d;

public class SwerveMath {

    public static Vector2d[] normalizeBatch(double limit, Vector2d... vecs) {
        double max = 0;
        for (Vector2d vec : vecs) {
            if (vec.getMagnitude() > max) {
                max = vec.getMagnitude();
            }
        }
        if (limit >= max) {
            return vecs;
        }
        Vector2d[] normVecs = new Vector2d[vecs.length];
        for (int i = 0; i < vecs.length; i++) {
            normVecs[i] = vecs[i].scale(limit / max);
        }
        return normVecs;
    }


}
