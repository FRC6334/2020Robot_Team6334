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

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SetBallShooterDistance extends InstantCommand {
  private double distance;
  private BallShooter ballshooter;
  private double v;
 

  public SetBallShooterDistance(BallShooter _b, double _d) {
    // Use addRequirements() here to declare subsystem dependencies.
    ballshooter = _b;
    distance = _d;
    v = 0;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (distance == RobotMap.ball_shooter_auto) {
     if (RobotMap.current_distance_to_target <= 120) 
        distance = RobotMap.ball_shooter_50s;
     else if (RobotMap.current_distance_to_target > 120 && RobotMap.current_distance_to_target <= 140)
        distance = RobotMap.ball_shooter_60;
     else if (RobotMap.current_distance_to_target > 140 && RobotMap.current_distance_to_target <= 160)
        distance = RobotMap.ball_shooter_68;
     else if (RobotMap.current_distance_to_target > 160 && RobotMap.current_distance_to_target <= 170)
        distance = RobotMap.ball_shooter_70;
     else if (RobotMap.current_distance_to_target > 170 && RobotMap.current_distance_to_target <= 180)
        distance = RobotMap.ball_shooter_80;
     else if (RobotMap.current_distance_to_target > 180 && RobotMap.current_distance_to_target <= 190)
        distance = RobotMap.ball_shooter_90;
     else if (RobotMap.current_distance_to_target > 190)
        distance = RobotMap.ball_shooter_100;
     else distance = RobotMap.ball_shooter_68;  
    }


    ballshooter.setSpeed(distance);

    if (distance == RobotMap.ball_shooter_80) v = RobotMap.ball_shooter_80_v;
    else if (distance == RobotMap.ball_shooter_60) v = RobotMap.ball_shooter_60_v;
    else if (distance == RobotMap.ball_shooter_68) v = RobotMap.ball_shooter_68_v;
    else if (distance == RobotMap.ball_shooter_50s) v = RobotMap.ball_shooter_50s_v;
    else if (distance == RobotMap.ball_shooter_90) v = RobotMap.ball_shooter_90_v;
    else if (distance == RobotMap.ball_shooter_100) v = RobotMap.ball_shooter_100_v;
    else if (distance == RobotMap.ball_shooter_70) v = RobotMap.ball_shooter_70_v;
  }

  // Called when the command is initially scheduled.
  @Override
  public void execute() {
    while (ballshooter.getVelocity() > v) ;
  }
}