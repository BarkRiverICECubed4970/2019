package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4970.robot.Robot;
import utils.Constants;
import org.usfirst.frc.team4970.robot.subsystems.FrontClimb;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ClimbFrontMoveManualOpposite extends Command {

	public ClimbFrontMoveManualOpposite() {

        requires(Robot._frontClimber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.frontClimbTestDutyCycle = SmartDashboard.getNumber("Front Climb Test Duty Cycle", Constants.frontClimbTestDutyCycle);
       	FrontClimb._legState = FrontClimb.LegState.LEGS_MOVING;    		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot._frontClimber.moveManual(-1.0*Constants.frontClimbTestDutyCycle);
    }

    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	Robot._frontClimber.hold();
    	Robot._frontClimber.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}