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
public class DriveStraightOffPlatform extends Command {
	
	public DriveStraightOffPlatform() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot._driveTrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

//		Constants.platformDriveDutyCycle = SmartDashboard.getNumber("Platform drive duty cycle", Constants.platformDriveDutyCycle);	

		Robot._driveTrain.setupGyroPID(DriveTrain.DriveTrainControl.DRIVE_STRAIGHT);
		Robot._driveTrain.setDriveTrainBrakeMode(true);
		Robot._driveTrain.setGyroPidSetpoint(0.0);

		setTimeout(1.75);

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot._driveTrain.controlLoop(DriveTrain.DriveTrainControl.DRIVE_STRAIGHT_PLATFORM, 0.1);
    	// continue to set this, since this function will ramp the setpoint
    	Robot._driveTrain.setGyroPidSetpoint(0.0);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
    	if (isTimedOut())
    	{
    		return true;
    	} else {
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
