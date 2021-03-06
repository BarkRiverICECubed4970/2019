/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4970.robot;

import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4970.robot.subsystems.*;
import org.usfirst.frc.team4970.robot.commands.*;



import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import utils.Constants;
import utils.VisionUtils;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	/* 
	 * due to wiring and weight, the intake and gyro are using the same talonSRX,
	 * keep things simple and have robot own it, so any subsystems that need the gyro can see it. 
	 */
    public static WPI_TalonSRX m_intake = new WPI_TalonSRX(Constants.intakeMotorCanAddress);

	public static final DriveTrain _driveTrain = new DriveTrain();
	public static final IntakeMotor _intakeMotor = new IntakeMotor();
	public static final HatchMotor _hatchMotor = new HatchMotor();
	public static final LiftMotor _liftMotor = new LiftMotor();
	public static final FrontClimb _frontClimber = new FrontClimb();
	public static final RearClimb _rearClimber = new RearClimb();
	public static final RearClimbDriveSystem _rearClimbDrive = new RearClimbDriveSystem();
  
	public static OI m_oi;

//	public static PowerDistributionPanel pdp = new PowerDistributionPanel(); 
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	public static Constants _calibrationManager;
	public static VisionUtils _visionUtils;
	
    /**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		_visionUtils = new VisionUtils();
		m_oi = new OI();
		
		_calibrationManager = new Constants();
		
	    m_chooser.setDefaultOption("Do Nothing", new ClimbersToStartAutonGroup());
		m_chooser.addObject("Drive Off Low Platform", new ClimberOffPlatformSequence());
        // instantiate the command used for the autonomous period

		SmartDashboard.putData("Auto mode", m_chooser);	

		CameraServer.getInstance().startAutomaticCapture();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		_driveTrain.setDriveTrainBrakeMode(true);
		
		m_autonomousCommand = m_chooser.getSelected();

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		
		_driveTrain.setDriveTrainBrakeMode(false);
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
