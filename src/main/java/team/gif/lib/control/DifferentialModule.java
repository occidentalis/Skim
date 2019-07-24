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
    private final PIDController controller;

    public DifferentialModule(int motorID_1, int motorID_2, int encoderID, Vector2d modulePos) {
        motor1 = new CANSparkMax(motorID_1, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor2 = new CANSparkMax(motorID_2, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor1.setIdleMode(CANSparkMax.IdleMode.kCoast);
        motor2.setIdleMode(CANSparkMax.IdleMode.kCoast);
        encoder = new AnalogPotentiometer(encoderID, 2*Math.PI, 0);
        this.modulePos = modulePos;
        controller = new PIDController(0.1, 0, 0);
    }

    public void set(Vector2d targetVec) {
        double currAngle = getAngle();
        currAngle = (motor1.getEncoder().getPosition() + motor2.getEncoder().getPosition()) / 2 / 15 * (2*Math.PI);
        double targetAngle = targetVec.getDirection();
        double angleError = MathUtil.wrapHalfRad(targetAngle - currAngle);
        System.out.println("currAngle: " + MathUtil.wrapRad(currAngle) + ", targetAngle: " + targetAngle);
        double turnPercent = controller.compute(currAngle, currAngle + angleError);
        motor1.set(turnPercent + targetVec.getMagnitude());
        motor2.set(turnPercent - targetVec.getMagnitude());
    }

    public void setRaw(double motor1Percent, double motor2Percent) {
        motor1.set(motor1Percent);
        motor2.set(motor2Percent);
    }

    public double getAngle() {
        return encoder.get();
    }

    public double getMotorAngle() {
        return (motor1.getEncoder().getPosition() + motor2.getEncoder().getPosition()) / 2 / 15 * (2*Math.PI);
    }

    public Vector2d getModulePos() {
        return modulePos;
    }

}
