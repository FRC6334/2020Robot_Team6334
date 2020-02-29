/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimeLightBall;

public class GetLimeLightBallValues extends InstantCommand {
  LimeLightBall lime_light;

  /**
   * Creates a new GetLimeLightValues.
   */
  public GetLimeLightBallValues(LimeLightBall m_lime) {
    // Use addRequirements() here to declare subsystem dependencies.
    lime_light = m_lime;
  }

  // Called when the command is initially scheduled.
  @Override
  public void execute() {
    System.out.println("getting values");
    lime_light.outputLimeLightValues();
  }
}
