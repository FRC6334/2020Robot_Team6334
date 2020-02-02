/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;

public class BallCounterDigitalInput extends SubsystemBase {
  private static DigitalInput di_in;
  private static DigitalInput di_out;
  private static int num_balls;

  /**
   * Creates a new BallCounterDigitalInput.
   */
  public BallCounterDigitalInput(int channel1, int channel2) {
    di_in = new DigitalInput(channel1);
    di_out = new DigitalInput(channel2);
    num_balls = 0;
  }

  public int getNumberofBalls() { return num_balls; }

  public void addBall() { num_balls++; }
  public void removeBall() { num_balls--; }
  public boolean getInStatus() { return di_in.get(); }
  public boolean getOutStatus() { return di_out.get(); }
 
  public void printStatus(){
    System.out.println("IN = " + di_in.get()+", OUT = "+di_out);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
