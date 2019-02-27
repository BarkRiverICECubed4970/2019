package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.subsystems.LiftMotor;
import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;

/**
 *
 */
public class LiftToBallIntakePosition extends Command {

	public LiftToBallIntakePosition() {

        requires(Robot._liftMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.liftBallIntakePositionSetpoint = SmartDashboard.getNumber("Lift Ball Intake Setpoint", Constants.liftBallIntakePositionSetpoint);

        Constants.liftCommandTimeout = SmartDashboard.getNumber("Lift Command Timeout", Constants.liftCommandTimeout);

    	setTimeout(Constants.liftCommandTimeout);

       	LiftMotor._liftState = LiftMotor.LiftState.LIFT_MOVING;    		
       	Robot._liftMotor.moveLiftPosition(Constants.liftBallIntakePositionSetpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	if ((isTimedOut()) || 
    		((Math.abs(Robot._liftMotor.getEncoderCount() - Constants.liftBallIntakePositionSetpoint))
    			<= (int)Constants.liftMotorAllowableClosedLoopError))
    	{
    		LiftMotor._liftState = LiftMotor.LiftState.LIFT_INTAKE_HEIGHT;
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