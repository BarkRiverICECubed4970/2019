package org.usfirst.frc.team4970.robot.commands;

//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbersToStartAutonGroup extends CommandGroup {
    public ClimbersToStartAutonGroup() {
        addParallel(new HatchUp());
        addParallel(new ClimbFrontToStartPos());
        addSequential(new ClimbRearToStartPos());
    }
}
