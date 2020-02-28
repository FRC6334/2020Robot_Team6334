/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.RobotMap;

/*
API Info - http://docs.limelightvision.io/en/latest/networktables_api.html
tv	Whether the limelight has any valid targets (0 or 1)
tx	Horizontal Offset From Crosshair To Target (LL1: -27 degrees to 27 degrees | LL2: -29.8 to 29.8 degrees)
ty	Vertical Offset From Crosshair To Target (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to 24.85 degrees)
ta	Target Area (0% of image to 100% of image)
ts	Skew or rotation (-90 degrees to 0 degrees)
tl	The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.
tshort	Sidelength of shortest side of the fitted bounding box (pixels)
tlong	Sidelength of longest side of the fitted bounding box (pixels)
thor	Horizontal sidelength of the rough bounding box (0 - 320 pixels)
tvert	Vertical sidelength of the rough bounding box (0 - 320 pixels)
getpipe	True active pipeline index of the camera (0 .. 9)
camtran	Results of a 3D position solution, 6 numbers: Translation (x,y,y) Rotation(pitch,yaw,roll)
*/

public class LimeLightVision extends SubsystemBase {
  private static NetworkTableInstance nTable = null;
  private static String table;

  /**
   * Creates a new LimeLightVision.
   */
  public LimeLightVision(String _table) {
    table = _table;
  }

  public void outputValues() {
      this.setLedMode(RobotMap.ll_on);
      this.setCameraMode(RobotMap.ll_vision);
      System.out.println(">>>>>>>>>>>>>>tx="+getValue("tx").getDouble(RobotMap.defaultLimeLight)+
        ", ty="+getValue("ty").getDouble(RobotMap.defaultLimeLight)+
        ",ta="+getValue("ta").getDouble(RobotMap.defaultLimeLight)+
        ",tv="+getValue("tv").getDouble(RobotMap.defaultLimeLight)+
        ",ts="+getValue("ts").getDouble(RobotMap.defaultLimeLight));

      //System.out.println("limelight="+NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(-999));
      //System.out.println("balltarget="+NetworkTableInstance.getDefault().getTable("balltarget").getEntry("tv").getDouble(-999));
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
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
    } else{
      setLedMode(RobotMap.ll_on);
      setCameraMode(RobotMap.ll_vision);
    } 
      
  }
  
  /**
	 * Toggles led light mode for Limelight between on / off
	 */
	public void toggleLedMode() {
    if (getValue("ledMode").getDouble(RobotMap.defaultLimeLight) == RobotMap.ll_on)
      setLedMode(RobotMap.ll_off);
    else 
      setLedMode(RobotMap.ll_on);
	}
}
