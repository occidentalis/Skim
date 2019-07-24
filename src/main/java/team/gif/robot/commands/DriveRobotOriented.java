package team.gif.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import team.gif.lib.math.MathUtil;
import team.gif.lib.math.Vector2d;
import team.gif.robot.OI;
import team.gif.robot.subsystems.Drivetrain;

public class DriveRobotOriented extends Command {

    private Drivetrain drive = Drivetrain.getInstance();

    public DriveRobotOriented() {
        requires(drive);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        Vector2d transVecF = new Vector2d(0.25*MathUtil.deadband(OI.getInstance().driver.getX(GenericHID.Hand.kLeft), 0.05),
                0.25*MathUtil.deadband(-OI.getInstance().driver.getY(GenericHID.Hand.kLeft), 0.05));
        double rotVal = 0.2 * MathUtil.deadband(OI.getInstance().driver.getX(GenericHID.Hand.kRight), 0.05);
        drive.set(0, transVecF, rotVal);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {

    }
}
