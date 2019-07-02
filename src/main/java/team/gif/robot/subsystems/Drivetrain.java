package team.gif.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import team.gif.lib.control.DifferentialModule;
import team.gif.lib.math.Vector2d;
import team.gif.robot.RobotMap;

public class Drivetrain extends Subsystem {

    private static Drivetrain instance;

    private DifferentialModule FRModule, FLModule, RLModule, RRModule;

    private Drivetrain() {
        FRModule = new DifferentialModule(RobotMap.FR_DRIVE_ID_1, RobotMap.FR_DRIVE_ID_2, RobotMap.FR_ENCODER_ID, new Vector2d(11, 11));

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
