/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;

public class BallIntake extends SubsystemBase {
  /**
   * The DriveTrain subsystem incorporates the sensors and actuators attached to the robots chassis.
   * These include four drive motors, a left and right encoder and a gyro.
   */
  private final CANSparkMax m_ballintake = new CANSparkMax(RobotMap.ballIntakeMotor, MotorType.kBrushless);

  /**
   * Create a new drive train subsystem.
   */
  public BallIntake() {
    super();

    
  }

  /**
   * The log method puts interesting information to the SmartDashboard.
   */
  public void log() {
    
  }

  /**
    *
   */
  public void setSpeed(double speed) {
    m_ballintake.set(speed);
  }

}
