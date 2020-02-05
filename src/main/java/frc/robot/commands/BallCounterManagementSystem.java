/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallCounterDigitalInput;

public class BallCounterManagementSystem extends CommandBase {
  private BallCounterDigitalInput bcdi;
  private boolean out_pressed;
  private boolean in_pressed;

  /**
   * Creates a new BallCounterManagementSystem.
   */
  public BallCounterManagementSystem(BallCounterDigitalInput _bcdi) {
    // Use addRequirements() here to declare subsystem dependencies.
    bcdi = _bcdi;
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
    //a ball came into the tube and the intake switch has not previously been pressed during this ball coming in
    if (bcdi.getInStatus() && !in_pressed) {
      in_pressed=true;
      bcdi.addBall();
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
