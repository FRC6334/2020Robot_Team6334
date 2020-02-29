/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.RobotMap;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.BallShooter;
import frc.robot.subsystems.BallElevator;
import frc.robot.subsystems.BallCounterDigitalInput;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Fire extends SequentialCommandGroup {
  /**
   * Creates a new Fire.
   */
  public Fire(BallShooter _bs, BallIntake _bi, BallElevator _be, BallCounterDigitalInput _bcdi) {
      this(_bs, _bi, _be, _bcdi, 150, -4, RobotMap.ball_shooter_far);
  }

  public Fire(BallShooter _bs, BallIntake _bi, BallElevator _be, BallCounterDigitalInput _bcdi, int _inches, int _backup, double _shootpower) {
    
    addCommands(
        new setFireMode(true),                          //set mode to fire so that the ball intake will not activate

        new ParallelCommandGroup (      
          //new SetBallShooterSpeed(_bs, 0),              //set all speed to 0
          new SetBallIntakeSpeed(_bi, 0),
          new SetBallElevatorSpeed(_be, 0)
        ),

        new ParallelCommandGroup (                      //back up balls in intake tube
          new SetBallIntakeSpeed(_bi, -0.4),
          new DriveElevatorInInches(_be, _backup)
        ),

        new SetBallIntakeSpeed(_bi, 0.0),                           //turn off the ball intake

        new SetBallShooterDistance(_bs, _shootpower),    //fire up the shooter for 18.5 foot shot

        new ParallelCommandGroup (                      //load balls from the intake to the shooter
          new SetBallIntakeSpeed(_bi, 0.2),
          new DriveElevatorInInches(_be, _inches)
        ),

        new SetBallShooterSpeed(_bs, 0.0),             //Now turn off the shooter

        new ResetBallCounter(_bcdi, _bi),      //reset number of balls to 0 which will also restart the ball intake
    
        new SetBallIntakeSpeed(_bi, RobotMap.ballIntakeSpeed),             

        new setFireMode(false)                  //turn off fire mode that the bal intake will activate
    );
  }
}
