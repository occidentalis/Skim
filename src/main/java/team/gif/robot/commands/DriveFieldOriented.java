package team.gif.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import team.gif.lib.control.PIDController;
import team.gif.lib.math.MathUtil;
import team.gif.lib.math.Vector2d;
import team.gif.robot.OI;
import team.gif.robot.subsystems.Drivetrain;

public class DriveFieldOriented extends Command implements Runnable {

    private final Drivetrain drive;
    private final PIDController angleController;
    private final Notifier notifier;
    private double targetheading = 0.0;
    private boolean lastTouch = true;

    public DriveFieldOriented() {
        drive = Drivetrain.getInstance();
        angleController = new PIDController(0.2, 0.0, 0.0);
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
        double rotVal = 0.5 * MathUtil.deadband(OI.getInstance().driver.getX(GenericHID.Hand.kRight), 0.05);
        Vector2d transVecR = new Vector2d(x, y);
        double robotHeading = drive.getHeading();
        drive.set(drive.getHeading(), transVecR.scale(0.5), rotVal);
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
