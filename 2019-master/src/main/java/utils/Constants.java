package utils;

import org.usfirst.frc.team4970.robot.Robot;
import org.usfirst.frc.team4970.robot.commands.DriveStraight;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {
	
	/* CAN Addresses for Motor Controllers */
	public static final int leftRearDriveMotorCanAddress = 2;
	public static final int leftFrontDriveMotorCanAddress = 3;
	public static final int rightRearDriveMotorCanAddress = 4;
	public static final int rightFrontDriveMotorCanAddress = 5;

	public static final int liftMotorCanAddress = 12;
	public static final int hatchMotorCanAddress = 13;
	public static final int intakeMotorCanAddress = 14;
	
    public static double autoScaleTurnDegrees = -45.0;
    public static double autoNinetyDegrees = -40.0;
    public static double driveInchesForTest = 36.0;
    public static double timedDriveTimeout = 1.0;
    public static double autoStraightDriveToCloseScaleInches = 240.0;
    public static double autoAngleDriveToCloseScaleInches = 80.0;
    public static double autoDrivePastSwitchInches = 174.0;
    public static double autoDriveAcrossSwitchInches = 168.0;
    public static double autoDriveAcrossScaleInches = 286.0;
    public static double autoDriveToNullZone = 8.0;
    public static double autoDriveToOppositeSwitchZone = 20.0;
    public static double autoDriveToFenceFromSwitchZone = 20.0;
    public static double autoOppositeScaleTurnDegrees = -120.0;
    public static double autoReverseDriveInches = 36.0;
    public static double autoTurnDegreesFromCenter = -25.0;
    public static double autoTurnDegreesToCloseScale = -30.0;
    public static double autoDriveToSwitchFromCenterTimeout = 2.5;
    public static double scaleInches = 100.0;
    public static double armDownMaxDriveDutyCycle = 1.0;
    public static double armUpMaxDriveDutyCycle = 0.75;
    public static double straightDriveRateLimit = 0.01;
    public static double straightDriveInchesForRampDownBegin = 24.0;	
    public static double driveStraightAngleForTest = 0.0;

    /* tuned or in progress parameters */
    public static double straightDriveStartDutyCycle = 0.4;
    public static double straightDriveFinalDutyCycle = 0.25;

    public static double autoDriveToFenceFromCenter = 90.0;

    public static double autoSwitchFromCenterLeftDegreeAdder = 2.0;
	public static double autoSwitchFromCenterLeftDistanceAdder = 20.0;

    public static double autoDriveStraightAutoInches = 80.0;
    public static double autoStraightDriveToCloseSwitchInches = 95.0;
    public static double autoAngleDriveToCloseSwitchInches = 20.0;
    public static double autoSwitchTurnDegreesFromSide = -20.0;

    
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
    
    
//    public static double turnDegreesTimeout = 90.0;
    public static double turnDegreesTimeout = 2.0;
    public static double turnDegrees = -90.0;
    
    public static double gyroStraightPidKp = 0.04;
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
    
    public static double hingeMotorPidKp = 0.75;
    public static double hingeMotorLowerPidKp = 0.3;
    public static double hingeMotorPidKi = 0.0;
    public static double hingeMotorPidKd = 0.0;
    public static double hingeMotorAllowableClosedLoopError = 50;
    public static double raiseHingePidSetpoint = 0.0;
    public static double hingeToScalePidSetpoint = 1500.0;
    public static double lowerHingePidSetpoint = 2000.0;
    public static double lowerHingeTimeout = 1.0;
    public static double raiseHingeTimeout = 1.0;
    public static double hingeSecondsFromNeutral = 0.5;
    public static double hingeMotorPeakVoltage = 0.7;

//    public static double raiseHingePidSetpoint = 0.3;
//    public static double lowerHingePidSetpoint = 0.3;

    public static double intakeMotorPidKp = 0.5;
    public static double intakeMotorPidKi = 0.0;
    public static double intakeMotorPidKd = 0.0;
    public static double intakeMotorAllowableClosedLoopError = 10;

    public static double hatchMotorUpDutyCycle = 0.2;
    public static double hatchMotorDownDutyCycle = 0.2;

	public static double ballOutputAutoTimeout = 2.0;
    
	public static double liftMotorPidKp = 5.0;
    public static double liftMotorLowerPidKp = 5.0;
    public static double liftMotorPidKi = 0.0;
    public static double liftMotorPidKd = 0.0;
    public static double liftMotorPidKf = 0.0;
    public static double liftMotorAllowableClosedLoopError = 10;
	public static double liftSecondsFromNeutral = 0.25;
	public static double liftMotorPeakVoltage = 0.5;
    public static double liftIntakePositionPidSetpoint = 50.0;
    public static double liftHatchPositionPidSetpoint = 70.0;
	public static double liftRocketPositionPidSetpoint = 100.0;
	public static double liftCommandTimeout = 5.0;
	

    public static double armMotorPidKp = 5.0;
    public static double armMotorLowerPidKp = 5.0;
    public static double armMotorPidKi = 0.0;
    public static double armMotorPidKd = 0.0;
    public static double armMotorRaisePidKf = 0.0;
    public static double armMotorLowerPidKf = 0.0;
    public static double armMotorAllowableClosedLoopError = 10;
    public static double armSecondsFromNeutral = 0.5;
    public static double intakePositionArmPidSetpoint = 50.0;
    public static double switchPositionArmPidSetpoint = 500.0;
    public static double armMotorLowerArmPidEntryPoint = 400.0;
    public static double scalePositionArmPidSetpoint = 1200.0;
    public static double armMotorPeakRaiseVoltage = 0.7;
    public static double armMotorPeakLowerVoltage = 0.5;
    public static double armToSwitchTimeout = 2.0;
    public static double armToScaleTimeout = 3.0;
    public static double armToIntakeTimeout = 5.0;
    public static double armReleaseSpringDutyCycle = -0.65;
    public static double armReleaseSpringTimeout = 0.25;
    
    public static double intakeBallDutyCycle = 0.3;
    public static double outputBallDutyCycle = 0.4;
 	
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
		SmartDashboard.putNumber("Timed Drive Timeout", timedDriveTimeout);
		SmartDashboard.putNumber("Degrees to turn", turnDegrees);
		SmartDashboard.putNumber("Autonomous drive inches", autoDriveStraightAutoInches);
				
		/* consider ramping function on the talons */
		SmartDashboard.putNumber("Straight drive start duty cycle", straightDriveStartDutyCycle);
		SmartDashboard.putNumber("Straight drive final duty cycle", straightDriveFinalDutyCycle);
		SmartDashboard.putNumber("Straight drive rate limit", straightDriveRateLimit);
		SmartDashboard.putNumber("Straight drive ramp down inches", straightDriveInchesForRampDownBegin);
		SmartDashboard.putNumber("Arm Up Max Drive DutyCycle",armUpMaxDriveDutyCycle);
		SmartDashboard.putNumber("Arm Down Max Drive DutyCycle",armDownMaxDriveDutyCycle);
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
    	SmartDashboard.putNumber("Lift PID Ramp", liftSecondsFromNeutral);
    	SmartDashboard.putNumber("Lift PID KP", liftMotorPidKp);
    	SmartDashboard.putNumber("Lift Lower PID KP", liftMotorLowerPidKp);
    	SmartDashboard.putNumber("Lift PID KI", liftMotorPidKi);
    	SmartDashboard.putNumber("Lift PID KD", liftMotorPidKd);
    	SmartDashboard.putNumber("Lift PID KF", liftMotorPidKf);
    	SmartDashboard.putNumber("Lift Peak Voltage", liftMotorPeakVoltage);
    	SmartDashboard.putNumber("Lift PID Allowable Error", liftMotorAllowableClosedLoopError);
    	SmartDashboard.putNumber("Lift Intake PID Setpoint", liftIntakePositionPidSetpoint);
    	SmartDashboard.putNumber("Lift Rocket PID Setpoint", liftRocketPositionPidSetpoint);
    	SmartDashboard.putNumber("Lift Hatch PID Setpoint", liftHatchPositionPidSetpoint);
		SmartDashboard.putNumber("Lift Command Timeout", liftCommandTimeout);

 		SmartDashboard.putNumber("Arm To Switch Timeout", Constants.armToSwitchTimeout);
		SmartDashboard.putNumber("Arm To Scale Timeout", Constants.armToScaleTimeout);
		SmartDashboard.putNumber("Arm To Intake Timeout", Constants.armToIntakeTimeout);
		SmartDashboard.putNumber("Arm Release Spring Duty Cycle", Constants.armReleaseSpringDutyCycle);
		SmartDashboard.putNumber("Arm Release Spring Timeout", Constants.armReleaseSpringTimeout);

    	
    	/* Intake motor */
    	SmartDashboard.putNumber("Intake Ball Duty Cycle", intakeBallDutyCycle);
    	SmartDashboard.putNumber("Output Ball Duty Cycle", outputBallDutyCycle);   
    	SmartDashboard.putNumber("Ball Output Auto Timeout", ballOutputAutoTimeout);
		
		/* Hatch motor */
    	SmartDashboard.putNumber("Hatch Up Duty Cycle", hatchMotorUpDutyCycle);
    	SmartDashboard.putNumber("Hatch Down Duty Cycle", hatchMotorDownDutyCycle);

		/* Hinge motor */
    	SmartDashboard.putNumber("Hinge PID KP", hingeMotorPidKp);
    	SmartDashboard.putNumber("Hinge Lower PID KP", hingeMotorLowerPidKp);
    	SmartDashboard.putNumber("Hinge PID KI", hingeMotorPidKi);
    	SmartDashboard.putNumber("Hinge PID KD", hingeMotorPidKd);
    	SmartDashboard.putNumber("Hinge PID Allowable Error", hingeMotorAllowableClosedLoopError);
    	SmartDashboard.putNumber("Raise Hinge PID Setpoint", raiseHingePidSetpoint);
    	SmartDashboard.putNumber("Lower Hinge PID Setpoint", lowerHingePidSetpoint);  
    	SmartDashboard.putNumber("Hinge To Scale PID Setpoint", hingeToScalePidSetpoint);  
    	SmartDashboard.putNumber("Hinge PID Ramp", hingeSecondsFromNeutral);
    	SmartDashboard.putNumber("Hinge Peak Voltage", hingeMotorPeakVoltage);
    	SmartDashboard.putNumber("Lower Hinge Timeout", lowerHingeTimeout);
    	SmartDashboard.putNumber("Raise Hinge Timeout", raiseHingeTimeout);

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
	}
}
