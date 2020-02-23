/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.commands.DriveInInches;
import frc.robot.commands.DriveToTarget;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimeLightVision;
import frc.robot.subsystems.BallElevator;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.BallShooter;
import frc.robot.subsystems.BallCounterDigitalInput;
import frc.robot.commands.Fire;
import frc.robot.commands.ReverseDrive;
import frc.robot.commands.DriveElevatorInInches;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveInInchesGroup extends SequentialCommandGroup {
  /**
   * Creates a new DriveInInchesGroup.
   */
  public DriveInInchesGroup(DriveTrain _dt, LimeLightVision _ll, BallShooter _bs, BallIntake _bi, BallElevator _be, BallCounterDigitalInput _bcdi) {
    addCommands(
      new ReverseDrive(_dt, RobotMap.direction_forward),
      new Fire(_bs, _bi, _be, _bcdi, 90, false),
      new setAutonomousMode(true),
      new DriveInInches2(_dt, 54, "F"),
      new DriveInInches2(_dt, 0.2, "R"),
      new DriveInInches2(_dt, 132, "F"), 
      new DriveInInches2(_dt, 1, "L"), 
      new ParallelCommandGroup(      
        new DriveInInches2(_dt, 110, "B"),
        new DriveElevatorInInches(_be, 5)
      ),
      new setAutonomousMode(false),
      new DriveToTarget(_ll, _dt, 160),
      new Fire(_bs, _bi, _be, _bcdi)
    );
  }

}
