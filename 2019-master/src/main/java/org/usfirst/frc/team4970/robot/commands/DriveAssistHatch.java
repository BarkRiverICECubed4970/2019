/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4970.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team4970.robot.Robot;

public class DriveAssistHatch extends CommandGroup {
  
  public DriveAssistHatch() {

    addSequential(new SetStateHatch());    
    addSequential(new DriveAssist());
  }
}
