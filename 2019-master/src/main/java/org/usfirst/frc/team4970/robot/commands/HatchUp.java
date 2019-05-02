package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.subsystems.HatchMotor;

import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;

/**
 *
 */
public class HatchUp extends Command {

	public HatchUp() {

        requires(Robot._hatchMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//        Constants.hatchCommandTimeout = SmartDashboard.getNumber("Hatch Command Timeout", Constants.hatchCommandTimeout);

//    	setTimeout(Constants.hatchCommandTimeout);

        Robot._hatchMotor.setHatchKp();

        Robot._hatchMotor.moveHatchPosition(Constants.hatchMotorUpPositionSetpoint);
        HatchMotor._hatchState = HatchMotor.HatchState.HATCH_UP;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }


    protected boolean isFinished() {
   		return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        /* spring holds this up */
//    	Robot._hatchMotor.keepUp();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}