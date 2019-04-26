package team.gif.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

    private static Drivetrain instance;

//    private DifferentialModule frontRightModule, frontLeftModule, rearLeftModule, rearRightModule;

    private CANSparkMax frontRightMotor1, frontRightMotor2, frontLeftMotor1, frontLeftMotor2,
            rearLeftMotor1, rearLeftMotor2, rearRightMotor1, rearRightMotor2;

    private Drivetrain() {

    }

    public Drivetrain getInstance() {
        if (instance == null) {
            instance = new Drivetrain();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
//        setDefaultCommand();
    }
}
