package team.gif.lib.math;

public class SwerveMath {

    public static Vector2d[] normalizeVectorBatch(double length, Vector2d... vecs) {
        double maxLength = 0;
        for (Vector2d vec : vecs) {
            if (vec.getMagnitude() > maxLength) {
                maxLength = vec.getMagnitude();
            }
        }
        if (length >= maxLength) {
            return vecs;
        }
        Vector2d[] normVecs = new Vector2d[vecs.length];
        for (int i = 0; i < vecs.length; i++) {
            normVecs[i] = vecs[i].scale(length / maxLength);
        }
        return normVecs;
    }

    /**
     * Works by calculating a "rotation vector" which is perpendicular
     * to the module's position vector and has a magnitude proportional
     * to the desired rotation speed. This vector is added to the
     * translation vector to calculate the drive vector.
     *
     * @param robotHeading robot heading
     * @param rotVal magnitude of rotation (-1.0 to 1.0)
     * @param transVecF field-centric translation vector (joystick input)
     * @param modulePos position vector of module relative to center of rotation
     * @return Vector representing wheel angle and speed
     */
    public static Vector2d calculateDriveVector(double robotHeading, Vector2d transVecF, double rotVal, Vector2d modulePos) {
        Vector2d transVecR = transVecF.rotate(-robotHeading);
        Vector2d rotVec = modulePos.normalize(rotVal).rotate(Math.PI/2);
        Vector2d driveVec = transVecR.add(rotVec);
        return driveVec;
    }

    /**
     * Calculates multiple drive vectors
     */
    public static Vector2d[] calculateDriveVector(double robotHeading, Vector2d transVecF, double rotVal, Vector2d... modulePos) {
        Vector2d[] driveVecs = new Vector2d[modulePos.length];
        for (int i = 0; i < modulePos.length; i++) {
            driveVecs[i] = calculateDriveVector(robotHeading, transVecF, rotVal, modulePos[i]);
        }
        return normalizeVectorBatch(1.0, driveVecs);
    }

}
