/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveInInches2 extends CommandBase {
  private DriveTrain drive_train;
  private double inches_or_angle = 0;
  private String direction = "F";
  private double endDistance;
  private double d_power = 0;  //drive power
  private double r_power = 0;  //rotate power

  /**
   * Creates a new DriveInInches2.
   */
  public DriveInInches2(DriveTrain dt, double _inches_or_angle, String _direction) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive_train = dt;
    inches_or_angle = _inches_or_angle;
    direction = _direction;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //setup the encoder distances
    if (direction.equals("F") || direction.equals("B"))
      endDistance = drive_train.getLeftEncoderDistance() + (inches_or_angle * RobotMap.rotations_per_inch);
    else if (direction.equals("R") || direction.equals("L"))
      endDistance = drive_train.getLeftEncoderDistance() + (inches_or_angle * RobotMap.roations_per_angle);

    //setup the y power
    if (direction.equals("F") d_power = -RobotMap.din_power;
    else if (direction.equals("B") d_power = RobotMap.din_power;
    else if (direction.equals("R") r_power = RobotMap.din_rotatepower;
    else if (direction.equals("L") r_power = -RobotMap.din_rotatepower;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive_train.drive(d_power, r_power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive_train.drive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (direction.equals("F") || direction.equals("B"))
      if (Math.abs(drive_train.getDistance()) >= endDistance) 
        { drive_train.resetEncoders(); return true; }
    else if (direction.equals("R") || direction.equals("L"))
      if (Math.abs(drive_train.getRightEncoderDistance()) > endDistance) 
        { drive_train.resetEncoders(); return true; }

    return false;
  }
}
