/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;


public class LimeLightTarget extends SubsystemBase {
  private static String table;
  private static NetworkTableInstance nTable = null;
  private static LedTargetRings rings;


  /**
   * Creates a new LimeLightTarget.
   */
  public LimeLightTarget(LedTargetRings _rings) {
    table = "limelight-target";
    rings = _rings;
  }

  public void outputLimeLightValues() {
    setCameraMode(RobotMap.ll_vision);
    setLedMode(RobotMap.ll_off);
    rings.turnOn();

    System.out.println(">>>>>>>>>>>>>>tx="+getValue("tx").getDouble(RobotMap.defaultLimeLight)+
      ", ty="+getValue("ty").getDouble(RobotMap.defaultLimeLight)+
      ",ta="+getValue("ta").getDouble(RobotMap.defaultLimeLight)+
      ",tv="+getValue("tv").getDouble(RobotMap.defaultLimeLight)+
      ",ts="+getValue("ts").getDouble(RobotMap.defaultLimeLight));    System.out.println("distance to target (inches): "+this.getDistanceToTarget());

      rings.turnOff();
  }

  /**
	 * Helper method to get an entry from the Limelight NetworkTable.
	 * 
	 * @param key
	 *            Key for entry.
	 * @return NetworkTableEntry of given entry.
	 */
	private static NetworkTableEntry getValue(String key) {
		// if (nTable == null) {
		// 	nTable = NetworkTableInstance.getDefault();
		// }

    return NetworkTableInstance.getDefault().getTable(table).getEntry(key);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //working within 1-2" of actual distance, a wider angle makes it less accurate
  public double getDistanceToTarget() {
    rings.turnOn();
    this.setLedMode(RobotMap.ll_off);
    
    if (this.getTV() != 1.0) {
      rings.turnOff();
      RobotMap.current_distance_to_target = RobotMap.defaultLimeLight;
      System.out.println("LimeLightTarget:getDistanceToTarget:: cannot find a target, distance="+RobotMap.current_distance_to_target);
      return RobotMap.defaultLimeLight;
    }
    
    //d = (h2-h1) / tan(a1+a2)
    double heightOfTarget = RobotMap.heightOfTarget; //h2 
    double heightOfLimeLight = RobotMap.heightOfTargetLimeLight; // h1
    double a2 = this.getTY(); // The limelight (or your vision system) can tell you the y angle to the target (a2).
    double a1 = RobotMap.angleOfTargetLimeLight;   //lime light mounting angle is known (a1)

    //calculate distance
    rings.turnOff();
    double d = (heightOfTarget - heightOfLimeLight) / Math.tan(Math.toRadians(a1+a2));
    RobotMap.current_distance_to_target = d;
    System.out.println("LimeLightTarget:getDistanceToTarget:: distance="+RobotMap.current_distance_to_target);
    return d;
  }

  //X offset to center of target
  public double getTX() { return getValue("tx").getDouble(RobotMap.defaultLimeLight); }

  //The limelight (or your vision system) can tell you the y angle to the target (a2).
  public double getTY() { return getValue("ty").getDouble(RobotMap.defaultLimeLight); }

  //target area
  public double getTA() { return getValue("ta").getDouble(RobotMap.defaultLimeLight); }

  //is a valid target - 1.0 means valid target
  public double getTV() { return getValue("tv").getDouble(RobotMap.defaultLimeLight); }

  //target scew
  public double getTS() { return getValue("ts").getDouble(RobotMap.defaultLimeLight); }

  /**
	 * Sets pipeline number (0-9 value).
	 * 
	 * @param number
	 *            Pipeline number (0-9).
	 */
	public static void setPipeline(int number) {
		getValue("pipeline").setNumber(number);
  }

  /**
	 * Sets LED mode of Limelight.
	 * 
	 * @param mode
	 *            Light mode for Limelight.
	 */
	public void setLedMode(int ll_ledmode) {
		getValue("ledMode").setNumber(ll_ledmode);
	}
  
  /**
	 * Sets camera mode for Limelight.
	 * 
	 * @param mode
	 *            Camera mode for Limelight.
	 */
	public void setCameraMode(int ll_cammode) {
		getValue("camMode").setNumber(ll_cammode);
  }
  
   /**
	 * Toggles camera mode for Limelight between driver and vision
	 */
	public void toggleCameraMode() {
    if (getValue("camMode").getDouble(RobotMap.defaultLimeLight) == RobotMap.ll_vision) {
      setLedMode(RobotMap.ll_off);
      setCameraMode(RobotMap.ll_driver);
      rings.turnOff();
    } else{
      setCameraMode(RobotMap.ll_vision);
      setLedMode(RobotMap.ll_off);
      rings.turnOn();
    } 
      
  }

}
