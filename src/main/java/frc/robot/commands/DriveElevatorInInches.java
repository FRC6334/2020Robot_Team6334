/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
//import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.BallElevator;
import frc.robot.RobotMap;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveElevatorInInches extends CommandBase {
  private BallElevator ball_elevator;
  private int inches;
  private double speed;

  public DriveElevatorInInches(BallElevator _be, int _in) {
    ball_elevator = _be;
    inches = _in;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ball_elevator.resetEncoders();
    if (inches == 0) 
      this.end(false);
    else if (inches > 0) 
      speed = -RobotMap.ballElevatorSpeed;
    else 
      speed = RobotMap.ballElevatorSpeed;
  }

  @Override
  public void execute() {
    ball_elevator.setSpeed(speed);
  }

  @Override
  public void end(boolean interrupted) {
    ball_elevator.setSpeed(0);
  }

  @Override
  public boolean isFinished() {  
    if (ball_elevator.hasTraveled(inches)) {
      ball_elevator.resetEncoders();
      return true;
    }
  
    return false;
  }

}
