package team.gif.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import team.gif.lib.math.MathUtil;
import team.gif.lib.math.Vector2d;
import team.gif.robot.OI;
import team.gif.robot.subsystems.Drivetrain;

public class DriveRobotOriented extends Command implements Runnable{

    private final Drivetrain drive = Drivetrain.getInstance();
    private final Notifier notifier;

    public DriveRobotOriented() {
        notifier = new Notifier(this);
        requires(drive);
    }

    @Override
    protected void initialize() {
        notifier.startPeriodic(0.005);
    }

    @Override
    public void run() {
        double x = MathUtil.deadband(OI.getInstance().driver.getX(GenericHID.Hand.kLeft), 0.05);
        double y = MathUtil.deadband(-OI.getInstance().driver.getY(GenericHID.Hand.kLeft), 0.05);
        double rotVal = MathUtil.deadband(OI.getInstance().driver.getX(GenericHID.Hand.kRight), 0.05);
        Vector2d transVecR = new Vector2d(x, y);
        drive.set(0, transVecR.scale(0.1), 0.1 * rotVal);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        notifier.stop();
    }
}
