package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;

/**
 *
 */
public class TestHatchStop extends Command {

	public TestHatchStop() {

        requires(Robot._hatchMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.hatchCommandTimeout = SmartDashboard.getNumber("Hatch Command Timeout", Constants.hatchCommandTimeout);

    	setTimeout(Constants.hatchCommandTimeout);

        Robot._hatchMotor.stop();
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
        /* spring holds this up */
    	Robot._hatchMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}