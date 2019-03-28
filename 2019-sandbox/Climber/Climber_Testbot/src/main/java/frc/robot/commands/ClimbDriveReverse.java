package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utils.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ClimbDriveReverse extends Command {

	public ClimbDriveReverse() {

        requires(Robot._rearClimbDrive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.climbDriveDutyCycle = SmartDashboard.getNumber("Climber Drive Duty Cycle", Constants.climbDriveDutyCycle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot._rearClimbDrive.driveReverse(Constants.climbDriveDutyCycle);
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