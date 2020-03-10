/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimeLightTarget;
import frc.robot.subsystems.BallElevator;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.BallShooter;
import frc.robot.subsystems.BallCounterDigitalInput;
import frc.robot.subsystems.LimeLightBall;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveInInchesGroup_Richmond extends SequentialCommandGroup {
  /**
   * Creates a new DriveInInchesGroup.
   */
  public DriveInInchesGroup_Richmond(DriveTrain _dt, LimeLightTarget _ll, LimeLightBall _lb, BallShooter _bs, BallIntake _bi, BallElevator _be, BallCounterDigitalInput _bcdi) {
    addCommands(
      new ReverseDrive(_dt, RobotMap.direction_forward),
      new Fire(_bs, _bi, _be, _bcdi, 46, 0, RobotMap.ball_shooter_68),
      new setAutonomousMode(true),
      new ParallelCommandGroup (      
        new SetBallIntakeSpeed(_bi, 1),
        new DriveInInches2(_dt, 54, "F")
      ),
      new DriveInInches2(_dt, 0.2, "R"),
      new DriveInInches2(_dt, 110, "F", _lb), 
      new DriveInInches2(_dt, 1, "L"), 
      new ParallelCommandGroup (      
        new DriveInInches2(_dt, 115, "B"),
        new DriveElevatorInInches(_be, 3),
        new SetBallShooterSpeed(_bs, -0.7)
      ),
      new setAutonomousMode(false),
      //new DriveInInches2(_dt, 1.3, "L"),
      new DriveToTarget(_ll, _dt),
      new Fire(_bs, _bi, _be, _bcdi, 90, 0,RobotMap.ball_shooter_50s)
    );
  }
}
