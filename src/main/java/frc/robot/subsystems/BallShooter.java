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
import com.revrobotics.CANEncoder;

public class BallShooter extends SubsystemBase {
  /**
   * The DriveTrain subsystem incorporates the sensors and actuators attached to the robots chassis.
   * These include four drive motors, a left and right encoder and a gyro.
   */
  private final CANSparkMax m_ballshooter = new CANSparkMax(RobotMap.ballShooterMotor, MotorType.kBrushless);
  private final CANEncoder m_encoder = m_ballshooter.getEncoder();

  /**
   * Create a new drive train subsystem.
   */
  public BallShooter() {
    super();
  }

  public void setSpeed(double speed) {
    m_ballshooter.set(speed);
  }

  public double getVelocity() { return m_encoder.getVelocity(); }
}
