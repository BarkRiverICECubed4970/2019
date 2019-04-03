package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import utils.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class FrontClimb extends Subsystem {

	public enum LegState
	{
		LEGS_START_POSITION, LEGS_MOVING, LEGS_PLATFORM_POSITION, LEGS_FINAL_POSITION
	};
	
	public static LegState _legState = LegState.LEGS_START_POSITION;
	
	WPI_TalonSRX m_frontClimb = new WPI_TalonSRX(Constants.frontClimbMotorCanAddress);

	public FrontClimb() {
		m_frontClimb.configFactoryDefault();
		m_frontClimb.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.timeoutMs);

		m_frontClimb.configNominalOutputForward(0, Constants.timeoutMs);
		m_frontClimb.configNominalOutputReverse(0, Constants.timeoutMs);		
				   
		m_frontClimb.setInverted(false);
		m_frontClimb.setSensorPhase(true);

		m_frontClimb.setNeutralMode(NeutralMode.Brake);
		
		/* 
		 * for now, forget about the absolute position, just set the relative position
		 * to 0 upon startup. This means the robot must be in the same position each
		 * time it's powered up!!
		 */
		m_frontClimb.setSelectedSensorPosition(0, 0, Constants.timeoutMs);

		m_frontClimb.config_kP(0, Constants.frontClimbMotorPidKp, Constants.timeoutMs);
		m_frontClimb.config_kI(0, Constants.frontClimbMotorPidKi, Constants.timeoutMs);
		m_frontClimb.config_kD(0, Constants.frontClimbMotorPidKd, Constants.timeoutMs);
		m_frontClimb.config_kF(0, Constants.frontClimbMotorPidKf, Constants.timeoutMs);

		m_frontClimb.configMotionCruiseVelocity((int)Constants.frontClimbMotorMotionCruiseVelocity, Constants.timeoutMs);
		m_frontClimb.configMotionAcceleration((int)Constants.frontClimbMotorMotionAcceleration, Constants.timeoutMs);

		m_frontClimb.configPeakOutputForward(Constants.frontClimbTestDutyCycle, Constants.timeoutMs);
		m_frontClimb.configPeakOutputReverse(-1.0 * Constants.frontClimbTestDutyCycleReverse, Constants.timeoutMs);

		m_frontClimb.configAllowableClosedloopError(0, (int)Constants.frontClimbMotorAllowableClosedLoopError, Constants.timeoutMs);	   		   	


	}

	public void moveToPosition(double setpoint)
	{
		m_frontClimb.set(ControlMode.MotionMagic, setpoint);
	}

	public void moveManual(double dutyCycle)
	{
		m_frontClimb.set(ControlMode.PercentOutput, dutyCycle);
	}

    public void initDefaultCommand() {
    }

	public void hold() {
		moveToPosition(getEncoderCount());
	}

    public void stop() {
    	m_frontClimb.set(ControlMode.PercentOutput, 0.0);
    }  

	public double getEncoderCount() {
    	return m_frontClimb.getSelectedSensorPosition(0);
    }
    
    public int getClosedLoopError() {
    	return m_frontClimb.getClosedLoopError(0);
    }

    public double getMotorOutputVoltage() {
    	return m_frontClimb.getMotorOutputVoltage();
    }
    
    public void resetEncoder() {
    	m_frontClimb.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
    }
}

