package org.usfirst.frc.team4970.robot.commands;

//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbersToLevelOnePlatformGroup extends CommandGroup {
    public ClimbersToLevelOnePlatformGroup() {
        addParallel(new ClimbFrontToSmallPlatform());
        addSequential(new ClimbRearToPlatform());
    }
}