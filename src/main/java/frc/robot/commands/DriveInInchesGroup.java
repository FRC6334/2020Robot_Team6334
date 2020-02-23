/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveInInches;
import frc.robot.commands.DriveToTarget;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimeLightVision;
import frc.robot.subsystems.BallElevator;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.BallShooter;
import frc.robot.subsystems.BallCounterDigitalInput;
import frc.robot.commands.Fire;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveInInchesGroup extends SequentialCommandGroup {
  /**
   * Creates a new DriveInInchesGroup.
   */
  public DriveInInchesGroup(DriveTrain _dt, LimeLightVision _ll, BallShooter _bs, BallIntake _bi, BallElevator _be, BallCounterDigitalInput _bcdi) {
    addCommands(new Fire(_bs, _bi, _be, _bcdi, 90));
    addCommands(new DriveInInches(_dt, 36, "F"));
    addCommands(new DriveInInches(_dt, 0.05, "R"));
    addCommands(new DriveInInches(_dt, 120, "F"));
    addCommands(new DriveInInches(_dt, 120, "B"));
    addCommands(new DriveInInches(_dt, 2, "L"));
    addCommands(new DriveToTarget(_ll, _dt, 175));
    addCommands(new Fire(_bs, _bi, _be, _bcdi));

    //addCommands(new DriveInInches(_dt, 360, "R"));
    //addCommands(new DriveInInches(_dt, 90, "R"));
  }
}
