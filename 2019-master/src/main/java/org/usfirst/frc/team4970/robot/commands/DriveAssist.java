/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import org.usfirst.frc.team4970.robot.subsystems.DriveTrain;

import utils.Constants;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveAssist extends Command {
	
	private double centerXValue;
	private double centerXOffset;
	private double degrees;
	private boolean targetFound;
	private double targetHeight;
	
	public DriveAssist() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot._driveTrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

		Robot._visionUtils.turnOnLed();
		Robot._visionUtils.setVisionAssistExposure(true);

		Constants.driveAssistDutyCycle = SmartDashboard.getNumber("Drive Assist DutyCycle", Constants.driveAssistDutyCycle);
		Constants.driveAssistTimeout = SmartDashboard.getNumber("Drive Assist Timeout", Constants.driveAssistTimeout);
		Constants.driveAssistPixelsToDegrees = SmartDashboard.getNumber("Drive Assist Pixels to Degrees", Constants.driveAssistPixelsToDegrees);

		centerXOffset = Robot._visionUtils.getOffset();

//		setTimeout(Constants.driveAssistTimeout);

		Robot._driveTrain.resetGyro();
		// redundant, since setupGyroPID() does this already
    	Robot._driveTrain.setGyroPidSetpoint(0.0);
		Robot._driveTrain.controlLoop(DriveTrain.DriveTrainControl.STOP, 0.0);

		Robot._driveTrain.resetEncoders();
				
		Robot._driveTrain.setupGyroPID(DriveTrain.DriveTrainControl.DRIVE_ASSIST);
		Robot._driveTrain.setDriveTrainBrakeMode(true);

		targetFound = false;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

//		if (Robot._visionUtils.centerXvalid())
    	{
    		centerXValue = Robot._visionUtils.getCenterX() + centerXOffset;
			targetHeight = Robot._visionUtils.getTargetHeight();
    		// convert centerXValue to degrees to turn
    		degrees = centerXValue * Constants.driveAssistPixelsToDegrees;
    		Robot._driveTrain.resetGyro();
    		Robot._driveTrain.setGyroPidSetpoint(degrees);
    		
    		targetFound = true;
    	}

    	/* 
    	 * as long as the target was found at least once, drive toward
    	 * the computed angle
    	 */
  //  	if (targetFound == true)
    	{
			Robot._driveTrain.controlLoop(DriveTrain.DriveTrainControl.DRIVE_ASSIST, Constants.driveAssistDutyCycle);
//			System.out.println("turn " + degrees + " degrees");
		} 
//		else {
//			System.out.println("no target found yet");
//		}  	
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {

//		if (isTimedOut())
//		{
//			return true;
//		} else
//		{
			return false;
//		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot._driveTrain.controlLoop(DriveTrain.DriveTrainControl.STOP, 0.0);
		Robot._visionUtils.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
