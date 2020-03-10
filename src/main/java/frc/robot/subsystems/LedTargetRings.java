/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LedTargetRings extends SubsystemBase {
  private static AddressableLED m_led = new AddressableLED(2);
  private static AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(24);

  /**
   * Creates a new LedTargetRings.
   */
  public LedTargetRings() {
    m_led.setLength(m_ledBuffer.getLength());

    // Set the data
    for (int i = 0; i < m_ledBuffer.getLength(); i++) {
      // Sets the specified LED to the RGB values for red
      m_ledBuffer.setRGB(i, 0, 255, 0);
   }   
    m_led.setData(m_ledBuffer);

  }

  public void turnOn() { m_led.start(); }
  public void turnOff() { m_led.stop(); }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
