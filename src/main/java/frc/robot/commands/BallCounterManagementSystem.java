/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BallCounterDigitalInput;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.BallElevator;
import frc.robot.RobotMap;
import frc.robot.commands.DriveElevatorInInches;

public class BallCounterManagementSystem extends CommandBase {
  private BallCounterDigitalInput bcdi;
  private BallIntake ball_intake;
  private BallElevator ball_elevator;
  private boolean in_pressed;

  /**
   * Creates a new BallCounterManagementSystem.
   */
  public BallCounterManagementSystem(BallCounterDigitalInput _bcdi, BallIntake _bi, BallElevator _be) {
    // Use addRequirements() here to declare subsystem dependencies.
    bcdi = _bcdi;
    ball_intake = _bi;
    ball_elevator = _be;
    addRequirements(bcdi);
    in_pressed=false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println("IN="+bcdi.getInValue()+"HOLD=" + bcdi.getHoldValue());
    //a ball came into the intake and the intake switch has not previously been pressed during this ball coming in
    if (!RobotMap.InFireMode && bcdi.getInStatus() && !in_pressed && bcdi.getNumberofBalls() < 5) {
      //reduce speed to very very slow
      ball_intake.setSpeed(0.3);

      //for the first ball, we want to run the elevator until the hold sensor sees the ball
      if (bcdi.getNumberofBalls() == 0) {
        while (!bcdi.getHoldStatus())
          ball_elevator.setSpeed(-RobotMap.ballElevatorSpeed);
        ball_elevator.setSpeed(0);
      }

      //for the last ball, we want to pull it in just enough to hold it in the basin but not bring it up the elevator
      else if (bcdi.getNumberofBalls() == 4) 
        new DriveElevatorInInches(ball_elevator, 2).schedule();

      //for balls 2-4, we want to do the following:
      //first "while" loop - run the elevator until the bottom sensor no longer sees the ball
      //second "while" loop - run the elevator X number of crannks
      //third "while" loop - run the elevator to just past where the hold sensor can see the ball
      else {
        while (bcdi.getInStatus()) 
          ball_elevator.setSpeed(-RobotMap.ballElevatorSpeed);
        new DriveElevatorInInches(ball_elevator, 1).schedule();
        //while (bcdi.getHoldStatus()) 
        //  ball_elevator.setSpeed(-RobotMap.ballElevatorSpeed);
      }
      
      //these are done by the DriveElevatorInInches command
      //stop the elevator & reset the elevator encoder so it is ready to lift the next ball
      //ball_elevator.setSpeed(0.0);
      //ball_elevator.resetEncoders();
      
      //add a ball to the counter
      bcdi.addBall();
      
      //if there are less than 5 balls then start the ball intake, if there are 5 balls stop the ball intake
      if (bcdi.getNumberofBalls() < 5) ball_intake.setSpeed(RobotMap.ballIntakeSpeed); 
      else ball_intake.setSpeed(0);
      
      //record that we took in a ball
      in_pressed=true;
      
      //print the number of balls
      System.out.println("BALLS="+bcdi.getNumberofBalls());
    }
    
    //the intake switch has been deprsessed so we need to account for the next time it is pressed
    if (!bcdi.getInStatus()) in_pressed=false;
    
    //a ball left the tube and the out switch has not previously been pressed during this ball leaving
    /*if (bcdi.getOutStatus() && !out_pressed) { 
      out_pressed=true;
      bcdi.removeBall();
      System.out.println("BALLS="+bcdi.getNumberofBalls());
    }
    
    //the output switch has been deprsessed so we need to account for the next time it is pressed
    if (!bcdi.getOutStatus()) out_pressed=false;*/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
