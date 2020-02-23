package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;
import com.revrobotics.CANEncoder;

public class BallElevator extends SubsystemBase {
  /**
   * The DriveTrain subsystem incorporates the sensors and actuators attached to the robots chassis.
   * These include four drive motors, a left and right encoder and a gyro.
   */
  private final CANSparkMax m_ballelevator = new CANSparkMax(RobotMap.ballElevatorMotor, MotorType.kBrushless);
  private final CANEncoder m_encoder = m_ballelevator.getEncoder();

  /**
   * Create a new drive train subsystem.
   */
  public BallElevator() {
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
  public void setSpeed(double speed) { m_ballelevator.set(speed); }
  
  public double getDistance() { return m_encoder.getPosition(); }

  public synchronized void resetEncoders() { m_encoder.setPosition(0.0000); }

  public void driveInInches(int inches) {
    this.resetEncoders();
    if (inches > 0)
      while ((this.getDistance()*RobotMap.rotations_per_inch_elevator) > -inches) {
        this.setSpeed(-RobotMap.ballElevatorSpeed*.8);
      }
    else if (inches < 0)
      while ((this.getDistance()*RobotMap.rotations_per_inch_elevator) < -inches) {
        this.setSpeed(RobotMap.ballElevatorSpeed*.8); 
      }
    this.setSpeed(0.0);
    this.resetEncoders();
  }

}