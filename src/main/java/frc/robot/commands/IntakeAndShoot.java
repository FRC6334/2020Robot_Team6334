/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.commands.SetBallIntakeSpeed;
import frc.robot.commands.SetBallShooterSpeed;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.BallShooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class IntakeAndShoot extends ParallelCommandGroup {
  /**
   * Creates a new IntakeAndShoot.
   */
  public IntakeAndShoot(BallShooter _bs, BallIntake _bi, double _bs_speed, double _bi_speed) {
    
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    addCommands(new SetBallShooterSpeed(_bs, _bs_speed));
    addCommands(new SetBallIntakeSpeed(_bi, _bi_speed));
  }
}
