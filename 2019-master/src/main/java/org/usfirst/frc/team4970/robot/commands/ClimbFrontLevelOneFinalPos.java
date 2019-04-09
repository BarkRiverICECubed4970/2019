package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;
import org.usfirst.frc.team4970.robot.subsystems.FrontClimb;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ClimbFrontLevelOneFinalPos extends Command {

	public ClimbFrontLevelOneFinalPos() {

        requires(Robot._frontClimber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.frontClimbFinalPositionSetpoint = SmartDashboard.getNumber("Front Climb Final Setpoint", Constants.frontClimbFinalPositionSetpoint);

        Constants.frontClimbCommandTimeout = SmartDashboard.getNumber("Front Climb Command Timeout", Constants.frontClimbCommandTimeout);

    	setTimeout(Constants.frontClimbCommandTimeout);

       	FrontClimb._legState = FrontClimb.LegState.LEGS_MOVING;    		
        Robot._frontClimber.moveToPosition(-3700);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	if ((isTimedOut()) || 
    		((Math.abs(Robot._frontClimber.getEncoderCount() - Constants.frontClimbFinalPositionSetpoint))
    			<= Constants.frontClimbAllowableClosedLoopError))
    	{
    		FrontClimb._legState = FrontClimb.LegState.LEGS_FINAL_POSITION;
    		return true;
    	} else {
            return false;
        }    
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot._frontClimber.hold();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}