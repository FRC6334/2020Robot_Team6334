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

public class DriveToBall extends CommandBase {
  private LimeLightBall lime_light;
  private DriveTrain drive_train;
  private double shoot_distance;
  private double tv;
  private double dist;

  /**
   * Creates a new DriveToTarget.
   */
  public DriveToBall(LimeLightBall m_lime, DriveTrain m_drive, double _shootdistance) {
    // Use addRequirements() here to declare subsystem dependencies    
    lime_light = m_lime;
    drive_train = m_drive;
    shoot_distance = _shootdistance;
    tv = 0;
    dist = 0;
  }

  public DriveToBall(LimeLightBall m_lime, DriveTrain m_drive) {
    this(m_lime, m_drive, RobotMap.ball_distance);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    lime_light.setCameraMode(RobotMap.ll_vision);
    lime_light.setLedMode(RobotMap.ll_off);
    drive_train.setDriveDirection(RobotMap.direction_forward);

    //check if we can see a target
    tv = lime_light.getTV();

    //spin around and look for a target, if not target found in 10 seconds exit the routine
    Timer t = new Timer();
    t.start();
    while (tv == 0 && t.get() < 10) {
      drive_train.drive(0, 0.5);
      tv = lime_light.getTV();
    }
    
    t.stop();
    if (tv == 0) this.end(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      //check if we can see a target
      tv = lime_light.getTV();  
      if (tv == 0) this.end(true);

      //once we find a target get the distance and X offset
      dist = lime_light.getDistanceToTarget();  
      double tx = lime_light.getTX();

      //calculate speed as a function of distance to shooting distance
      double speed_adj = -Math.exp(-((dist-shoot_distance)-46)/10)+100;
      speed_adj *= RobotMap.y_speed / 100;
      if (speed_adj < 0.2) speed_adj = 0.2;
      if (speed_adj > 1.0) speed_adj = 1.0;

      //calculate center adjustment as a function of motor power to X offset
      //the larger the X offset the more motor power to turn and get back to x = 0
      double center_adj = Math.pow(tx/5, 2);
      center_adj *= RobotMap.x_speed / 10;
      if (center_adj < 0.3) center_adj = 0.3;
      if (center_adj > 0.7) center_adj = 0.7;

      //move to the target at the proper forward speed and X center adjustment speed
      if (tx < 9) {
        drive_train.drive(-speed_adj, -center_adj);
        alignReport(10, tv, tx, -speed_adj, -center_adj, dist);
      }
      else if (tx > 4) {
          drive_train.drive(-speed_adj, center_adj);
          alignReport(11, tv, tx, -speed_adj, center_adj, dist);
      }
      else {
        drive_train.drive(-speed_adj, 0);
        alignReport(12, tv, tx, -speed_adj, center_adj, dist);
      }
  }

  private void alignReport(int id, double tv, double tx, double speed, double turn, double dist) {
    System.out.println(id+": TV="+tv+", TX="+tx+", Yspeed="+speed+", Xspeed="+turn+", Dist="+dist+", Driven="+drive_train.getDistance());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive_train.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (dist <= shoot_distance+1) {
      new frc.robot.commands.DriveInInches2(drive_train, 20, "F").schedule();
      return true;
    }
    return false;
  }
}