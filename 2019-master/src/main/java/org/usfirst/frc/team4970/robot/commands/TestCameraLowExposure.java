package org.usfirst.frc.team4970.robot.commands;

import org.usfirst.frc.team4970.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestCameraLowExposure extends Command {

	public TestCameraLowExposure() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot._visionUtils.setVisionAssistExposure(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
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