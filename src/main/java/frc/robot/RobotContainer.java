/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.SetBallIntakeSpeed;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.DriveTrain;
// import frc.robot.subsystems.USBCamera;
import frc.robot.subsystems.LimeLightVision;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DriveToTarget;
//import frc.robot.commands.DriveInInchesGroup;
import frc.robot.commands.ReverseDrive;
import frc.robot.commands.GetLimeLightValues;
import frc.robot.commands.ToggleLimeLightLED;
import frc.robot.commands.ToggleLimeLightVision;
import frc.robot.commands.ResetBallCounter;
import frc.robot.subsystems.BallShooter;
import frc.robot.commands.Fire;
import frc.robot.subsystems.BallCounterDigitalInput;
import frc.robot.commands.BallCounterManagementSystem;
import frc.robot.subsystems.BallElevator;
import frc.robot.commands.DriveElevatorInInches;
//import frc.robot.subsystems.LEDLightStrip12V;
import frc.robot.commands.ClimberDrive;
import frc.robot.subsystems.RobotClimber;
import frc.robot.commands.Vomit;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final LEDLightStrip12V ledLightStrip = new LEDLightStrip12V();
  private final DriveTrain m_drivetrain = new DriveTrain();
  //private final USBCamera m_camera = new USBCamera();
  private final LimeLightVision m_limelight = new LimeLightVision();
  //private final ColorSensor m_color_sensor = new ColorSensor();
  private final Joystick m_joystick0 = new Joystick(0);
  private final Joystick m_joystick1 = new Joystick(1);
  private final BallIntake m_ballintake = new BallIntake();
  private final BallShooter m_ballshooter = new BallShooter();
  private final BallElevator m_ballelevator = new BallElevator();
  private final RobotClimber m_climber = new RobotClimber();
  private final BallCounterDigitalInput bcdi = new BallCounterDigitalInput(
    RobotMap.ballIntakeChannel,
    RobotMap.ballOutputChannel,
    RobotMap.ballHoldChannel);
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_limelight.setLedMode(RobotMap.ll_off);
    //m_ballintake.setSpeed(RobotMap.ballIntakeSpeed);
    
    // Assign default commands
    //
    m_drivetrain.setDefaultCommand(new ArcadeDrive(m_joystick0, m_drivetrain));
    m_climber.setDefaultCommand(new ClimberDrive(m_joystick1, m_climber));
    bcdi.setDefaultCommand(new BallCounterManagementSystem(bcdi, m_ballintake, m_ballelevator));
    
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
    final JoystickButton m_button01 = new JoystickButton(m_joystick0, 1);
    m_button01.whenReleased(new Fire(m_ballshooter, m_ballintake, m_ballelevator, bcdi));

    final JoystickButton m_button02 = new JoystickButton(m_joystick0, 2);
    m_button02.whileHeld(new DriveToTarget(m_limelight, m_drivetrain));

    final JoystickButton m_button03 = new JoystickButton(m_joystick0, 3);
    m_button03.whenReleased(new SetBallIntakeSpeed(m_ballintake, 0));

    final JoystickButton m_button04 = new JoystickButton(m_joystick0, 4);
     m_button04.whenReleased(new SetBallIntakeSpeed(m_ballintake, -0.5));

    final JoystickButton m_button05 = new JoystickButton(m_joystick0, 5);
    m_button05.whenReleased(new SetBallIntakeSpeed(m_ballintake, 0.5));

    final JoystickButton m_button06 = new JoystickButton(m_joystick0, 6);
    m_button06.whenReleased(new ReverseDrive(m_drivetrain));

    final JoystickButton m_button10 = new JoystickButton(m_joystick0, 10);
    m_button10.whenReleased(new ToggleLimeLightLED(m_limelight));

    final JoystickButton m_button11 = new JoystickButton(m_joystick0, 11);
    m_button11.whenReleased(new ToggleLimeLightVision(m_limelight));

    final JoystickButton m_button2 = new JoystickButton(m_joystick1, 2);
    m_button2.whenReleased(new GetLimeLightValues(m_limelight));

    final JoystickButton m_button4 = new JoystickButton(m_joystick1, 4);
    m_button4.whenReleased(new DriveElevatorInInches(m_ballelevator, -7));

    final JoystickButton m_button5 = new JoystickButton(m_joystick1, 5);
    m_button5.whenReleased(new DriveElevatorInInches(m_ballelevator, 7));

    final JoystickButton m_button6 = new JoystickButton(m_joystick1, 6);
    m_button6.whenReleased(new ResetBallCounter(bcdi, m_ballintake));

    final JoystickButton m_button8 = new JoystickButton(m_joystick1, 8);
    m_button8.whenReleased(new Vomit(m_ballintake, m_ballelevator));

// final JoystickButton m_button01 = new JoystickButton(m_joystick0, 1);
// m_button01.whenHeld(new DriveBallIntakeSpeed(m_ballintake, m_joystick0.getY()*-1));
//      */

//     // final JoystickButton m_button08 = new JoystickButton(m_joystick0, 8);
//     // m_button08.whenReleased(new IntakeAndShoot(m_ballshooter, m_ballintake, 0.05, 0.5));
//     /*

//     final JoystickButton m_button02 = new JoystickButton(m_joystick0, 2);
//     m_button02.whenReleased(new DriveInInchesGroup(m_drivetrain));

//     final JoystickButton m_button09 = new JoystickButton(m_joystick0, 9);
//     m_button09.whenPressed(new ResetEncoderDistance(m_drivetrain));

//     final JoystickButton m_button10 = new JoystickButton(m_joystick0, 10);
//     m_button10.whenPressed(new GetColorInformation(m_color_sensor));
//     */
  }


}
