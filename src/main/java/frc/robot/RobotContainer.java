/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.SetBallIntakeSpeed;
import frc.robot.subsystems.DriveTrain;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final DriveTrain m_drivetrain = new DriveTrain();

  private final Joystick m_joystick0 = new Joystick(0);
  private final CANSparkMax m_ballintake = new CANSparkMax(1, MotorType.kBrushless);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    // Assign default commands
    //m_drivetrain.setDefaultCommand(new TankDrive(() -> m_joystick.getY(Hand.kLeft),
    //    () -> m_joystick.getY(Hand.kRight), m_dhttps://www.kauailabs.com/dist/frc/2020/navx_frc.jsonrivetrain));


    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Create some buttons
    final JoystickButton m_button03 = new JoystickButton(m_joystick0, 3);
    m_button03.whenReleased(new SetBallIntakeSpeed(m_ballintake, 0));
    
    final JoystickButton m_button04 = new JoystickButton(m_joystick0, 4);
    m_button04.whenReleased(new SetBallIntakeSpeed(m_ballintake, -0.5));

    final JoystickButton m_button05 = new JoystickButton(m_joystick0, 5);
    m_button05.whenReleased(new SetBallIntakeSpeed(m_ballintake, 0.5));
  }


}
