package frc.robot.commands;

//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbersToPlatformGroup extends CommandGroup {
    public ClimbersToPlatformGroup() {
        addParallel(new ClimbFrontToPlatform());
        addSequential(new ClimbRearToPlatform());
    }
}