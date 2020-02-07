/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallCounterDigitalInput;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.BallElevator;
import frc.robot.RobotMap;

public class BallCounterManagementSystem extends CommandBase {
  private BallCounterDigitalInput bcdi;
  private BallIntake ball_intake;
  private BallElevator ball_elevator;
  private boolean out_pressed;
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
    out_pressed=false;
    in_pressed=false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println("IN="+bcdi.getInValue());

    //a ball came into the intake and the intake switch has not previously been pressed during this ball coming in
    if (bcdi.getInStatus() && !in_pressed) {
      //reduce speed to very very slow
      ball_intake.setSpeed(0.2);  
      //lift ball into the elevator by X inches
      while ((ball_elevator.getDistance()*RobotMap.rotations_per_inch_elevtor) > -20) ball_elevator.setSpeed(-RobotMap.ballElevatorSpeed); 
      //stop the elevator & reset the elevator encoder so it is ready to lift the next ball
      ball_elevator.setSpeed(0.0);
      ball_elevator.resetEncoders();
      //add a ball to the counter
      bcdi.addBall();
      //if there are less than 5 balls then start the ball intake, if there are 5 balls stop the ball intake
      if (bcdi.getNumberofBalls() < 5) ball_intake.setSpeed(RobotMap.ballIntakeSpeed); else ball_intake.setSpeed(0);
      //record that we took in a ball
      in_pressed=true;
      //print the number of balls
      System.out.println("BALLS="+bcdi.getNumberofBalls());
    }
    
    //the intake switch has been deprsessed so we need to account for the next time it is pressed
    if (!bcdi.getInStatus()) in_pressed=false;
    
    //a ball left the tube and the out switch has not previously been pressed during this ball leaving
    if (bcdi.getOutStatus() && !out_pressed) { 
      out_pressed=true;
      bcdi.removeBall();
      System.out.println("BALLS="+bcdi.getNumberofBalls());
    }
    
    //the output switch has been deprsessed so we need to account for the next time it is pressed
    if (!bcdi.getOutStatus()) out_pressed=false;
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
