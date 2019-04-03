package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;
import org.usfirst.frc.team4970.robot.subsystems.RearClimb;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ClimbRearMoveManualOffPlatform extends Command {

	public ClimbRearMoveManualOffPlatform() {

        requires(Robot._rearClimber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.rearClimbDriveOffPlatformDutyCycle = SmartDashboard.getNumber("Rear Climb Drive Off Duty Cycle", Constants.rearClimbDriveOffPlatformDutyCycle);
       	RearClimb._legState = RearClimb.LegState.LEGS_MOVING;    		

        setTimeout(1.0);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot._rearClimber.moveManual(Constants.rearClimbDriveOffPlatformDutyCycle);
    }

    protected boolean isFinished() {
        return (isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot._rearClimber.hold();
    	Robot._rearClimber.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}