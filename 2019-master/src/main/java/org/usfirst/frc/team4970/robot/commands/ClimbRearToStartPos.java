package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;
import org.usfirst.frc.team4970.robot.subsystems.RearClimb;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ClimbRearToStartPos extends Command {

	public ClimbRearToStartPos() {

        requires(Robot._rearClimber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.rearClimbStartPositionSetpoint = SmartDashboard.getNumber("Rear Climb Start Setpoint", Constants.rearClimbStartPositionSetpoint);

        Constants.rearClimbCommandTimeout = SmartDashboard.getNumber("Rear Climb Command Timeout", Constants.rearClimbCommandTimeout);

    	setTimeout(Constants.rearClimbCommandTimeout);

       	RearClimb._legState = RearClimb.LegState.LEGS_MOVING;    		
        Robot._rearClimber.moveToPosition(Constants.rearClimbStartPositionSetpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	if ((isTimedOut()) || 
    		((Math.abs(Robot._rearClimber.getEncoderCount() - Constants.rearClimbStartPositionSetpoint))
    			<= Constants.rearClimbAllowableClosedLoopError))
    	{
    		RearClimb._legState = RearClimb.LegState.LEGS_START_POSITION;
    		return true;
    	} else {
            return false;
        }    
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot._rearClimber.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}