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
import frc.robot.commands.SetBallShooterSpeed;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.USBCamera;
import frc.robot.subsystems.LimeLightTarget;
import frc.robot.subsystems.LimeLightBall;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DriveToTarget;
import frc.robot.commands.DriveInInchesGroup;
import frc.robot.commands.ReverseDrive;
import frc.robot.commands.GetLimeLightValues;
import frc.robot.commands.GetLimeLightBallValues;
import frc.robot.commands.ToggleLimeLightLED;
import frc.robot.commands.ToggleLimeLightVision;
import frc.robot.commands.ResetBallCounter;
import frc.robot.subsystems.BallShooter;
import frc.robot.commands.Fire;
import frc.robot.subsystems.BallCounterDigitalInput;
import frc.robot.commands.BallCounterManagementSystem;
import frc.robot.subsystems.BallElevator;
import frc.robot.commands.DriveElevatorInInches;
import frc.robot.commands.DriveInInches2;
import frc.robot.subsystems.LEDLightRing;
import frc.robot.commands.ClimberDrive;
import frc.robot.subsystems.RobotClimber;
import frc.robot.commands.Vomit;
import frc.robot.commands.PivotCamera;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.FollowBall;
import frc.robot.commands.DriveToBall;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final LEDLightRing ledRing = new LEDLightRing();
  private final USBCamera m_camera = new USBCamera();
  private final DriveTrain m_drivetrain = new DriveTrain(m_camera);
  private final LimeLightTarget m_limelight = new LimeLightTarget();
  private final LimeLightBall m_limeball = new LimeLightBall(ledRing);
  //private final ColorSensor m_color_sensor = new ColorSensor();
  private final Joystick m_joystick0 = new Joystick(0);
  private final Joystick m_joystick1 = new Joystick(1);
  private final Joystick m_joystick2 = new Joystick(2);
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
    m_ballintake.setSpeed(RobotMap.ballIntakeSpeed);
    //m_ballintake.setSpeed(0);
    m_camera.lookForward();
    //ledRing.turnOn();
    
    // Assign default commands
    //
    ArcadeDrive ad = new ArcadeDrive(m_joystick0, m_drivetrain);
    m_drivetrain.setDefaultCommand(ad);
    m_climber.setDefaultCommand(new ClimberDrive(m_joystick1, m_joystick2, m_climber));
    bcdi.setDefaultCommand(new BallCounterManagementSystem(bcdi, m_ballintake, m_ballelevator));
    
    // Configure the button bindings
    configureButtonBindings();

    m_limelight.setLedMode(RobotMap.ll_off);
    m_limeball.setLedMode(RobotMap.ll_off);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //
    // Create some buttons Joystick 0 (Drive train)
    //
    final JoystickButton m_button01 = new JoystickButton(m_joystick0, 1);
    //m_button01.whenReleased(new Fire(m_ballshooter, m_ballintake, m_ballelevator, bcdi));
    m_button01.whenReleased(new SequentialCommandGroup(
        new Fire(m_ballshooter, m_ballintake, m_ballelevator, bcdi),
        new ReverseDrive(m_drivetrain, RobotMap.direction_forward)
    ));

    final JoystickButton m_button02 = new JoystickButton(m_joystick0, 2);
    m_button02.whileHeld(new ParallelCommandGroup( //we need to test this as whileheld so the driver can cancel at any time
      new SetBallShooterSpeed(m_ballshooter, -0.7),
      new DriveToTarget(m_limelight, m_drivetrain)
    ));

    final JoystickButton m_button03 = new JoystickButton(m_joystick0, 3);
    m_button03.whenReleased(new SetBallIntakeSpeed(m_ballintake, 0));

    final JoystickButton m_button04 = new JoystickButton(m_joystick0, 4);
     m_button04.whenReleased(new SetBallIntakeSpeed(m_ballintake, -0.5));

    final JoystickButton m_button05 = new JoystickButton(m_joystick0, 5);
    m_button05.whenReleased(new SetBallIntakeSpeed(m_ballintake, 0.5));

    final JoystickButton m_button06 = new JoystickButton(m_joystick0, 6);
    m_button06.whenReleased(new ReverseDrive(m_drivetrain));

    final JoystickButton m_button08 = new JoystickButton(m_joystick0, 8);
    m_button08.whenReleased(new PivotCamera(m_camera, RobotMap.cam_fwd));

    final JoystickButton m_button09 = new JoystickButton(m_joystick0, 9);
    m_button09.whenReleased(new PivotCamera(m_camera, RobotMap.cam_rev));

    final JoystickButton m_button10 = new JoystickButton(m_joystick0, 10);
    m_button10.whenReleased(new ToggleLimeLightLED(m_limelight));

    final JoystickButton m_button11 = new JoystickButton(m_joystick0, 11);
    m_button11.whenReleased(new ToggleLimeLightVision(m_limelight));

    //
    // Create some buttons Joystick 1 (climber drive)
    //
    final JoystickButton m_button2 = new JoystickButton(m_joystick1, 2);
    m_button2.whenReleased(new GetLimeLightValues(m_limelight));

    final JoystickButton m_button4 = new JoystickButton(m_joystick1, 4);
    m_button4.whenReleased(new SequentialCommandGroup(
      new ParallelCommandGroup(
        new SetBallIntakeSpeed(m_ballintake,-0.3),
        new SetBallShooterSpeed(m_ballshooter, 1),
        new DriveElevatorInInches(m_ballelevator, -7)
        ),
      new SetBallShooterSpeed(m_ballshooter, 0),
      new SetBallIntakeSpeed(m_ballintake,RobotMap.ballIntakeSpeed)
    ));

    final JoystickButton m_button5 = new JoystickButton(m_joystick1, 5);
    m_button5.whenReleased(new DriveElevatorInInches(m_ballelevator, 7));

    final JoystickButton m_button6 = new JoystickButton(m_joystick1, 6);
    m_button6.whenReleased(new ResetBallCounter(bcdi, m_ballintake));

    final JoystickButton m_button8 = new JoystickButton(m_joystick1, 8);
    m_button8.whenReleased(new Vomit(m_ballintake, m_ballelevator, bcdi));

    final JoystickButton m_button9 = new JoystickButton(m_joystick1, 9);
    m_button9.whenHeld(new FollowBall(m_limeball, m_drivetrain));

    //final JoystickButton m_button110 = new JoystickButton(m_joystick1, 10);
    //m_button110.whenReleased(new DriveToBall(m_limeball, m_drivetrain));

    //Extra commands not used in this release
/*
    final JoystickButton m_button01 = new JoystickButton(m_joystick0, 1);
    m_button01.whenHeld(new DriveBallIntakeSpeed(m_ballintake, m_joystick0.getY()*-1));
    
    final JoystickButton m_button08 = new JoystickButton(m_joystick0, 8);
    m_button08.whenReleased(new IntakeAndShoot(m_ballshooter, m_ballintake, 0.05, 0.5));

    final JoystickButton m_button02 = new JoystickButton(m_joystick0, 2);
    m_button02.whenReleased(new DriveInInchesGroup(m_drivetrain));

    final JoystickButton m_button09 = new JoystickButton(m_joystick0, 9);
    m_button09.whenPressed(new ResetEncoderDistance(m_drivetrain));

   final JoystickButton m_button10 = new JoystickButton(m_joystick0, 10);
   m_button10.whenPressed(new GetColorInformation(m_color_sensor));
*/
  }
  
  public Command getAutonomousCommand(){
    // return new SequentialCommandGroup(
    //   new frc.robot.commands.setAutonomousMode(true),
    //   new DriveInInches2(m_drivetrain, 96, "F", m_limeball),
    //   new frc.robot.commands.setAutonomousMode(false)
    // );

    return new DriveInInchesGroup(m_drivetrain, m_limelight, m_limeball, m_ballshooter, m_ballintake, m_ballelevator, bcdi);
  }
}
