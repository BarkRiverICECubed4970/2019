package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;
import org.usfirst.frc.team4970.robot.subsystems.FrontClimb;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ClimbFrontToDriveOffPos extends Command {

	public ClimbFrontToDriveOffPos() {

        requires(Robot._frontClimber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.frontClimbDriveOffSetpoint = SmartDashboard.getNumber("Front Climb Drive Off Setpoint", Constants.frontClimbDriveOffSetpoint);

        Constants.frontClimbCommandTimeout = SmartDashboard.getNumber("Front Climb Command Timeout", Constants.frontClimbCommandTimeout);

    	setTimeout(Constants.frontClimbCommandTimeout);

       	FrontClimb._legState = FrontClimb.LegState.LEGS_MOVING;    		
        Robot._frontClimber.moveToPosition(Constants.frontClimbDriveOffSetpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	if ((isTimedOut()) || 
    		((Math.abs(Robot._frontClimber.getEncoderCount() - Constants.frontClimbDriveOffSetpoint))
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
//    	Robot._frontClimber.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}