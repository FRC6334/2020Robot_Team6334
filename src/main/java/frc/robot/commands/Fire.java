/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.SetBallIntakeSpeed;
import frc.robot.commands.SetBallShooterSpeed;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.BallShooter;
import edu.wpi.first.wpilibj.Timer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Fire extends SequentialCommandGroup {
  /**
   * Creates a new Fire.
   */
  public Fire(BallShooter _bs, BallIntake _bi) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    //super();
    Timer t = new Timer();
    

    //set all speed to 0
    addCommands(new SetBallShooterSpeed(_bs, 0));
    addCommands(new SetBallIntakeSpeed(_bi, 0));

    //back up balls in intake tube
    addCommands(new SetBallIntakeSpeed(_bi, -1.0));
    //t.start(); while (t.get() < 1.5); t.stop();
    addCommands(new SetBallIntakeSpeed(_bi, 0));

    //fire up the shooter
    addCommands(new SetBallShooterSpeed(_bs, -0.8));
    t.start(); while (t.get() < 1.0); t.stop();

    //load balls from the intake to the shooter
    addCommands(new SetBallIntakeSpeed(_bi, 1.0));
  }
}
