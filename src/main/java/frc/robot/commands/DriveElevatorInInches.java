/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.BallElevator;
import frc.robot.RobotMap;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveElevatorInInches extends InstantCommand {
  private BallElevator ball_elevator;
  private int inches;

  public DriveElevatorInInches(BallElevator _be, int _in) {
    // Use addRequirements() here to declare subsystem dependencies.
    ball_elevator = _be;
    inches = _in;
    _be.resetEncoders();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (inches > 0)
      while ((ball_elevator.getDistance()*RobotMap.rotations_per_inch_elevator) > -inches) {
        ball_elevator.setSpeed(-RobotMap.ballElevatorSpeed);
      }
    else if (inches < 0)
      while ((ball_elevator.getDistance()*RobotMap.rotations_per_inch_elevator) < -inches) {
        ball_elevator.setSpeed(RobotMap.ballElevatorSpeed); 
      }
    ball_elevator.setSpeed(0.0);
    ball_elevator.resetEncoders();
  }
}
