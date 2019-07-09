package team.gif.lib.control;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import team.gif.lib.math.MathUtil;
import team.gif.lib.math.Vector2d;

public class DifferentialModule {

    private final CANSparkMax motor1, motor2;
    private final AnalogPotentiometer encoder;
    private final Vector2d modulePos;

    public DifferentialModule(int motorID_1, int motorID_2, int encoderID, Vector2d modulePos) {
        motor1 = new CANSparkMax(motorID_1, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor2 = new CANSparkMax(motorID_2, CANSparkMaxLowLevel.MotorType.kBrushless);
        encoder = new AnalogPotentiometer(encoderID, 2*Math.PI, 0);
        this.modulePos = modulePos;
    }

    public void set(Vector2d targetVec) {
        double angleError = MathUtil.wrapHalfRad(targetVec.getDirection() - getAngle());
        double rotationError = angleError / (2*Math.PI) * 15;
        double motor1Pos = motor1.getEncoder().getPosition();
        double motor2Pos = motor2.getEncoder().getPosition();
        motor1.getPIDController().setReference(motor1Pos + rotationError, ControlType.kPosition, 0, targetVec.getMagnitude());
        motor2.getPIDController().setReference(motor2Pos + rotationError, ControlType.kPosition, 0, -targetVec.getMagnitude());
    }

    public double getAngle() {
        return encoder.get();
    }

    public Vector2d getModulePos() {
        return modulePos;
    }

}
