/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class RobotMap {
    
    //Motor numbers
    public static final int leftFrontMotor = 3;
    public static final int leftBackMotor = 4;
    public static final int rightFrontMotor = 1;
    public static final int rightBackMotor = 2;
    public static final int ballShooterMotor = 3;
    public static final int ballIntakeMotor = 4;
    public static final int ballElevatorMotor = 2;

    //Drive Train Power
    //0 = no power
    //0.5 = half power
    //1 = full power
    public static final double driveTrainPower = 0.8;

    //Drive Train
    // -1 is forward, 1 is reverse
    public static int direction = -1;

    //Motor rotations per inch for DriveInInches
    public static final double roations_per_angle = 0.1079282407;//0.117555;
    public static final double rotations_per_inch = 0.536100860941121;//0.5361;//0.5629;
    public static final double din_power = 0.5;

    //Default LimeLight Value
    public static final double defaultLimeLight = -999;

    //target height calculaitons
    public static final double heightOfTarget = 115.625;   //(h2) The height of the target 
    public static final double heightOfLimeLight = 36.125;   //(h1) The height of your camera above the floor
    public static final double angleOfLimeLight = 45; // in degrees
    public static final double shoot_distance = 55; //how far to shoot from
    public static final double x_speed = 0.8; //drive to target right/left speed
    public static final double y_speed = 0.8; //drive to trget forward/back up speed
    public static final double x_flex = 2; //amount of acceptable error in degrees when lining up on the X axis

    //color wheel colors
    public static final int color_red = 0;
    public static final int color_green = 1;
    public static final int color_blue = 2;
    public static final int color_yellow = 3;
    public static final int color_unknown = -1;
    public static final int color_error = -2;

    //limelight ledmode
    public static final int ll_current = 0;
    public static final int ll_off = 1;
    public static final int ll_blink = 2;
    public static final int ll_on = 3;

    //limelight cam mode
    public static final int ll_vision = 0;
    public static final int ll_driver = 1;

    //ball intake system variables
    public static final int startingNumberOfBalls = 0;
    public static final int ballIntakeChannel = 0;  //Analog channel ports
    public static final int ballOutputChannel = 9;  //DIO ports
    public static final int ballHoldChannel = 1; //Analog channel port
    public static final double ballIntakeSpeed = 0.5;  //ball intake motor speed
    public static final double ballElevatorSpeed = 0.5;  //ball elevator motor speed
    public static final double rotations_per_inch_elevator = 0.7; //higher values mean less movement per turn

    //ball shooter variables
    public static final double ball_shooter_max = -1.0; //need to test
    public static final double ball_shooter_max_v = -3800; //need to test
    public static final double ball_shooter_far = -0.8; //shooter speed for 18.5 feet (tested)
    public static final double ball_shooter_far_v = -3600; //motor velocity for 18.5 feet (tested)
    public static final double ball_shooter_med = -0.6; //need to test
    public static final double ball_shooter_med_v = -3400; //need to test
    public static final double ball_shooter_min = -0.5; //need to test
    public static final double ball_shooter_min_v = -3300; //need to test

    //led Lights
    public static final int LEDLightsPWMport = 0; // PWM port the LED lights are connected to
    public static final double LEDLightsInitialColor = 0.77;

    //sets mode to be in fireing
    public static boolean InFireMode = false; //when in fire mode this is true so the robot will not intkae balls
}
