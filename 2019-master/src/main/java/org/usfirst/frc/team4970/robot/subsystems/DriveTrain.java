/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import org.usfirst.frc.team4970.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team4970.robot.Robot;

import utils.Gyro;
import utils.Constants;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem implements PIDOutput {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public enum DriveTrainControl
	{
		STOP, JOYSTICK, DRIVE_STRAIGHT, DRIVE_STRAIGHT_REVERSE, TURN_DEGREES, DRIVE_ASSIST
	};
	
	private DriveTrainControl _driveTrainControl = DriveTrainControl.STOP;
    private static double PID_rotateValue;
    private static double forward;
    private static double rotate;
    /* used for ramp up in autonomous to prevent lurching */
//    private static double prevForward;
    private static boolean squaredInputs = true;
    
    private double gyroAngle;
    private double potentialError;

    /* drive motors and differential drive */
	CANSparkMax m_leftRear = new CANSparkMax(Constants.leftRearDriveMotorCanAddress, MotorType.kBrushless);
	CANSparkMax m_leftFront = new CANSparkMax(Constants.leftFrontDriveMotorCanAddress, MotorType.kBrushless);
	CANSparkMax m_rightRear = new CANSparkMax(Constants.rightRearDriveMotorCanAddress, MotorType.kBrushless);
	CANSparkMax m_rightFront = new CANSparkMax(Constants.rightFrontDriveMotorCanAddress, MotorType.kBrushless);

	private CANEncoder m_leftEncoder;
	private CANEncoder m_rightEncoder;	

//	WPI_TalonSRX m_leftRear = new WPI_TalonSRX(Constants.leftRearDriveMotorCanAddress);
//	WPI_TalonSRX m_leftFront = new WPI_TalonSRX(Constants.leftFrontDriveMotorCanAddress);
//	WPI_TalonSRX m_rightRear = new WPI_TalonSRX(Constants.rightRearDriveMotorCanAddress);
//	WPI_TalonSRX m_rightFront = new WPI_TalonSRX(Constants.rightFrontDriveMotorCanAddress);
	
	SpeedControllerGroup m_left = new SpeedControllerGroup(m_leftFront, m_leftRear);
	SpeedControllerGroup m_right = new SpeedControllerGroup(m_rightFront, m_rightRear);
	
    private final DifferentialDrive _robotDrive = new DifferentialDrive(m_left, m_right);

	public Gyro _gyro = new Gyro(Robot.m_intake);
	
    public final PIDController _gyroPid = new PIDController(Constants.gyroStraightPidKp, Constants.gyroPidKi, Constants.gyroPidKd, _gyro, this);
	
	public DriveTrain()
	{
		m_leftFront.restoreFactoryDefaults();
		m_leftRear.restoreFactoryDefaults();
		m_rightFront.restoreFactoryDefaults();
		m_rightRear.restoreFactoryDefaults();
/*
		m_leftFront.configFactoryDefault();
		m_leftRear.configFactoryDefault();
		m_rightFront.configFactoryDefault();
		m_rightRear.configFactoryDefault();
*/
		m_leftEncoder = m_leftFront.getEncoder();
		m_rightEncoder = m_rightFront.getEncoder();

//		m_leftFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Constants.timeoutMs);
//    	m_rightFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Constants.timeoutMs);
    	
		/* invert right encoder to count forward when drive is moving forward */
//		m_rightFront.setSensorPhase(true);
		
    	/* reset encoder counters */
		m_leftEncoder.setPosition(0.0);
		m_rightEncoder.setPosition(0.0);

//    	m_leftFront.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
//    	m_rightFront.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
    	
    	m_leftFront.setInverted(true);
    	m_rightFront.setInverted(true);
    	m_leftRear.setInverted(true);
    	m_rightRear.setInverted(true);
    	
    	_gyroPid.setName("Gyro PID");
    	_gyro.setName("Pigeon");
    	
    	
		_robotDrive.setMaxOutput(1.0);

    	// just in case
//    	prevForward = 0;
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveWithJoystick());		
	}
	
    /* forward duty cycle is only used for autonomous straight drive and reverse drive */
    public void controlLoop(DriveTrainControl commandInControl, double forwardDutyCycle)
    {
    	_driveTrainControl = commandInControl;
    	
    	switch (_driveTrainControl) {
    	
	    	case JOYSTICK:
	    		squaredInputs = true;
    			forward = Robot.m_oi.joystick.getRawAxis(1);
	    		rotate = -Robot.m_oi.joystick.getRawAxis(0);
	    		break;

	    	case TURN_DEGREES:
	    		squaredInputs = false;
	    		forward = 0.0;
	    		rotate = PID_rotateValue;	    		
	    		break;
	    		
	    	case DRIVE_STRAIGHT:
	    		squaredInputs = false;
	   			forward = -forwardDutyCycle;	    		
	    		rotate = PID_rotateValue;
	    		break;
	    		
	    	case DRIVE_STRAIGHT_REVERSE:
	    		squaredInputs = false;
	   			forward = forwardDutyCycle;	    		
	    		rotate = PID_rotateValue;
	    		break;

			case DRIVE_ASSIST:
				squaredInputs = false;
				forward = -Constants.driveAssistDutyCycle;
				rotate = -PID_rotateValue;
				break;

	    	case STOP:
	    		squaredInputs = false;
	    		forward = 0.0;
	    		rotate = 0.0;
	    		break;

	    	default:
	    		squaredInputs = false;
	    		forward = 0.0;
	    		rotate = 0.0;
	    		break;
    	}

		/* try this to potentially turn better with only high gear */
		if (_driveTrainControl == DriveTrainControl.TURN_DEGREES)
		{
			if (rotate < 0.0)
			{
				_robotDrive.tankDrive(-Math.abs(rotate), 0.0);				
			} else
			{
				_robotDrive.tankDrive(0.0, -Math.abs(rotate));
			}
		} else
		{
			_robotDrive.arcadeDrive(forward, rotate, squaredInputs);
		}
    }
    
    public void setDriveTrainBrakeMode(boolean enabled)
    {
    	if (enabled)
    	{
			m_leftFront.setIdleMode(CANSparkMax.IdleMode.kBrake);
	    	m_rightFront.setIdleMode(CANSparkMax.IdleMode.kBrake);
			m_leftRear.setIdleMode(CANSparkMax.IdleMode.kBrake);
	    	m_rightRear.setIdleMode(CANSparkMax.IdleMode.kBrake);
//			m_leftFront.setNeutralMode(NeutralMode.Brake);
//	    	m_rightFront.setNeutralMode(NeutralMode.Brake);
//			m_leftRear.setNeutralMode(NeutralMode.Brake);
//	    	m_rightRear.setNeutralMode(NeutralMode.Brake);
    	} else {
			m_leftFront.setIdleMode(CANSparkMax.IdleMode.kCoast);
	    	m_rightFront.setIdleMode(CANSparkMax.IdleMode.kCoast);
			m_leftRear.setIdleMode(CANSparkMax.IdleMode.kCoast);
			m_rightRear.setIdleMode(CANSparkMax.IdleMode.kCoast);
//			m_leftFront.setNeutralMode(NeutralMode.Coast);
//	    	m_rightFront.setNeutralMode(NeutralMode.Coast);
//			m_leftRear.setNeutralMode(NeutralMode.Coast);
//	    	m_rightRear.setNeutralMode(NeutralMode.Coast);
    	}
    }
    
    public void resetEncoders()
    {
		/* reset encoder counters */
		m_leftEncoder.setPosition(0.0);
		m_rightEncoder.setPosition(0.0);
//    	m_leftFront.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
//    	m_rightFront.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
    }
    
    public double getRightEncoderCount()
    {
		return m_rightEncoder.getPosition();
//		return m_rightFront.getSelectedSensorPosition(0);
    }
    
    public double getLeftEncoderCount()
    {
		return m_leftEncoder.getPosition();
//    	return m_leftFront.getSelectedSensorPosition(0);
    }
    
    public double getGyroHeading()
    {
    	return _gyro.getAngle();
    }
    
    public void setupGyroPID(DriveTrainControl commandInControl)
    {
    	double kp;
    	if (commandInControl == DriveTrainControl.TURN_DEGREES)
    	{
        	kp = SmartDashboard.getNumber("Gyro Turn Degrees PID KP", Constants.gyroTurnPidKp);    		
    	} else if (commandInControl == DriveTrainControl.DRIVE_STRAIGHT_REVERSE)
    	{
        	kp = SmartDashboard.getNumber("Gyro Drive Reverse PID KP", Constants.gyroReversePidKp);    		    		
    	} else {
        	kp = SmartDashboard.getNumber("Gyro Drive Straight PID KP", Constants.gyroStraightPidKp);    		
    	}
    		
    	Constants.gyroPidKi = SmartDashboard.getNumber("Gyro PID KI", Constants.gyroPidKi);
    	Constants.gyroPidKd = SmartDashboard.getNumber("Gyro PID KD", Constants.gyroPidKd);
    	Constants.gyroPidMinIn = SmartDashboard.getNumber("Gyro PID Min Input", Constants.gyroPidMinIn);
    	Constants.gyroPidMaxIn = SmartDashboard.getNumber("Gyro PID Max Input", Constants.gyroPidMaxIn);
    	Constants.gyroPidMinOut = SmartDashboard.getNumber("Gyro PID Min Output", Constants.gyroPidMinOut);
    	Constants.gyroPidMaxOut = SmartDashboard.getNumber("Gyro PID Max Output", Constants.gyroPidMaxOut);
    	Constants.gyroPidTolerance = SmartDashboard.getNumber("Gyro PID Tolerance", Constants.gyroPidTolerance);
    	Constants.gyroPidMaxSetpoint = SmartDashboard.getNumber("Gyro PID Max Setpoint", Constants.gyroPidMaxSetpoint);

    	_gyroPid.reset();
		_gyroPid.setPID(kp, Constants.gyroPidKi , Constants.gyroPidKd);
		_gyroPid.setInputRange(Constants.gyroPidMinIn, Constants.gyroPidMaxIn);
		_gyroPid.setOutputRange(Constants.gyroPidMinOut, Constants.gyroPidMaxOut);
		_gyroPid.setAbsoluteTolerance(Constants.gyroPidTolerance);
		_gyroPid.setSetpoint(0.0);
		
		/*
		 *  commands should be calling this, but call this just in case
		 *  since the pidSetpoint is initialized to 0.0 for ramping purposes
		 */
		_gyro.reset();
		
		_gyroPid.enable();
    }

	public void resetGyro()
	{
		_gyro.reset();
	}

    public void setGyroPidSetpoint(double setPoint)
    {
    	gyroAngle = _gyro.getAngle();
    	potentialError = setPoint - gyroAngle;
    	
    	/* 
    	 * the first check represents the possible error if the PID were fed
    	 * the input parameter "setPoint". If this potential error is too large, 
    	 * then reduce it to something that won't go wild with a more aggressive 
    	 * kP term
    	 */
    	if ((Math.abs(potentialError)) > Constants.gyroPidMaxSetpoint)
    	{    		
    		/*
    		 * if setpoint is greater than angle, then add the max setpoint to the
    		 * current angle, or else subtract the max setpoint from the current
    		 * angle
    		 */
    		_gyroPid.setSetpoint((Math.copySign(Constants.gyroPidMaxSetpoint, potentialError) + gyroAngle));
    	} else
    	{
    		_gyroPid.setSetpoint(setPoint);    
    	}
    }
    
    public double getGyroPidSetpoint()
    {
    	return _gyroPid.getSetpoint();
    }
    
    static int onTargetCount = 0;
    static final int onTargetThresh = 10;
    public boolean gyroPidOnTarget()
    {
    	if (Math.abs(_gyroPid.getError()) < Constants.gyroPidTolerance)
    	{
    		onTargetCount++;
    		if (onTargetCount >= onTargetThresh)
    		{
    			onTargetCount = 0;
    			return true;
    		}
    	} else 
    	{
    		onTargetCount = 0;
    		return false;
    	}
    	
    	return false;
    }
    
    public void resetOnTargetCount()
    {
    	onTargetCount = 0;
    }
    
	@Override
	public void pidWrite(double output) {
		PID_rotateValue = output;
	}
	
	public double getPidOutput()
	{
		return PID_rotateValue;
	}
}
