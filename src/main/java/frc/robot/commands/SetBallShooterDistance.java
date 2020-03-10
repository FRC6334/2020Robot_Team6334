/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.BallShooter;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SetBallShooterDistance extends InstantCommand {
  private double distance;
  private BallShooter ballshooter;
  private double v;
  private double RPM;
 

  public SetBallShooterDistance(BallShooter _b, double _d) {
    // Use addRequirements() here to declare subsystem dependencies.
    ballshooter = _b;
    //distance = _d;
    distance = -0.55;
    v = 0;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
     if (RobotMap.current_distance_to_target == -999)ballshooter.setSpeed(RobotMap.ball_shooter_68);
      else{
     RPM = 0.628 * 8 * (((0.00688033 * RobotMap.current_distance_to_target) * Math.sqrt(80057441 * RobotMap.current_distance_to_target - 82901907 * 98.25))/(RobotMap.current_distance_to_target-1.03553 * 98.25));
      v = RPM;
      }
      
    }


    private Timer increTime = new Timer();
    

  // Called when the command is initially scheduled.
  @Override
  public void execute() {
    while (Math.abs(ballshooter.getVelocity()) < v){
         distance -= 0.1;
         ballshooter.setSpeed(distance);
         increTime.start();
         while (increTime.get() < 0.05);
         increTime.stop();
         increTime.reset();
    }
  }
}
