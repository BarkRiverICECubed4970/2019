package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.subsystems.HatchMotor;

import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;

/**
 *
 */
public class HatchDown extends Command {

	public HatchDown() {

        requires(Robot._hatchMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
 //       Constants.hatchCommandTimeout = SmartDashboard.getNumber("Hatch Command Timeout", Constants.hatchCommandTimeout);

//    	setTimeout(Constants.hatchCommandTimeout);

        Robot._hatchMotor.setHatchKp();

        Constants.hatchMotorDownPositionSetpoint = SmartDashboard.getNumber("Hatch Down Setpoint", Constants.hatchMotorDownPositionSetpoint);
        Robot._hatchMotor.moveHatchPosition(Constants.hatchMotorDownPositionSetpoint);
        HatchMotor._hatchState = HatchMotor.HatchState.HATCH_DOWN;
}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }


    protected boolean isFinished() {
   		return true;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot._hatchMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}