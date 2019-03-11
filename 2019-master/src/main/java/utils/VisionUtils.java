package utils;

import org.usfirst.frc.team4970.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionUtils {

	private Spark m_ledDriver;
	private NetworkTableEntry xEntry;
	private NetworkTableEntry exposureEntry;
	public double centerX;

    public VisionUtils() {
		NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
		NetworkTable table = ntinst.getTable("visionTable");

		m_ledDriver = new Spark(0);

		xEntry = table.getEntry("centerX");
		exposureEntry = table.getEntry("piCamExposure");

		m_ledDriver.set(0.0);
    }

	public double getCenterX()
	{
		return xEntry.getDouble(0.0);
	}

	public void setVisionAssistExposure (boolean lowExposure)
	{
		exposureEntry.setBoolean(lowExposure);
	}

	public void turnOnLed()
	{
		m_ledDriver.set(0.8);
	}

	public void turnOffLed()
	{
		m_ledDriver.set(0.0);
	}
}
