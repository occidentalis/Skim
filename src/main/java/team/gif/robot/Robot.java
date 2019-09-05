package team.gif.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import team.gif.robot.commands.DriveRobotOriented;
import team.gif.robot.subsystems.Drivetrain;

public class Robot extends TimedRobot {

    private Drivetrain drive;

    @Override
    public void robotInit() {
        drive = Drivetrain.getInstance();
    }

    @Override
    public void robotPeriodic() {
        Scheduler.getInstance().run();
        double[] modAngles = drive.getModuleAngles();
        System.out.println("Drive Heading" + drive.getHeading());
        System.out.println("FR Module: " + modAngles[0] + ", RL Module: " + modAngles[1]);
    }

    @Override
    public void autonomousInit() {

    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void teleopPeriodic() {
    }

}
