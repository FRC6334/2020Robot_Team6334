/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.DriverStation;


public class ColorSensor extends SubsystemBase {
  /**
   * Creates a new ColorSensor.
   */
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  public ColorSensor() {
    
  }

  /*
  In the 2020 FIRST® Robotics Competition game, the Position Control objective requires alliances to select 
  a specific color transmitted to them when specific pre-requisites have been met. The field will transmit 
  the selected color to teams using Game Data. 
  https://docs.wpilib.org/en/latest/docs/software/wpilib-overview/2020-Game-Data.html
  */
  public int getGameDataColor() {
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0)
    {
      switch (gameData.charAt(0))
      {
        case 'B' :
          return RobotMap.color_blue;
        case 'G' :
          return RobotMap.color_green;
        case 'R' :
          return RobotMap.color_red;
        case 'Y' :
          return RobotMap.color_yellow;
        default :
          //This is corrupt data
          return RobotMap.color_error;
      }
    } else {
      //Code for no data received yet
      return RobotMap.color_unknown;
    }
  }

  //get the color from what the color sensor is currently looking at
  public int detectColor() {
    Color detectedColor = m_colorSensor.getColor();
    
    if(detectedColor.red > 0.3 && detectedColor.red < detectedColor.green && detectedColor.blue < 0.3)
      return RobotMap.color_yellow;
    else if (detectedColor.red > detectedColor.blue && detectedColor.red > detectedColor.green) 
      return RobotMap.color_red;
    else if(detectedColor.green > detectedColor.red && detectedColor.green > detectedColor.blue) 
      return RobotMap.color_green;
    else if(detectedColor.blue > detectedColor.red && detectedColor.blue > detectedColor.green) 
      return RobotMap.color_blue;
    else 
      return RobotMap.color_unknown; 
  }

  public void printColorInformation() {
    Color detectedColor = m_colorSensor.getColor();
  
    System.out.println("IR:" + m_colorSensor.getIR());
    System.out.println("Proximity:" + m_colorSensor.getProximity()); 
    System.out.println("RED:" + detectedColor.red);
    System.out.println("Green:" + detectedColor.green);
    System.out.println("Blue:" + detectedColor.blue);

    if(detectedColor.red > 0.3 && detectedColor.red < detectedColor.green && detectedColor.blue < 0.3) {
      System.out.println("The color is YELLOW");
    }
    else if (detectedColor.red > detectedColor.blue && detectedColor.red > detectedColor.green) {
      System.out.println("The color is RED");
    } else if(detectedColor.green > detectedColor.red && detectedColor.green > detectedColor.blue) {
      System.out.println("The color is GREEN");
    } else if(detectedColor.blue > detectedColor.red && detectedColor.blue > detectedColor.green) {
      System.out.println("The color is BLUE");
    }  else {
      System.out.println("Color unknown...");
    }   
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}