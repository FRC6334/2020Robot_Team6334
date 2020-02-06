/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.BallCounterDigitalInput;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ResetBallCounter extends InstantCommand {
  private BallCounterDigitalInput bcdi;
  private int balls;

  public ResetBallCounter(BallCounterDigitalInput _bcdi, int _balls) {
    // Use addRequirements() here to declare subsystem dependencies.
    bcdi = _bcdi;
    balls = _balls;
  }

  public ResetBallCounter(BallCounterDigitalInput _bcdi) {
    // Use addRequirements() here to declare subsystem dependencies.
    this(_bcdi , 0);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    bcdi.setNumberofBalls(balls);
  }
}
