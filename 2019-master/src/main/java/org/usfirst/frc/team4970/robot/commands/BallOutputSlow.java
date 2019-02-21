package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;

/**
 *
 */
public class BallOutputSlow extends Command {

	public BallOutputSlow() {

        requires(Robot._intakeMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.outputBallDutyCycle = SmartDashboard.getNumber("Output Ball Duty Cycle", Constants.outputBallDutyCycle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot._intakeMotor.outputBallSlow(Constants.outputBallDutyCycle);
    }

    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot._intakeMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}