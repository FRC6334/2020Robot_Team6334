/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.LimeLightBall;
//import edu.wpi.first.wpilibj2.command.Command;

public class DriveInInches2 extends CommandBase {
  private DriveTrain drive_train;
  private double inches_or_angle = 0;
  private String direction = "F";
  private double endDistance;
  private double d_power = 0;  //drive power
  private double r_power = 0;  //rotate power
  private LimeLightBall ll_ball;
  private double tv;

  /**
   * Creates a new DriveInInches2.
   */
  public DriveInInches2(DriveTrain dt, double _inches_or_angle, String _direction, LimeLightBall _ll) {
    // Use addRequirements() here to declare subsystem dependencies.
    drive_train = dt;
    inches_or_angle = _inches_or_angle;
    direction = _direction;
    ll_ball = _ll;
    tv = 0;
  }

  public DriveInInches2(DriveTrain dt, double _inches_or_angle, String _direction){
    this(dt, _inches_or_angle, _direction, null);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive_train.resetEncoders();
    Timer t = new Timer();
    t.start();
    while (t.get() < 0.08);
    t.stop();

    if(ll_ball != null) {
      ll_ball.setLedMode(RobotMap.ll_off);
      ll_ball.ledRingOn();
    }

    //setup the encoder distances
    if (direction.equals("F") || direction.equals("B"))
      endDistance = drive_train.getLeftEncoderDistance() + (inches_or_angle * RobotMap.rotations_per_inch);
    else if (direction.equals("R") || direction.equals("L"))
      endDistance = drive_train.getLeftEncoderDistance() + (inches_or_angle * RobotMap.roations_per_angle);

    //setup the drive and rotate power
    if (direction.equals("F")) d_power = RobotMap.din_power;
    else if (direction.equals("B")) d_power = -RobotMap.din_power;
    else if (direction.equals("R")) r_power = RobotMap.din_rotatepower;
    else if (direction.equals("L")) r_power = -RobotMap.din_rotatepower;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (direction.equals("F") && ll_ball != null) {
      r_power = alignToBall();
    }

    drive_train.drive_in_inches(d_power, r_power);
  }

  //get rotate value to line up robot with ball
  private double alignToBall(){
    tv = ll_ball.getTV();
    if (tv == 1){
      double tx = ll_ball.getTX();

      if (tx < 8) {
        return -0.4;
      }
      else if (tx > 5) {
        return 0.4;
      }
    }
    return 0;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //drive_train.drive(0,0);
    ll_ball.ledRingOff();
    System.out.println("END");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("DIN2: current="+Math.abs(drive_train.getLeftEncoderDistance())+", goal="+endDistance+", d="+d_power+", r="+r_power);

    if (direction.equals("F") || direction.equals("B")) {
        if (Math.abs(drive_train.getLeftEncoderDistance()) >= endDistance)  { 
          drive_train.resetEncoders(); 
          return true; 
        }
    }
    else if (direction.equals("R") || direction.equals("L"))
      if (Math.abs(drive_train.getLeftEncoderDistance()) >= endDistance) { 
        drive_train.resetEncoders(); 
        return true; 
      }

    return false;
  }
}
