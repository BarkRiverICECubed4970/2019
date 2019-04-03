package org.usfirst.frc.team4970.robot.commands;

//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbersToFinalGroup extends CommandGroup {
    public ClimbersToFinalGroup() {
        addParallel(new ClimbDriveReverse());
        addParallel(new ClimbFrontToFinalPos());
        addSequential(new ClimbRearToFinalPos());
    }
}