package org.usfirst.frc.team4970.robot.commands;

//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimberOffPlatformSequence extends CommandGroup {
    public ClimberOffPlatformSequence() {
        addSequential(new ClimbFrontToFinalPos());
        addSequential(new ClimbFrontStop());

        addParallel(new ClimbDriveForward());
        addSequential(new DriveStraightOnPlatform());

        /* one second pause */
        addSequential(new StallCommand());

        addSequential(new ClimbRearToPlatform());
        addParallel(new ClimbRearStop());

        /* one second pause */
        addSequential(new StallCommand());
        addSequential(new StallCommand());

        addParallel(new ClimbersToStartGroup());

    }
}