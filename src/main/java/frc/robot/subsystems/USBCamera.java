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

  public double getAngle() { return cameraServo.getAngle(); }
  public double getPosition() { return cameraServo.get(); }

  public void lookForward() {
    cameraServo.set(RobotMap.cam_fwd);
  }

  public void lookBackward() {
    cameraServo.set(RobotMap.cam_rev);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
