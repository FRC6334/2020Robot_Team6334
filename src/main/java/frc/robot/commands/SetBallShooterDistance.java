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
    ballshooter.setSpeed(distance);

    if (distance == RobotMap.ball_shooter_max) v = RobotMap.ball_shooter_max_v;
    else if (distance == RobotMap.ball_shooter_far) v = RobotMap.ball_shooter_far_v;
    else if (distance == RobotMap.ball_shooter_med) v = RobotMap.ball_shooter_med_v;
    else if (distance == RobotMap.ball_shooter_min) v = RobotMap.ball_shooter_min_v;
  }

  // Called when the command is initially scheduled.
  @Override
  public void execute() {
    while (ballshooter.getVelocity() > v) ;
  }
}