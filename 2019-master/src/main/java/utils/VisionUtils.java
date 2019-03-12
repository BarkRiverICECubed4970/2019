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
	private NetworkTableEntry xValid;
	private NetworkTableEntry exposureEntry;
	public double centerX;

	public enum VisionAssistState
	{
		STOP, BALL, HATCH
	};
	
	private VisionAssistState _visionAssistState = VisionAssistState.STOP;

    public VisionUtils() {
		NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
		NetworkTable table = ntinst.getTable("visionTable");

		m_ledDriver = new Spark(0);

		xEntry = table.getEntry("centerX");
		xValid = table.getEntry("centerXValid");
		exposureEntry = table.getEntry("piCamExposure");

		m_ledDriver.set(0.0);
    }

	public double getCenterX()
	{
		return xEntry.getDouble(0.0);
	}

	public boolean centerXvalid()
	{
		return xValid.getBoolean(false);
	}

	public void setState (VisionAssistState state)
	{
		_visionAssistState = state;
	}

	public double getOffset()
	{
		double offset = 0.0;
		if (_visionAssistState == VisionAssistState.BALL)
		{
			offset = SmartDashboard.getNumber("Drive Assist Ball Image Center Pixels", Constants.ballAssistImageCenterPixels);
		} else if (_visionAssistState == VisionAssistState.HATCH)
		{
			offset = SmartDashboard.getNumber("Drive Assist Hatch Image Center Pixels", Constants.hatchAssistImageCenterPixels);
		}
		return offset;
	}

	public void setVisionAssistExposure (boolean lowExposure)
	{
		exposureEntry.setBoolean(lowExposure);
	}

	public void turnOnLed()
	{
		m_ledDriver.set(1.0);
	}

	public void turnOffLed()
	{
		m_ledDriver.set(0.0);
	}

	public void stop()
	{
		turnOffLed();
		setVisionAssistExposure(false);
		_visionAssistState = VisionAssistState.STOP;
	}
}
