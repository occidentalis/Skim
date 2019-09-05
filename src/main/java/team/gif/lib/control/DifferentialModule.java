package team.gif.lib.control;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import team.gif.lib.math.MathUtil;
import team.gif.lib.math.Vector2d;
import team.gif.robot.Constants;

public class DifferentialModule {

    private final CANSparkMax motor1, motor2;
    private final AnalogPotentiometer encoder;
    private final Vector2d modulePos;
    private final PIDController angleController, driveController;
//    private final double kV;

    public DifferentialModule(int motorID_1, int motorID_2, int encoderID, Vector2d modulePos) {//, double kV, double wheelDiameter, double gearRatio) {
        motor1 = new CANSparkMax(motorID_1, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor2 = new CANSparkMax(motorID_2, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor1.setIdleMode(CANSparkMax.IdleMode.kCoast);
        motor2.setIdleMode(CANSparkMax.IdleMode.kCoast);
        motor1.setOpenLoopRampRate(0.1);
        motor2.setOpenLoopRampRate(0.1);
        encoder = new AnalogPotentiometer(encoderID);
        this.modulePos = modulePos;
        angleController = new PIDController(0.4, 0, 0);
        driveController = new PIDController(0.1, 0, 0, 0);
//        this.kV = 1.0 / (kV / 60.0 * wheelDiameter * Math.PI / gearRatio * 12);
    }

    public void setAngleAndVelocity(double targetAngle, double targetVelocity) {
        double currAngle = getAngle();
        double angleError = MathUtil.wrapHalfRad(targetAngle - currAngle);
        double rotatePercent = angleController.compute(currAngle, currAngle + angleError);
        double targetMotorVelocity = targetVelocity / (Constants.WHEEL_DIAMETER * Math.PI) * Constants.WHEEL_DIAMETER;

        motor1.getPIDController().setReference(targetMotorVelocity, ControlType.kVelocity, 0, rotatePercent);
        motor2.getPIDController().setReference(-targetMotorVelocity, ControlType.kVelocity, 0, rotatePercent);
    }

    public void setAngleAndPercent(double targetAngle, double percent) {
        double currAngle = getAngle();
        double angleError = MathUtil.wrapHalfRad(targetAngle - currAngle);
        double rotate = angleController.compute(currAngle, currAngle + angleError);
        motor1.set(rotate - percent);
        motor2.set(rotate + percent);
    }

    public void setVectorPercent(Vector2d targetVec) {
        double currAngle = getAngle();
        double targetAngle = targetVec.getDirection();
        double angleError = MathUtil.wrapHalfRad(targetAngle - currAngle);
        double targetVelocity = targetVec.getMagnitude();

        if (targetVelocity < 0.03) { // If input is neutral, maintain position
            setAngleAndPercent(getAngle(), 0.0);
        } else if (Math.abs(angleError) < Math.PI/2) { // Drive at intended angle and velocity
            setAngleAndPercent(targetAngle, targetVelocity);
        } else { // If angle error is greater than 90 degrees, flip drive angle and velocity
            setAngleAndPercent(targetAngle + Math.PI, -targetVelocity);
        }
    }

    public void setVectorVelocity(Vector2d vec) {

    }

    public void setRaw(double motor1Percent, double motor2Percent) {
        motor1.set(motor1Percent);
        motor2.set(motor2Percent);
    }

    public double getAngle() {
        return MathUtil.map(encoder.get(), 0.041, 0.96, 0, 2*Math.PI);
    }

    public double getRPM() {
        return (motor1.getEncoder().getVelocity() - motor2.getEncoder().getVelocity()) / 2;
    }

    public double getAvgMotorAngle() {
        return (motor1.getEncoder().getPosition() + motor2.getEncoder().getPosition()) / 2 / 15 * (2*Math.PI);
    }

    public Vector2d getModulePos() {
        return modulePos;
    }

}
