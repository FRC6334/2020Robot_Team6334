/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
import com.analog.adis16448.frc.ADIS16448_IMU;

public class ADIS_NavX extends SubsystemBase {
  //private final AHRS navx;
  private final ADIS16448_IMU navx;
  
  /**
   * Creates a new ADIS_NavX.
   */
  public ADIS_NavX() {

    //initialize NavX
    //navx = new AHRS(SPI.Port.kMXP);
    navx = new ADIS16448_IMU();
    navx.reset();
    // System.out.println("TEMPATURE="+this.getTempature());
  }

  //NAVX methods
  // public float getNAVXDisplacementX() { return navx.get.getDisplacementX(); }
  // public float getNAVXDisplacementY() { return navx.getDisplacementY(); }
  public void  resetNAVX() { navx.reset(); }
  public double getTempature() { return navx.getTemperature(); }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
