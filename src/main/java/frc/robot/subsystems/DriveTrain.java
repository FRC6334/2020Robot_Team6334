/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;
import frc.robot.subsystems.USBCamera;
//import edu.wpi.first.wpilibj.SPI;

public class DriveTrain extends SubsystemBase {
  /**
   * The DriveTrain subsystem incorporates the sensors and actuators attached to the robots chassis.
   * These include four drive motors, a left and right encoder and a gyro.
   */
  
  //private static final double kAngleSetpoint = 0.0;
	//private static final double kP = 0.005; // propotional turning constant

  private final CANSparkMax leftFrontMotor = new CANSparkMax(RobotMap.leftFrontMotor, MotorType.kBrushless);
  private final CANSparkMax leftBackMotor = new CANSparkMax(RobotMap.leftBackMotor, MotorType.kBrushless);
  private final CANSparkMax rightFrontMotor = new CANSparkMax(RobotMap.rightFrontMotor, MotorType.kBrushless);
  private final CANSparkMax rightBackMotor = new CANSparkMax(RobotMap.rightBackMotor, MotorType.kBrushless);

  private final CANEncoder right_encoder = rightFrontMotor.getEncoder();
  private final CANEncoder left_encoder = leftFrontMotor.getEncoder();

  private final SpeedController m_leftMotor =
      new SpeedControllerGroup(leftFrontMotor, leftBackMotor);
  private final SpeedController m_rightMotor =
      new SpeedControllerGroup(rightFrontMotor, rightBackMotor);

  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  private final USBCamera cam;

  /**
   * Create a new drive train subsystem.
   */
  public DriveTrain(USBCamera _c) {
    super();

    //reset encoders to 0
    this.resetEncoders();
    cam = _c;

    leftBackMotor.setOpenLoopRampRate(3);
    leftFrontMotor.setOpenLoopRampRate(3);
    rightBackMotor.setOpenLoopRampRate(3);
    rightFrontMotor.setOpenLoopRampRate(3);
  }

  
  /**
   * The log method puts interesting information to the SmartDashboard.
   */
  // public void log() {
  //   SmartDashboard.putNumber("Left Position", left_encoder.getPosition());
  //   SmartDashboard.putNumber("Right Position", right_encoder.getPosition());
  //   SmartDashboard.putNumber("Left Speed", left_encoder.getVelocity());
  //   SmartDashboard.putNumber("Right Speed", right_encoder.getVelocity());
  // }

  /**
   * Arcade style driving for the DriveTrain.
   *
   */
  public void drive(double y, double x) {

    //if (y >= 0)
    //  m_drive.arcadeDrive((y*RobotMap.driveTrainPower*RobotMap.direction), (-x*RobotMap.driveTrainPower));
    //else
    //System.out.println((y*RobotMap.driveTrainPower*RobotMap.direction)+","+(x*RobotMap.driveTrainPower)+","+RobotMap.driveTrainPower+","+RobotMap.direction);
      m_drive.arcadeDrive((y*RobotMap.driveTrainPower*RobotMap.direction), (x*RobotMap.driveTrainPower));
  }

  public void drive_in_inches(double y, double x) {
    m_drive.arcadeDrive(y, x);
  }

  /*public void driveStraight(double y) {
    double turningValue = (kAngleSetpoint - navx.getAngle()) * kP;
    turningValue = Math.copySign(turningValue, y);
    this.drive(y, turningValue);
  }*/

  private void setCameraDirection() {
    if (RobotMap.direction == RobotMap.direction_backward) cam.lookBackward();
    else cam.lookForward();
  }

  public void reverseDriveDirection() { 
    System.out.println("directionBEFORE="+RobotMap.direction);
    //RobotMap.direction *= -1;
    if (RobotMap.direction == -1) RobotMap.direction = 1;
    else RobotMap.direction = -1;

    setCameraDirection();
    System.out.println("directionAFTER="+RobotMap.direction);
  }

  // as defined in robot map
  // public static final int direction_forward = -1;
   // public static final int direction_backward = 1;
  public void setDriveDirection(int direction) { 
    RobotMap.direction = direction; 
    this.setCameraDirection();
  }

  /* not used but example of calling tank drive */
  /*
  public void TankDrive(double left, double right) {
    m_drive.tankDrive(left, right);
  } */

  /**
   * Reset the robots sensors to the zero states.
   */
  public synchronized void resetEncoders() {
    //System.out.println("Start Reset: L:"+left_encoder.getPosition()+",R:"+right_encoder.getPosition());
    left_encoder.setPosition(0.0000);
    right_encoder.setPosition(0.0000); 
    //navx.reset();
    //log();
    //System.out.println("End Reset: L:"+left_encoder.getPosition()+",R:"+right_encoder.getPosition());
  }

  /**
   * Get the average distance of the encoders since the last reset.
   * When the robot drives forward, the right value will be negative and
   * the left value will be positive.
   * @return The distance driven (average of left and right encoders).
   */
  public double getDistance() {
    return (Math.abs(left_encoder.getPosition())+Math.abs(right_encoder.getPosition()))/2;
  }

  public double getLeftEncoderDistance() { return left_encoder.getPosition(); }
  public double getRightEncoderDistance() { return right_encoder.getPosition(); }
}
