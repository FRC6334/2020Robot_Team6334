/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.subsystems.LimeLightVision;
import frc.robot.RobotMap;
import edu.wpi.first.networktables.NetworkTableInstance;


public class LimeLightBall extends LimeLightVision {
  /**
   * Creates a new LimeLightBall.
   */
  public LimeLightBall() {
    super("limelight-ball");
  }

  public void outputLimeLightValues() {
    this.outputValues();
    System.out.println("distance to target (inches): "+this.getDistanceToTarget());
    //System.out.println("upper: "+NetworkTableInstance.getDefault().getTable("limelight-target").getEntry("tv").getDouble(-999));
    //System.out.println("lower: "+NetworkTableInstance.getDefault().getTable("limelight-ball").getEntry("tv").getDouble(-999));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //working within 1-2" of actual distance, a wider angle makes it less accurate
  public double getDistanceToTarget() {
    this.setLedMode(RobotMap.ll_on);
    if (this.getTV() != 1.0) return RobotMap.defaultLimeLight;
    
    //d = (h2-h1) / tan(a1+a2)
    double heightOfTarget = RobotMap.heightOfBall; //h2 
    double heightOfLimeLight = RobotMap.heightOfBallLimeLight; // h1
    double a2 = this.getTY(); // The limelight (or your vision system) can tell you the y angle to the target (a2).
    double a1 = RobotMap.angleOfBallLimeLight;   //lime light mounting angle is known (a1)

    //calculate distance
    System.out.println("("+heightOfLimeLight+"-"+heightOfTarget+") / tan("+a1+"+"+a2+")");
    return (heightOfLimeLight - heightOfTarget) / Math.tan(Math.toRadians(a1+a2));
  }
}