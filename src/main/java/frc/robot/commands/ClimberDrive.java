/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RobotClimber;

/**
 * Add your docs here.
 */
public class ClimberDrive extends CommandBase {
    private final RobotClimber m_climber;
    private final Joystick m_stick;

  /**
   * Creates a new TankDrive command.
   *
   * @param left       The control input for the left side of the drive
   * @param right      The control input for the right sight of the drive
   * @param drivetrain The drivetrain subsystem to drive
   */
  public ClimberDrive(Joystick stick, RobotClimber climber) {
    m_climber = climber;
    m_stick = stick;
    addRequirements(m_climber);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    m_climber.drive(m_stick.getY(), m_stick.getX());
    //m_climber.log();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false; // Runs until interrupted
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    m_climber.drive(0, 0);
  }

}
