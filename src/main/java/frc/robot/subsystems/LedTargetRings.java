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
  private static AddressableLED m_led1 = new AddressableLED(2); //ring number and PWM port it is on
  //private static AddressableLED m_led2 = new AddressableLED(3); //ring number and PWM port it is on
  //private static AddressableLED m_led3 = new AddressableLED(4); //ring number and PWM port it is on
  private static AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(24);
  private static boolean led_on;

  /**
   * Creates a new LedTargetRings.
   */
  public LedTargetRings() {
    m_led1.setLength(m_ledBuffer.getLength());
    // m_led2.setLength(m_ledBuffer.getLength());
    // m_led3.setLength(m_ledBuffer.getLength());

    // Set the data
    for (int i = 0; i < m_ledBuffer.getLength(); i++) {
      // Sets the specified LED to the RGB values for red
      m_ledBuffer.setRGB(i, 0, 255, 0);
   }   
    m_led1.setData(m_ledBuffer);
    // m_led2.setData(m_ledBuffer);
    // m_led3.setData(m_ledBuffer);

    led_on = false;

  }

  public void turnOn() { 
    m_led1.start(); 
    // m_led2.start(); 
    // m_led3.start(); 
    led_on = true;
  }

  public void turnOff() { 
    m_led1.stop();
    // m_led2.stop();
    // m_led3.stop(); 
    led_on = false;
  }


  public boolean isOn() { return led_on; }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
