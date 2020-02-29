/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimeLightBall;
import frc.robot.subsystems.DriveTrain;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;

public class FollowBall extends CommandBase {
  private LimeLightBall lime_light;
  private DriveTrain drive_train;
  private double shoot_distance;
  private double tv;
  private double dist;

  /**
   * Creates a new DriveToTarget.
   */
  public FollowBall(LimeLightBall m_lime, DriveTrain m_drive) {
    // Use addRequirements() here to declare subsystem dependencies    
    lime_light = m_lime;
    drive_train = m_drive;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lime_light.setCameraMode(RobotMap.ll_vision);
    lime_light.setLedMode(RobotMap.ll_on);
    drive_train.setDriveDirection(RobotMap.direction_forward);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      //check if we can see a target
      tv = lime_light.getTV();  
      if (tv == 0) this.end(true);

      double tx = lime_light.getTX();

      //move to the target at the proper forward speed and X center adjustment speed
      if (tx < 3) {
        drive_train.drive(0, -0.3);
      }
      else if (tx > 3) {
          drive_train.drive(0, 0.3);
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive_train.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return false;
  }
}
