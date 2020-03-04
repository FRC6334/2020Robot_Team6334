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

  private final CANSparkMax m_ballelevator2 = new CANSparkMax(RobotMap.ballElevatorMotor2, MotorType.kBrushless);


  /**
   * Create a new drive train subsystem.
   */
  public BallElevator() {
    super();    
    //m_ballelevator2.follow(m_ballelevator);
  }

  //set the speed of the elevator motor
  public void setSpeed(double speed) { m_ballelevator.set(speed); m_ballelevator2.set(speed*1.20);}
  
  //distance of the motor encoder
  public double getDistance() { return m_encoder.getPosition(); }
  public double getDistanceInInches() { return (m_encoder.getPosition()*RobotMap.rotations_per_inch_elevator); }

  //returns if the elevator has traveled the specific number of inches (positive or negative)
  public boolean hasTraveled(double inches) {
    if (inches == 0) 
      return true;
    else if (Math.abs(this.getDistanceInInches()) > Math.abs(inches))
      return true;

    return false;
  }  

  //reset the encoder distance
  public synchronized void resetEncoders() { m_encoder.setPosition(0.0000); }

}