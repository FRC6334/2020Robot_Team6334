/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import com.revrobotics.CANSparkMax;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SetBallIntakeSpeed extends InstantCommand {
  private CANSparkMax motor;
  private double speed;

  public SetBallIntakeSpeed(CANSparkMax _m, double _s) {
    // Use addRequirements() here to declare subsystem dependencies.
    motor = _m;
    speed = _s;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    motor.set(speed);
  }
}
