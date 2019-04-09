/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4970.robot;


import org.usfirst.frc.team4970.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public Joystick joystick = new Joystick(0);
	public Joystick joystick2 = new Joystick(1);
    public JoystickButton joystickButton1;
    public JoystickButton joystickButton2;
    
    public OI() {

    	/* test button... take out for competition */
//        joystickButton1 = new JoystickButton(joystick, 4);
 //       joystickButton1.whenPressed(new ArmToScaleGroup());        
    	
//    	joystickButton1 = new JoystickButton(joystick, 9);
 //       joystickButton1.whenPressed(new LowerHinge());
        
 //       joystickButton1 = new JoystickButton(joystick, 10);
//        joystickButton1.whenPressed(new RaiseHinge());
        
        joystickButton1 = new JoystickButton(joystick, 2);
        joystickButton1.whenPressed(new LiftToBallIntakePosition());
        
        joystickButton1 = new JoystickButton(joystick, 3);
        joystickButton1.whenPressed(new LiftToHatchIntakePosition());
        
        joystickButton1 = new JoystickButton(joystick, 1);
        joystickButton1.whenPressed(new LiftToHatchLowerPosition());
        
        joystickButton1 = new JoystickButton(joystick, 4);
        joystickButton1.whenPressed(new LiftToRocketPosition());

		joystickButton1 = new JoystickButton(joystick, 10);
        joystickButton1.whenPressed(new LiftToHatchUpperPosition());
        
        joystickButton1 = new JoystickButton(joystick, 14);
        joystickButton1.whenPressed(new HatchToggle());
        
        joystickButton1 = new JoystickButton(joystick, 8);
        joystickButton1.whileHeld(new BallIntake());
        
        joystickButton1 = new JoystickButton(joystick, 7);
        joystickButton1.whileHeld(new BallOutput());

        joystickButton1 = new JoystickButton(joystick, 5);
        joystickButton1.whileHeld(new TestLiftDown());

        joystickButton1 = new JoystickButton(joystick, 6);
        joystickButton1.whileHeld(new TestLiftUp());

        joystickButton1 = new JoystickButton(joystick, 12);
	    joystickButton1.whileHeld(new DriveAssistHatch());
		
		joystickButton2 = new JoystickButton(joystick2, 8);
		joystickButton2.whileHeld(new ClimbDriveForward());
		 
		joystickButton2 = new JoystickButton(joystick2, 7);
		joystickButton2.whileHeld(new ClimbDriveReverse());
  
		joystickButton2 = new JoystickButton(joystick2, 1);
		joystickButton2.whileHeld(new ClimbFrontMoveManualOpposite());
  
		joystickButton2 = new JoystickButton(joystick2, 2);
		joystickButton2.whileHeld(new ClimbRearMoveManual());
  
		joystickButton2 = new JoystickButton(joystick2, 3);
		joystickButton2.whileHeld(new ClimbFrontMoveManual());
  
		joystickButton2 = new JoystickButton(joystick2, 4);
		joystickButton2.whileHeld(new ClimbRearMoveManualOpposite());
	
  //      joystickButton2 = new JoystickButton(joystick2, 11);
  //      joystickButton2.whileHeld(new ClimberSmallPlatformSequence());
  
		joystickButton2 = new JoystickButton(joystick2, 12);
		joystickButton2.whenPressed(new ClimberSequenceLevelOne());

		joystickButton2 = new JoystickButton(joystick2, 13);
		joystickButton2.whenPressed(new ClimberOffPlatformSequence());

		joystickButton2 = new JoystickButton(joystick2, 14);
		joystickButton2.whenPressed(new ClimberSequence());
        
    	// SmartDashboard Buttons
    	SmartDashboard.putData("Drive Straight", new DriveStraight(0.0, 0.0, true));
    	SmartDashboard.putData("Drive Straight Reverse", new DriveStraightReverse(0.0, 0.0, true));
		SmartDashboard.putData("Turn Degrees", new TurnDegrees(0.0, true));
		
		SmartDashboard.putData("Stop Lift", new TestLiftStop());
		SmartDashboard.putData("Stop Hatch", new TestHatchStop());

		SmartDashboard.putData("LED Off", new TestLedOff());
		SmartDashboard.putData("LED On", new TestLedOn());

		SmartDashboard.putData("PiCam Low Exposure", new TestCameraLowExposure());
		SmartDashboard.putData("PiCam High Exposure", new TestCameraHighExposure());

		SmartDashboard.putData("Hatch Up", new HatchUp());
		SmartDashboard.putData("Hatch Down", new HatchDown());
		SmartDashboard.putData("Hatch Toggle", new HatchToggle());
		SmartDashboard.putData("Lift to Ball Intake Position", new LiftToBallIntakePosition());
		SmartDashboard.putData("Lift to Hatch Intake Position", new LiftToHatchIntakePosition());
		SmartDashboard.putData("Lift to Hatch Lower Position", new LiftToHatchLowerPosition());
		SmartDashboard.putData("Lift to Hatch Upper Position", new LiftToHatchUpperPosition());
		SmartDashboard.putData("Lift to Rocket Position", new LiftToRocketPosition());
		SmartDashboard.putData("Test Lift Up", new TestLiftUp());
		SmartDashboard.putData("Test Lift Down", new TestLiftDown());
		SmartDashboard.putData("Reset Lift Encoder", new TestLiftResetEncoder());
		SmartDashboard.putData("Drive Assist Hatch", new DriveAssistHatch());
		SmartDashboard.putData("Drive Assist Ball", new DriveAssistBall());

		SmartDashboard.putData("Front Climber to Start Position", new ClimbFrontToStartPos());
		SmartDashboard.putData("Front Climber to Final Position", new ClimbFrontToFinalPos());
		SmartDashboard.putData("Front Climber to Platform Position", new ClimbFrontToPlatform());
		SmartDashboard.putData("Front Climber Move Manual", new ClimbFrontMoveManual());
		SmartDashboard.putData("Front Climber To Drive Off Position", new ClimbFrontToDriveOffPos());

		SmartDashboard.putData("Back Climber to Start Position", new ClimbRearToStartPos());
		SmartDashboard.putData("Back Climber to Final Position", new ClimbRearToFinalPos());
		SmartDashboard.putData("Back Climber to Platform Position", new ClimbRearToPlatform());
		SmartDashboard.putData("Back Climber Move Manual", new ClimbRearMoveManual());

		SmartDashboard.putData("Both Climbers To Platform Position", new ClimbersToPlatformGroup());
		SmartDashboard.putData("Both Climbers To Start Position", new ClimbersToStartGroup());
		SmartDashboard.putData("Both Climbers To Final Position", new ClimbersToFinalGroup());

		SmartDashboard.putData("Climb Sequence", new ClimberSequence());
		SmartDashboard.putData("Climb Off Platform", new ClimberOffPlatformSequence());

    }
}
