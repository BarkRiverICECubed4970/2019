package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utils.Constants;
import frc.robot.subsystems.RearClimb;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ClimbRearMoveManual extends Command {

	public ClimbRearMoveManual() {

        requires(Robot._rearClimber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Constants.rearClimbTestDutyCycle = SmartDashboard.getNumber("Rear Climb Test Duty Cycle", Constants.rearClimbTestDutyCycle);
       	RearClimb._legState = RearClimb.LegState.LEGS_MOVING;    		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot._rearClimber.moveManual(Constants.rearClimbTestDutyCycle);
    }

    protected boolean isFinished() {
        return false;
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