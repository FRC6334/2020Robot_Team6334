/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Servo;
import frc.robot.RobotMap;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class USBCamera extends SubsystemBase {
  private final Servo cameraServo;


  /**
   * Creates a new USBCamera.
   */
  public USBCamera() {
    CameraServer.getInstance().startAutomaticCapture();
    cameraServo = new Servo(RobotMap.USB_camera_port);
  }

  public void lookForward() {
    cameraServo.set(0.1);
  }

  public void lookBackward() {
    cameraServo.set(0.9);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
