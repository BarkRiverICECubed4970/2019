package org.usfirst.frc.team4970.robot.commands;

//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimberSequence extends CommandGroup {
    public ClimberSequence() {
        addSequential(new ClimbersToPlatformGroup());

        /* one second pause */
//        addSequential(new StallCommand());

        /* assuming the front to platform finishes first, this will be fine */
        addParallel(new ClimbDriveReverse());
        addParallel(new ClimbFrontToFinalPos());
        addParallel(new ClimbRearToFinalPos());

        /* one second pause */
        addSequential(new StallCommand());

        addSequential(new DriveStraightOnPlatform());

        addParallel(new ClimbRearStop());
        addParallel(new ClimbFrontStop());
        addSequential(new ClimbDriveStop());

        // add front climber to start position?
        // add rear climber to platform position?
    }
}