/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.USBCamera;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotMap;
import frc.robot.subsystems.USBCamera;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class PivotCamera extends InstantCommand {
  private USBCamera c;
  private double d;

  public PivotCamera(USBCamera _c, double _d ) {
    // Use addRequirements() here to declare subsystem dependencies.
    c = _c;
    d = _d;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Moving camera: A("+c.getAngle()+"),P("+c.getPosition()+")");

    if (d == RobotMap.cam_fwd) {
     c.lookForward();
     System.out.println("CAMERA: look forward");
    }

    else if (d == RobotMap.cam_rev) {
      c.lookBackward();
      System.out.println("CAMERA: look reverse");
    } 
  }
}
