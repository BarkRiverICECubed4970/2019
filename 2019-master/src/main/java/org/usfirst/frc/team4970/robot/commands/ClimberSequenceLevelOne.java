package org.usfirst.frc.team4970.robot.commands;

//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimberSequenceLevelOne extends CommandGroup {
    public ClimberSequenceLevelOne() {
        addSequential(new ClimbersToLevelOnePlatformGroup());

        /* one second pause */
//        addSequential(new StallCommand());

        /* assuming the front to platform finishes first, this will be fine */
        addParallel(new ClimbDriveReverse());
        addParallel(new ClimbFrontLevelOneFinalPos());
        addParallel(new ClimbRearToSmallFinalPos());

        /* one second pause */
        addSequential(new StallCommand());

        addSequential(new DriveStraightOnPlatformLevelOne());

        addParallel(new ClimbRearStop());
        addParallel(new ClimbFrontStop());
        
        addSequential(new ClimbDriveStop());
        
        addSequential(new ClimbersToStartGroup());

        // add front climber to start position?
        // add rear climber to platform position?
    }
}