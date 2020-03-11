/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LedTargetRings;
import frc.robot.subsystems.LimeLightTarget;

public class AlignToTarget extends CommandBase {
  private LimeLightTarget lime_light;
  private DriveTrain drive_train;
  private LedTargetRings rings;
  private double tv;
  private static double power = 0.4;

  /**
   * Creates a new AlignToTarget.
   */
  public AlignToTarget(LimeLightTarget m_lime, DriveTrain m_drive, LedTargetRings _rings) {
    rings = _rings;
    lime_light = m_lime;
    drive_train = m_drive;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lime_light.setCameraMode(RobotMap.ll_vision);
    lime_light.setLedMode(RobotMap.ll_off);
    rings.turnOn();
    drive_train.setDriveDirection(RobotMap.direction_backward);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      tv = lime_light.getTV();  
      if (tv == 0) this.end(true);

      double tx = lime_light.getTX();

      //move to the target at the proper forward speed and X center adjustment speed
      if (tx <= -3) {
        drive_train.drive(0, -power);
        System.out.println("AlignToTarget:-"+power+" power: TX="+tx);
      }
      else if (tx >= 3) {
          drive_train.drive(0, power);
          System.out.println("AlignToTarget:+"+power+" power: TX="+tx);
      }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotMap.current_distance_to_target = lime_light.getDistanceToTarget();
    rings.turnOff();
    drive_train.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
