/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallIntake;
import edu.wpi.first.wpilibj.Joystick;

public class DriveBallIntakeSpeed extends CommandBase {
  private Joystick stick;
  private BallIntake ballintake;

  /**
   * Creates a new DriveBallIntakeSpeed.
   */
  public DriveBallIntakeSpeed(BallIntake _bi, Joystick _js) {
    // Use addRequirements() here to declare subsystem dependencies.
    ballintake = _bi;
    stick = _js;
    addRequirements(ballintake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ballintake.setSpeed(stick.getY());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ballintake.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
