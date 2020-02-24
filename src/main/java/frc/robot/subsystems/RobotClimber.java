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
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotClimber extends SubsystemBase {
  private final CANSparkMax m_leftMotor = new CANSparkMax(RobotMap.climberMotorLeft, MotorType.kBrushless);
  private final CANSparkMax m_rightMotor = new CANSparkMax(RobotMap.climberMotorRight, MotorType.kBrushless);
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);

  
  /**
   * Creates a new RobotClimber.
   */
  public RobotClimber() {
    
  }

  /**
   * Arcade style driving for the Climber
   * Joystick Y-axis will move the robot up and down evenly
   * Joystick to the right will move the right side up and the left side down
   * Joystick to the left will move the left side up and right side down
   */
  public void drive(double y, double x) {
    m_drive.arcadeDrive((y*RobotMap.climberPower*-1), (x*RobotMap.climberPower*-1));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
