package commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import utils.Constants;
import subsystems.RearClimb;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ClimbRearToFinalPos extends Command {

	public ClimbRearToFinalPos() {

        requires(Robot._rearClimber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.rearClimbFinalPositionSetpoint = SmartDashboard.getNumber("Rear Climb Final Setpoint", Constants.rearClimbFinalPositionSetpoint);

        Constants.rearClimbCommandTimeout = SmartDashboard.getNumber("Rear Climb Command Timeout", Constants.rearClimbCommandTimeout);

    	setTimeout(Constants.rearClimbCommandTimeout);

       	RearClimb._legState = RearClimb.LegState.LEGS_MOVING;    		
        Robot._rearClimber.moveToPosition(Constants.rearClimbFinalPositionSetpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    protected boolean isFinished() {
    	if ((isTimedOut()) || 
    		((Math.abs(Robot._rearClimber.getEncoderCount() - Constants.rearClimbFinalPositionSetpoint))
    			<= Constants.rearClimbAllowableClosedLoopError))
    	{
    		RearClimb._legState = RearClimb.LegState.LEGS_FINAL_POSITION;
    		return true;
    	} else {
            return false;
        }    
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot._rearClimber.hold();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}