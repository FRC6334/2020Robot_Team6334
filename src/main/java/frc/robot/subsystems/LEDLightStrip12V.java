/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PWM;
import frc.robot.RobotMap;

public class LEDLightStrip12V extends SubsystemBase {
  private final PWM ledLights = new PWM(RobotMap.LEDLightsPWMport);

  /**
   * Creates a new LEDLightStrip12V.
   */
  public LEDLightStrip12V() {
    ledLights.setSpeed(RobotMap.LEDLightsInitialColor);
  }

  public void setColor(double color) { ledLights.setSpeed(color); }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
