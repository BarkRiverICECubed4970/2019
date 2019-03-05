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
public class DriveAssistHatch extends Command {
	
	private double centerXValue;
	private double degrees;
	
	public DriveAssistHatch() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot._driveTrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

//		Robot.setExposure(1);

		Constants.driveAssistDutyCycle = SmartDashboard.getNumber("Drive Assist DutyCycle", Constants.driveAssistDutyCycle);
		Constants.driveAssistTimeout = SmartDashboard.getNumber("Drive Assist Timeout", Constants.driveAssistTimeout);
		Constants.driveAssistPixelsToDegrees = SmartDashboard.getNumber("Drive Assist Pixels to Degrees", Constants.driveAssistPixelsToDegrees);
		Constants.driveAssistImageCenter = SmartDashboard.getNumber("Drive Assist Image Center", Constants.driveAssistImageCenter);

		setTimeout(Robot.driveAssistTimeout);

		// redundant, since setupGyroPID() does this already
    	Robot.driveTrain.setGyroPidSetpoint(0.0);
    	Robot.driveTrain.controlLoop(DriveTrain.STOP_MOTOR);
		Robot.targetFound = false;
		
		Robot._driveTrain.resetEncoders();
				
		Robot._driveTrain.setupGyroPID(DriveTrain.DriveTrainControl.DRIVE_ASSIST);
		Robot._driveTrain.setDriveTrainBrakeMode(true);
		Robot._driveTrain.setGyroPidSetpoint(_gyroPidSetpoint);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
    	if (Constants.updateCenterXArray() == true)
    	{
    		centerXValue = (Robot.centerXArray[0] - Robot.centerXArray[1])/2.0 + Robot.centerXArray[1];
    		// change centerX to drive direction
    		centerXValue = centerXValue - Constants.driveAssistImageCenter;
    	
    		// convert centerXValue to degrees to turn
    		degrees = centerXValue * Constants.pixelsToDegrees;
    		Robot._driveTrain.resetGyro();
    		Robot._driveTrain.setGyroPidSetpoint(degrees);
    		
    		SmartDashboard.putNumber("centerXValue", centerXValue);
    		Robot.targetFound = true;
    	}

    	/* 
    	 * as long as the target was found at least once, drive toward
    	 * the computed angle
    	 */
    	if (Robot.targetFound == true)
    	{
    		Robot.driveTrain.controlLoop(DriveTrain.DRIVE_ASSIST);
    	}    	
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {

		if (isTimedOut())
		{
			return true;
		} else
		{
			return false;
		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot._driveTrain.controlLoop(DriveTrain.DriveTrainControl.STOP, 0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
