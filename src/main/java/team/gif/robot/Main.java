/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team.gif.robot;

import edu.wpi.first.wpilibj.RobotBase;
import team.gif.lib.math.MathUtil;

/**
 * Do NOT add any static variables to this class, or any initialization at all.
 * Unless you know what you are doing, do not modify this file except to
 * change the parameter class to the startRobot call.
 */
public final class Main {
  private Main() {
  }

  /**
   * Main initialization function. Do not perform any initialization here.
   *
   * <p>If you change your main robot class, change the parameter type.
   */
  public static void main(String... args) {
//    RobotBase.startRobot(Robot::new);

    long t, t1, t2;

    MathUtil.wrap(16, -Math.PI, Math.PI);



    t = System.currentTimeMillis();
    for (int i = -100000; i <= 100000; i++) {
      MathUtil.wrap2(i, -Math.PI, Math.PI);
    }
    t1 = System.currentTimeMillis() - t;

    System.out.println("T1: " + t1);




  }
}
