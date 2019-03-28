package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utils.Constants;
import frc.robot.subsystems.FrontClimb;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ClimbFrontToPlatform extends Command {

	public ClimbFrontToPlatform() {

        requires(Robot._frontClimber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.frontClimbPlatformPositionSetpoint = SmartDashboard.getNumber("Front Climb Platform Setpoint", Constants.frontClimbPlatformPositionSetpoint);

        Constants.frontClimbCommandTimeout = SmartDashboard.getNumber("Front Climb Command Timeout", Constants.frontClimbCommandTimeout);

    	setTimeout(Constants.frontClimbCommandTimeout);

       	FrontClimb._legState = FrontClimb.LegState.LEGS_MOVING;    		
        Robot._frontClimber.moveToPosition(Constants.frontClimbPlatformPositionSetpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	if ((isTimedOut()) || 
    		((Math.abs(Robot._frontClimber.getEncoderCount() - Constants.frontClimbPlatformPositionSetpoint))
    			<= Constants.frontClimbAllowableClosedLoopError))
    	{
    		FrontClimb._legState = FrontClimb.LegState.LEGS_PLATFORM_POSITION;
    		return true;
    	} else {
            return false;
        }    
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot._frontClimber.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}