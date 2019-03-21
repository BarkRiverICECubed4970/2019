package commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbersToStartGroup extends CommandGroup {
    public ClimbersToStartGroup() {
        addParallel(new ClimbFrontToStartPos());
        addSequential(new ClimbRearToStartPos());
    }
}