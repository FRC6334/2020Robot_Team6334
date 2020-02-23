/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.commands.SetBallIntakeSpeed;
import frc.robot.commands.SetBallShooterSpeed;
import frc.robot.RobotMap;
import frc.robot.commands.DriveElevatorInInches;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.BallShooter;
import frc.robot.subsystems.BallElevator;
import frc.robot.subsystems.BallCounterDigitalInput;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.commands.setFireMode;
import frc.robot.commands.SetBallIntakeSpeed;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Fire extends SequentialCommandGroup {
  /**
   * Creates a new Fire.
   */
  public Fire(BallShooter _bs, BallIntake _bi, BallElevator _be, BallCounterDigitalInput _bcdi) {
      this(_bs, _bi, _be, _bcdi, 150);
  }

  public Fire(BallShooter _bs, BallIntake _bi, BallElevator _be, BallCounterDigitalInput _bcdi, int _inches) {
    //set mode to fire so that the ball intake will not activate
    addCommands(new setFireMode(true));
    
    //set all speed to 0
    addCommands(new SetBallShooterSpeed(_bs, 0));
    addCommands(new SetBallIntakeSpeed(_bi, 0));
    addCommands(new SetBallElevatorSpeed(_be, 0));

    //back up balls in intake tube
    addCommands(new SetBallIntakeSpeed(_bi, -0.4));
    addCommands(new DriveElevatorInInches(_be, -4));

    addCommands(new SetBallIntakeSpeed(_bi, 0.0));

    //fire up the shooter for 18.5 foot shot
    addCommands(new SetBallShooterDistance(_bs, RobotMap.ball_shooter_far));
    
    //load balls from the intake to the shooter
    addCommands(new SetBallIntakeSpeed(_bi, 0.2));
    addCommands(new DriveElevatorInInches(_be, _inches));
  
    //Now turn off the shooter
    addCommands(new SetBallShooterSpeed(_bs, 0.0));

    //reset number of balls to 0 which will also restart the ball intake
    addCommands(new ResetBallCounter(_bcdi, _bi));
    
    //turn off fire mode that the bal intake will activate
    addCommands(new setFireMode(false));
  }
}
