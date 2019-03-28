package org.usfirst.frc.team4970.robot.commands;

//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimberSequence extends CommandGroup {
    public ClimberSequence() {
        addParallel(new ClimbFrontToPlatform());
        addSequential(new ClimbRearToPlatform());
        /* assuming the front to platform finishes first, this will be fine */
        addParallel(new ClimbDriveForward());
        addParallel(new ClimbFrontToFinalPos());
        addSequential(new ClimbRearToFinalPos());

        // add drive to timeout here??
        // add front climber to start position?
        // add rear climber to platform position?
    }
}