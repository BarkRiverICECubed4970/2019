package org.usfirst.frc.team4970.robot.commands;

//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimberOffPlatformSequence extends CommandGroup {
    public ClimberOffPlatformSequence() {
        addSequential(new ClimbFrontToDriveOffPos());

        addParallel(new ClimbDriveReverse());
        addParallel(new ClimbRearToLevel());
        addSequential(new DriveStraightOffPlatform());

        /* one second pause */
 //       addSequential(new StallCommand());


//        addSequential(new ClimbRearMoveManualOffPlatform());

        /* one second pause */
//        addSequential(new StallCommand());
        addParallel(new ClimbDriveStop());

        addSequential(new StallCommandSmall());
        addParallel(new ClimbRearStop());
        addSequential(new StallCommandSmall());

        addParallel(new ClimbFrontStop());
        addSequential(new StallCommand());

        addParallel(new ClimbersToStartGroup());

    }
}