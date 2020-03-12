/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.BallShooter;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SetBallShooterDistance extends InstantCommand {
  private double distance;
  private BallShooter ballshooter;
  private double v;
  private Timer increTime;
  private double arg1 = 4500639166632303.0;
  private double arg2 = 4660548288480875.0;
  private double arg3 = 196908165188316992.0;
  private boolean auto;

  public SetBallShooterDistance(BallShooter _b, double _d) {
    // Use addRequirements() here to declare subsystem dependencies.
    ballshooter = _b;
    distance = _d;
    v = 0;
    increTime = new Timer();
    
    if (distance == RobotMap.ball_shooter_auto) auto = true;
    else auto = false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      if (auto) {
        distance = -0.55;

        if (RobotMap.current_distance_to_target == -999)
          v = RobotMap.ball_shooter_68_v;
        else {
          v = 0.5 * 2.73 * ((4.12998 * (Math.pow(10, 9)) * RobotMap.current_distance_to_target)/
          (Math.sqrt((arg1 * RobotMap.current_distance_to_target) - (arg2 * 98.25) + arg3)));
          if (v > 3500) v = 3500;
        }

        System.out.println("SetBallShooterDistance (AUTO): v = " + v + "   Distance = " + RobotMap.current_distance_to_target);
      }
      else {
        if (distance == RobotMap.ball_shooter_80) v = RobotMap.ball_shooter_80_v;
        else if (distance == RobotMap.ball_shooter_60) v = RobotMap.ball_shooter_60_v;
        else if (distance == RobotMap.ball_shooter_68) v = RobotMap.ball_shooter_68_v;
        else if (distance == RobotMap.ball_shooter_50s) v = RobotMap.ball_shooter_50s_v;
        else if (distance == RobotMap.ball_shooter_90) v = RobotMap.ball_shooter_90_v;
        else if (distance == RobotMap.ball_shooter_100) v = RobotMap.ball_shooter_100_v;
        else if (distance == RobotMap.ball_shooter_70) v = RobotMap.ball_shooter_70_v;
        else v = RobotMap.ball_shooter_68_v;
        System.out.println("SetBallShooterDistance (SET): v = " + v + "   Distance = " + RobotMap.current_distance_to_target);
      }
    } 
    
  // Called when the command is initially scheduled.
  @Override
  public void execute() {
    if (auto && RobotMap.current_distance_to_target != -999) {
      while (Math.abs(ballshooter.getVelocity()) < Math.abs(v)){
            distance -= 0.05;
            ballshooter.setSpeed(distance);
            increTime.start();
            while (increTime.get() < 0.03);
            increTime.stop();
            increTime.reset();
      }
      System.out.println("Final velocity = " + ballshooter.getVelocity()); 
    }
    else {
      ballshooter.setSpeed(distance);
      while (Math.abs(ballshooter.getVelocity()) < Math.abs(v)); 
      System.out.println("Set v = " + v);
    }
  }
}
