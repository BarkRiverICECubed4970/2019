package utils;

import org.usfirst.frc.team4970.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionUtils {

	private static DigitalOutput led_output;
	private static NetworkTableEntry xEntry;
	private static NetworkTableEntry exposureEntry;
	public static double centerX;

    public VisionUtils() {
		NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
		NetworkTable table = ntinst.getTable("visionTable");

		xEntry = table.getEntry("centerX");
		exposureEntry = table.getEntry("piCamExposure");

		led_output = new DigitalOutput(0);
		led_output.set(false);
    }

	public static double getCenterX()
	{
		return xEntry.getDouble(0.0);
	}

	public static void setVisionAssistExposure (boolean lowExposure)
	{
		exposureEntry.setBoolean(lowExposure);
	}

	public static void turnOnLed()
	{
		led_output.set(true);
	}

	public static void turnOffLed()
	{
		led_output.set(false);
	}
}
