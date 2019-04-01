package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;

/**
 *
 */
public class StallCommand extends Command {

	public StallCommand() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Constants.stallCommandTimeout = SmartDashboard.getNumber("Stall Command Timeout", Constants.stallCommandTimeout);

    	setTimeout(Constants.stallCommandTimeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }


    protected boolean isFinished() {
    	if (isTimedOut())
    	{
    		return true;
    	} else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}