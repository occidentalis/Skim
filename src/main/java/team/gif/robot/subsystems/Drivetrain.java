package team.gif.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.command.Subsystem;
import team.gif.lib.control.DifferentialModule;
import team.gif.lib.math.SwerveMath;
import team.gif.lib.math.Vector2d;
import team.gif.robot.RobotMap;
import team.gif.robot.commands.DriveFieldOriented;
import team.gif.robot.commands.DriveRobotOriented;

public class Drivetrain extends Subsystem {

    private static Drivetrain instance;

    private DifferentialModule FRModule, FLModule, RLModule, RRModule;

    private PigeonIMU pigeon;

    private double[] ypr_deg;

    private Drivetrain() {
        FRModule = new DifferentialModule(RobotMap.FR_DRIVE_ID_1, RobotMap.FR_DRIVE_ID_2, RobotMap.FR_ENCODER_ID, new Vector2d(11.375, 11.375));
//        FLModule = new DifferentialModule(RobotMap.FL_DRIVE_ID_1, RobotMap.FL_DRIVE_ID_2, RobotMap.FL_ENCODER_ID, new Vector2d(-8.875, 8.875));
        RLModule = new DifferentialModule(RobotMap.RL_DRIVE_ID_1, RobotMap.RL_DRIVE_ID_2, RobotMap.RL_ENCODER_ID, new Vector2d(-11.375, -11.375));
//        RRModule = new DifferentialModule(RobotMap.RR_DRIVE_ID_1, RobotMap.RR_DRIVE_ID_2, RobotMap.RR_ENCODER_ID, new Vector2d(8.875, -8.875));

        pigeon = new PigeonIMU(0);

        ypr_deg = new double[3];
    }

    public static Drivetrain getInstance() {
        if (instance == null) {
            instance = new Drivetrain();
        }
        return instance;
    }

    public void set(double robotHeading, Vector2d transVecF, double rotVal) {
//        Vector2d[] driveVecs = SwerveMath.calculateDriveVector(robotHeading, transVecF, rotVal,
//                FRModule.getModulePos(), FLModule.getModulePos(), RLModule.getModulePos(), RRModule.getModulePos());
        Vector2d[] driveVecs = SwerveMath.calculateDriveVector(robotHeading, transVecF, rotVal,
                FRModule.getModulePos(), RLModule.getModulePos());
        FRModule.setVectorPercent(driveVecs[0]);
//        FLModule.set(driveVecs[1]);
        RLModule.setVectorPercent(driveVecs[1]);
//        RRModule.set(driveVecs[3]);
    }

    public double[] getYawPitchRollDeg() {
        pigeon.getYawPitchRoll(ypr_deg);
        return ypr_deg;
    }
    public double getHeading() {
        return Math.toRadians(getYawPitchRollDeg()[0]);
    }

    public double[] getModuleAngles() {
        return new double[]{FRModule.getAngle(), RLModule.getAngle()};
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveFieldOriented());
    }
}
