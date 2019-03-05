package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.subsystems.LiftMotor;
import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;

/**
 *
 */
public class LiftToHatchLowerPosition extends Command {

	public LiftToHatchLowerPosition() {

        requires(Robot._liftMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.liftHatchPlaceLowerPositionSetpoint = SmartDashboard.getNumber("Lift Hatch Lower Setpoint", Constants.liftHatchPlaceLowerPositionSetpoint);

        Constants.liftCommandTimeout = SmartDashboard.getNumber("Lift Command Timeout", Constants.liftCommandTimeout);

    	setTimeout(Constants.liftCommandTimeout);

       	LiftMotor._liftState = LiftMotor.LiftState.LIFT_MOVING;    		
       	Robot._liftMotor.moveLiftPosition(Constants.liftHatchPlaceLowerPositionSetpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	if ((isTimedOut()) || 
    		((Math.abs(Robot._liftMotor.getEncoderCount() - Constants.liftHatchPlaceLowerPositionSetpoint))
    			<= Constants.liftMotorAllowableClosedLoopError))
    	{
    		LiftMotor._liftState = LiftMotor.LiftState.LIFT_HATCH_HEIGHT;
    		return true;
    	} else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot._liftMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}