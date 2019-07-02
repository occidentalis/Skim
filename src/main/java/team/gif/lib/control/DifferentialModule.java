package team.gif.lib.control;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import team.gif.lib.math.MathUtil;
import team.gif.lib.math.Vector2d;

public class DifferentialModule {

    private final CANSparkMax motor1, motor2;
    private final AnalogPotentiometer encoder;
    private final Vector2d modulePos;

    private Vector2d targetVec;

    public DifferentialModule(int motorID_1, int motorID_2, int encoderID, Vector2d modulePos) {
        motor1 = new CANSparkMax(motorID_1, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor2 = new CANSparkMax(motorID_2, CANSparkMaxLowLevel.MotorType.kBrushless);
        encoder = new AnalogPotentiometer(encoderID, 2*Math.PI, 0);
        this.modulePos = modulePos;
    }

    public void set(Vector2d driveVec) {
        targetVec = driveVec;

    }

    public void update() {
        double angleError = targetVec.getDirection() - getAngle();
        angleError = MathUtil.absError(angleError, 0, 2*Math.PI);
        motor1.set(0.1 * angleError + targetVec.getMagnitude());
        motor2.set(0.1 * angleError + targetVec.getMagnitude());
    }

    public double getAngle() {
        return encoder.get();
    }

    public Vector2d getModulePos() {
        return modulePos;
    }
}
