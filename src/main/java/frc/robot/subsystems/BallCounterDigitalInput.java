/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.RobotMap;

public class BallCounterDigitalInput extends SubsystemBase {
  private static AnalogInput di_in;
  private static DigitalInput di_out;
  private static int num_balls;

  /**
   * Creates a new BallCounterDigitalInput.
   */
  public BallCounterDigitalInput(int intake, int output) {
    di_in = new AnalogInput(intake);
    di_out = new DigitalInput(output);
    num_balls = RobotMap.startingNumberOfBalls;
  }

  public int getNumberofBalls() { return num_balls; }
  public void addBall() { num_balls++; }
  public void removeBall() { num_balls--; }
  public boolean getInStatus() { return (di_in.getValue() > 3000); }
  public int getInValue() { return di_in.getValue(); }
  public boolean getOutStatus() { return di_out.get(); }
 
  public void printStatus(){
    //System.out.println("IN = " + di_in.get()+", OUT = "+di_out);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
