package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ClimbDriveForward extends Command {

	public ClimbDriveForward() {

        requires(Robot._rearClimbDrive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.climbDriveDutyCycle = SmartDashboard.getNumber("Climber Drive Duty Cycle", Constants.climbDriveDutyCycle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot._rearClimbDrive.driveForward(Constants.climbDriveDutyCycle);
    }

    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot._rearClimbDrive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}