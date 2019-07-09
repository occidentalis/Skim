package team.gif.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import team.gif.lib.control.DifferentialModule;
import team.gif.lib.math.SwerveMath;
import team.gif.lib.math.Vector2d;
import team.gif.robot.RobotMap;

public class Drivetrain extends Subsystem {

    private static Drivetrain instance;

    private DifferentialModule FRModule, FLModule, RLModule, RRModule;

    private Drivetrain() {
        FRModule = new DifferentialModule(RobotMap.FR_DRIVE_ID_1, RobotMap.FR_DRIVE_ID_2, RobotMap.FR_ENCODER_ID, new Vector2d(8.875, 8.875));
        FLModule = new DifferentialModule(RobotMap.FL_DRIVE_ID_1, RobotMap.FL_DRIVE_ID_2, RobotMap.FL_ENCODER_ID, new Vector2d(-8.875, 8.875));
        RLModule = new DifferentialModule(RobotMap.RL_DRIVE_ID_1, RobotMap.RL_DRIVE_ID_2, RobotMap.RL_ENCODER_ID, new Vector2d(-8.875, -8.875));
        RRModule = new DifferentialModule(RobotMap.RR_DRIVE_ID_1, RobotMap.RR_DRIVE_ID_2, RobotMap.RR_ENCODER_ID, new Vector2d(8.875, -8.875));
    }

    public Drivetrain getInstance() {
        if (instance == null) {
            instance = new Drivetrain();
        }
        return instance;
    }

    public void set(double robotHeading, Vector2d transVecF, double rotVal) {
        Vector2d[] driveVecs = SwerveMath.calculateDriveVector(robotHeading, transVecF, rotVal,
                FRModule.getModulePos(), FLModule.getModulePos(), RLModule.getModulePos(), RRModule.getModulePos());
        FRModule.set(driveVecs[0]);
        FLModule.set(driveVecs[1]);
        RLModule.set(driveVecs[2]);
        RRModule.set(driveVecs[3]);
    }

    @Override
    protected void initDefaultCommand() {
//        setDefaultCommand();
    }
}
