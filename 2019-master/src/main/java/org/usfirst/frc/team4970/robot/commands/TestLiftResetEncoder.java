package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.subsystems.LiftMotor;
import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;

/**
 *
 */
public class TestLiftResetEncoder extends Command {

	public TestLiftResetEncoder() {

        requires(Robot._liftMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
       	Robot._liftMotor.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot._liftMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}