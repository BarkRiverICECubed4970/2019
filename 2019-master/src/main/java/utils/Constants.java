package utils;

import org.usfirst.frc.team4970.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {
	
	/* CAN Addresses for Motor Controllers */
	public static final int leftRearDriveMotorCanAddress = 2;
	public static final int leftFrontDriveMotorCanAddress = 3;
	public static final int rightRearDriveMotorCanAddress = 4;
	public static final int rightFrontDriveMotorCanAddress = 5;

	public static final int rearClimbMotorCanAddress = 7;
	public static final int frontClimbMotorCanAddress = 8;
	public static final int rearClimbDriveMotorCanAddress = 9;

	public static final int liftMotorCanAddress = 12;
	public static final int hatchMotorCanAddress = 13;
	public static final int intakeMotorCanAddress = 14;
	
    public static double driveInchesForTest = 36.0;
    public static double straightDriveRateLimit = 0.01;
    public static double straightDriveInchesForRampDownBegin = 24.0;	
    public static double driveStraightAngleForTest = 0.0;

    /* tuned or in progress parameters */
    public static double straightDriveStartDutyCycle = 0.4;
    public static double straightDriveFinalDutyCycle = 0.25;

    public static double autoDriveStraightAutoInches = 80.0;
    
    /* counts per revolution on output shaft * inches per revolution from tires
     * 
     *  6 inch diameter wheels. 
     *  inches/rotation = 6 * pi = 18.849555
     *  counts/inch = counts/rotation / inches/rotation
     *  
     *  */
    
	// -1408 counts on right encoder per forward rev
	// 714 counts on left encoder per forward rev
    // use left encoder for now
    // 714 counts per 18.85 inches = 37.87
    public static double driveEncoderCountsPerInch = 74.7;
	
	public static double driveAssistDutyCycle = 0.2;
	public static double driveAssistTimeout = 3.0;
	public static double driveAssistPixelsToDegrees = 0.05;
	public static double hatchAssistImageCenterPixels = 0.0;
	public static double ballAssistImageCenterPixels = 0.0;

//    public static double turnDegreesTimeout = 90.0;
    public static double turnDegreesTimeout = 2.0;
    public static double turnDegrees = -90.0;
    
    public static double gyroStraightPidKp = 0.08;
    public static double gyroTurnPidKp = 0.08;
    public static double gyroReversePidKp = 0.05;
    public static double gyroPidKi = 0.0;
    public static double gyroPidKd = 0.0;
    public static double gyroPidMinIn = -120.0;
    public static double gyroPidMaxIn = 120.0;
    public static double gyroPidMinOut = -1.0;
    public static double gyroPidMaxOut = 1.0;
    public static double gyroPidTolerance = 2.0;
    public static double gyroPidMaxSetpoint = 8;
    
    public static double intakeMotorPidKp = 0.5;
    public static double intakeMotorPidKi = 0.0;
    public static double intakeMotorPidKd = 0.0;
    public static double intakeMotorAllowableClosedLoopError = 10;

    public static double hatchMotorUpDutyCycle = 0.35;
    public static double hatchMotorDownDutyCycle = 0.3;

	public static double hatchCommandTimeout = 1.0;

	public static double ballOutputAutoTimeout = 2.0;
    
	public static double liftMotorPidKp = 5.0;
    public static double liftMotorPidKi = 0.0;
    public static double liftMotorPidKd = 0.0;
	public static double liftMotorPidKf = 0.0;
	public static double liftMotorMotionCruiseVelocity = 4000.0;
	public static double liftMotorMotionAcceleration = 2000.0;
    public static double liftMotorAllowableClosedLoopError = 10;
	public static double liftMotorPeakVoltage = 1.0;

	public static double liftBallIntakePositionSetpoint = 50.0;
	public static double liftRocketPositionSetpoint = 7580.0;
	public static double liftHatchIntakePositionSetpoint = 2250.0;
    public static double liftHatchPlaceLowerPositionSetpoint = 1100.0;
	public static double liftHatchPlaceUpperPositionSetpoint = 10975.0;

	public static double liftCommandTimeout = 3.0;
	public static double liftTestDutyCycle = 0.4;

    public static double intakeBallDutyCycle = 0.3;
    public static double outputBallDutyCycle = 0.4;

	public static double rearClimbStartPositionSetpoint = 0.0;
	public static double rearClimbPlatformPositionSetpoint = 5200.0;
	public static double rearClimbFinalPositionSetpoint = 8500.0;
	public static double rearClimbCommandTimeout = 3.0;
	public static double rearClimbAllowableClosedLoopError = 100;

	public static double rearClimbMotorPidKp = 0.5;
    public static double rearClimbMotorPidKi = 0.0;
    public static double rearClimbMotorPidKd = 0.0;
	public static double rearClimbMotorPidKf = 0.0;
	public static double rearClimbMotorMotionCruiseVelocity = 4000.0;
	public static double rearClimbMotorMotionAcceleration = 2000.0;
    public static double rearClimbMotorAllowableClosedLoopError = 100;
	public static double rearClimbMotorPeakVoltage = 1.0;

	public static double climbDriveDutyCycle = 0.8;
	public static double frontClimbTestDutyCycle = 0.8;
	public static double frontClimbTestDutyCycleReverse = 0.5;
	public static double rearClimbForwardTestDutyCycle = 0.8;
	public static double rearClimbReverseTestDutyCycle = 0.5;
	public static double platformDriveDutyCycle = 0.15;

	public static double platformDriveTimeout = 4.0;

	public static double stallCommandTimeout = 1.0;

	public static double frontClimbStartPositionSetpoint = 0.0;
	public static double frontClimbPlatformPositionSetpoint = -1150.0;
	public static double frontClimbFinalPositionSetpoint = -3100.0;
	public static double frontClimbCommandTimeout = 3.0;
	public static double frontClimbAllowableClosedLoopError = 100;

	public static double frontClimbMotorPidKp = 0.75;
    public static double frontClimbMotorPidKi = 0.0;
    public static double frontClimbMotorPidKd = 0.0;
	public static double frontClimbMotorPidKf = 0.0;
	public static double frontClimbMotorMotionCruiseVelocity = 4000.0;
	public static double frontClimbMotorMotionAcceleration = 2000.0;
    public static double frontClimbMotorAllowableClosedLoopError = 100;
	public static double frontClimbMotorPeakVoltage = 1.0;

    public static final int timeoutMs = 10;
    
    public Constants() {
    	
    	postConstants();
    	
	    new Thread(() -> {
		   	while (true) {
		   		updateSmartDashboard();
		   		Timer.delay(1.0);
		   	}
		}).start();
    }
    
    /* post the constants to the shuffleboard */
	private void postConstants() {
		/* drive train */
		SmartDashboard.putNumber("Inches to drive for test", driveInchesForTest);
		SmartDashboard.putNumber("Degrees to turn", turnDegrees);
		SmartDashboard.putNumber("Autonomous drive inches", autoDriveStraightAutoInches);
				
		/* drive assist */
		SmartDashboard.putNumber("Drive Assist DutyCycle", driveAssistDutyCycle);
		SmartDashboard.putNumber("Drive Assist Timeout", driveAssistTimeout);
		SmartDashboard.putNumber("Drive Assist Pixels to Degrees", driveAssistPixelsToDegrees);
		SmartDashboard.putNumber("Drive Assist Hatch Image Center Pixels", hatchAssistImageCenterPixels);
		SmartDashboard.putNumber("Drive Assist Ball Image Center Pixels", ballAssistImageCenterPixels);


		/* consider ramping function on the talons */
		SmartDashboard.putNumber("Straight drive start duty cycle", straightDriveStartDutyCycle);
		SmartDashboard.putNumber("Straight drive final duty cycle", straightDriveFinalDutyCycle);
		SmartDashboard.putNumber("Straight drive rate limit", straightDriveRateLimit);
		SmartDashboard.putNumber("Straight drive ramp down inches", straightDriveInchesForRampDownBegin);
		SmartDashboard.putNumber("Gyro Turn Degrees PID KP", Constants.gyroTurnPidKp);    		
    	SmartDashboard.putNumber("Gyro Drive Reverse PID KP", Constants.gyroReversePidKp);    		    		
    	SmartDashboard.putNumber("Gyro Drive Straight PID KP", Constants.gyroStraightPidKp);    		
    	SmartDashboard.putNumber("Gyro PID KI", gyroPidKi);
    	SmartDashboard.putNumber("Gyro PID KD", gyroPidKd);
    	SmartDashboard.putNumber("Gyro PID Min Input", gyroPidMinIn);
    	SmartDashboard.putNumber("Gyro PID Max Input", gyroPidMaxIn);
    	SmartDashboard.putNumber("Gyro PID Min Output", gyroPidMinOut);
    	SmartDashboard.putNumber("Gyro PID Max Output", gyroPidMaxOut);
    	SmartDashboard.putNumber("Gyro PID Tolerance", gyroPidTolerance);
    	SmartDashboard.putNumber("Gyro PID Max Setpoint", gyroPidMaxSetpoint);
    	SmartDashboard.putNumber("Drive Encoder Counts Per Inch", driveEncoderCountsPerInch);
    	SmartDashboard.putNumber("Turn Degrees Timeout", turnDegreesTimeout);
    	SmartDashboard.putNumber("Drive straight angle for test", driveStraightAngleForTest);

		/* Lift motor */
    	SmartDashboard.putNumber("Lift PID KP", liftMotorPidKp);
    	SmartDashboard.putNumber("Lift PID KI", liftMotorPidKi);
    	SmartDashboard.putNumber("Lift PID KD", liftMotorPidKd);
		SmartDashboard.putNumber("Lift PID KF", liftMotorPidKf);
		SmartDashboard.putNumber("Lift Motion Cruise Velocity", liftMotorMotionCruiseVelocity);
		SmartDashboard.putNumber("Lift Motion Acceleration", liftMotorMotionAcceleration);
    	SmartDashboard.putNumber("Lift Peak Voltage", liftMotorPeakVoltage);
    	SmartDashboard.putNumber("Lift PID Allowable Error", liftMotorAllowableClosedLoopError);
    	SmartDashboard.putNumber("Lift Ball Intake Setpoint", liftBallIntakePositionSetpoint);
    	SmartDashboard.putNumber("Lift Rocket Setpoint", liftRocketPositionSetpoint);
		SmartDashboard.putNumber("Lift Hatch Intake Setpoint", liftHatchIntakePositionSetpoint);
		SmartDashboard.putNumber("Lift Hatch Lower Setpoint", liftHatchPlaceLowerPositionSetpoint);
		SmartDashboard.putNumber("Lift Hatch Upper Setpoint", liftHatchPlaceUpperPositionSetpoint);
		SmartDashboard.putNumber("Lift Command Timeout", liftCommandTimeout);
		SmartDashboard.putNumber("Lift Test Duty Cycle", liftTestDutyCycle);
    	
    	/* Intake motor */
    	SmartDashboard.putNumber("Intake Ball Duty Cycle", intakeBallDutyCycle);
    	SmartDashboard.putNumber("Output Ball Duty Cycle", outputBallDutyCycle);   
    	SmartDashboard.putNumber("Ball Output Auto Timeout", ballOutputAutoTimeout);
		
		/* Hatch motor */
    	SmartDashboard.putNumber("Hatch Up Duty Cycle", hatchMotorUpDutyCycle);
    	SmartDashboard.putNumber("Hatch Down Duty Cycle", hatchMotorDownDutyCycle);
		SmartDashboard.putNumber("Hatch Command Timeout", hatchCommandTimeout);

		/* Climb Drive */
		SmartDashboard.putNumber("Climber Drive Duty Cycle", climbDriveDutyCycle);

		/* Main Drive System Duty Cycle for Automatic Climbing */
		SmartDashboard.putNumber("Platform drive duty cycle", platformDriveDutyCycle);

    	SmartDashboard.putNumber("Stall Command Timeout", stallCommandTimeout);

		/* Front Climber */
		SmartDashboard.putNumber("Front Climb Platform Setpoint", frontClimbPlatformPositionSetpoint);
		SmartDashboard.putNumber("Front Climb Start Setpoint", frontClimbStartPositionSetpoint);
		SmartDashboard.putNumber("Front Climb Final Setpoint", frontClimbFinalPositionSetpoint);		
		SmartDashboard.putNumber("Front Climb Test Duty Cycle", frontClimbTestDutyCycle);
		SmartDashboard.putNumber("Front Climb PID KP", frontClimbMotorPidKp);
		SmartDashboard.putNumber("Front Climb PID KI", frontClimbMotorPidKi);
		SmartDashboard.putNumber("Front Climb PID KD", frontClimbMotorPidKd);
		SmartDashboard.putNumber("Front Climb PID KF", frontClimbMotorPidKf);
		SmartDashboard.putNumber("Front Climb Motion Cruise Velocity", frontClimbMotorMotionCruiseVelocity);
		SmartDashboard.putNumber("Front Climb Motion Acceleration", frontClimbMotorMotionAcceleration);
		SmartDashboard.putNumber("Front Climb Peak Voltage", frontClimbMotorPeakVoltage);
		
		/* Rear Climber */
		SmartDashboard.putNumber("Rear Climb Platform Setpoint", rearClimbPlatformPositionSetpoint);
		SmartDashboard.putNumber("Rear Climb Start Setpoint", rearClimbStartPositionSetpoint);
		SmartDashboard.putNumber("Rear Climb Final Setpoint", rearClimbFinalPositionSetpoint);		
		SmartDashboard.putNumber("Rear Climb Forward Test Duty Cycle", rearClimbForwardTestDutyCycle);
		SmartDashboard.putNumber("Rear Climb Reverse Test Duty Cycle", rearClimbReverseTestDutyCycle);
		SmartDashboard.putNumber("Rear Climb PID KP", rearClimbMotorPidKp);
		SmartDashboard.putNumber("Rear Climb PID KI", rearClimbMotorPidKi);
		SmartDashboard.putNumber("Rear Climb PID KD", rearClimbMotorPidKd);
		SmartDashboard.putNumber("Rear Climb PID KF", rearClimbMotorPidKf);
		SmartDashboard.putNumber("Rear Climb Motion Cruise Velocity", rearClimbMotorMotionCruiseVelocity);
		SmartDashboard.putNumber("Rear Climb Motion Acceleration", rearClimbMotorMotionAcceleration);
		SmartDashboard.putNumber("Rear Climb Peak Voltage", rearClimbMotorPeakVoltage);
				
	   	/* CAN Addresses for Talons */
    	SmartDashboard.putNumber("Left Rear Drive Motor CAN Address", leftRearDriveMotorCanAddress);   
    	SmartDashboard.putNumber("Left Front Drive Motor CAN Address", leftFrontDriveMotorCanAddress);   
    	SmartDashboard.putNumber("Right Rear Drive Motor CAN Address", rightRearDriveMotorCanAddress);   
    	SmartDashboard.putNumber("Right Front Drive Motor CAN Address", rightFrontDriveMotorCanAddress);   
    	SmartDashboard.putNumber("Intake Motor CAN Address", intakeMotorCanAddress);   
    	SmartDashboard.putNumber("Lift Motor CAN Address", liftMotorCanAddress);   
    	SmartDashboard.putNumber("Hatch Motor CAN Address", hatchMotorCanAddress);   
	}
    
	/* periodically publish outputs */
    private void updateSmartDashboard() {

    	/* 
    	 * put an instance of the PDP to shuffleboard... this may help to see
    	 * issues with motors, etc...
    	 */
//    	SmartDashboard.putData("Power Distribution Panel", Robot.pdp);
    	
		/* joystick */
		SmartDashboard.putNumber("Joystick forward", Robot.m_oi.joystick.getRawAxis(1));
		SmartDashboard.putNumber("Joystick rotate", Robot.m_oi.joystick.getRawAxis(0));

		/* drive train */
		SmartDashboard.putNumber("Pigeon fused heading", Robot._driveTrain.getGyroHeading());
		SmartDashboard.putData(Robot._driveTrain._gyro);
		SmartDashboard.putData(Robot._driveTrain._gyroPid);

		SmartDashboard.putNumber("Right Encoder Count", Robot._driveTrain.getRightEncoderCount());
		SmartDashboard.putNumber("Left Encoder Count", Robot._driveTrain.getLeftEncoderCount());
		SmartDashboard.putNumber("Gyro PID output value", Robot._driveTrain.getPidOutput());
    	SmartDashboard.putNumber("Drive Encoder Counts Per Inch", driveEncoderCountsPerInch);

    	/* Lift motor */
    	SmartDashboard.putNumber("Lift Encoder Count", Robot._liftMotor.getEncoderCount());
    	SmartDashboard.putNumber("Lift Closed Loop Error", Robot._liftMotor.getClosedLoopError());
		SmartDashboard.putNumber("Lift Motor Output Voltage", Robot._liftMotor.getMotorOutputVoltage());
		
		/* vision utils */
		SmartDashboard.putNumber("CenterX from Pi Camera", Robot._visionUtils.getCenterX());

		/* rear climb */
		SmartDashboard.putNumber("Rear climber encoder count", Robot._rearClimber.getEncoderCount());

		/* front climb */
		SmartDashboard.putNumber("Front climber encoder count", Robot._frontClimber.getEncoderCount());
	}

}
