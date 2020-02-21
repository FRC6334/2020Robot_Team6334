/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.SetBallIntakeSpeed;
import frc.robot.subsystems.BallIntake;
import frc.robot.RobotMap;
import frc.robot.commands.setFireMode;
import frc.robot.commands.DriveElevatorInInches;
import frc.robot.subsystems.BallElevator;
import frc.robot.commands.ResetBallCounter;
import frc.robot.subsystems.BallCounterDigitalInput;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Vomit extends SequentialCommandGroup {
  /**
   * Creates a new Vomit.
   */
  public Vomit(BallIntake _bi, BallElevator _be, BallCounterDigitalInput _bcdi) {
    addCommands(new setFireMode(true));
    addCommands(new SetBallIntakeSpeed(_bi, -RobotMap.ballIntakeSpeed));
    addCommands(new DriveElevatorInInches(_be, -150));
    addCommands(new SetBallIntakeSpeed(_bi, RobotMap.ballIntakeSpeed));
    addCommands(new ResetBallCounter(_bcdi, _bi));
    addCommands(new setFireMode(false));
  }
}
