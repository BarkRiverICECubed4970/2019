package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;
import org.usfirst.frc.team4970.robot.subsystems.RearClimb;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ClimbRearToPlatform extends Command {

	public ClimbRearToPlatform() {

        requires(Robot._rearClimber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.rearClimbPlatformPositionSetpoint = SmartDashboard.getNumber("Rear Climb Platform Setpoint", Constants.rearClimbPlatformPositionSetpoint);

        Constants.rearClimbCommandTimeout = SmartDashboard.getNumber("Rear Climb Command Timeout", Constants.rearClimbCommandTimeout);

    	setTimeout(Constants.rearClimbCommandTimeout);

       	RearClimb._legState = RearClimb.LegState.LEGS_MOVING;    		
        Robot._rearClimber.moveToPosition(Constants.rearClimbPlatformPositionSetpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	if ((isTimedOut()) || 
    		((Math.abs(Robot._rearClimber.getEncoderCount() - Constants.rearClimbPlatformPositionSetpoint))
    			<= Constants.rearClimbAllowableClosedLoopError))
    	{
    		RearClimb._legState = RearClimb.LegState.LEGS_PLATFORM_POSITION;
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