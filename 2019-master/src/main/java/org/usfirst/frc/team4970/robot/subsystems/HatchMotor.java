package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import utils.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HatchMotor extends Subsystem {

	public enum HatchState
	{
		HATCH_UP, HATCH_DOWN
	};

	public static HatchState _hatchState = HatchState.HATCH_UP;

	public static WPI_TalonSRX m_hatch = new WPI_TalonSRX(Constants.hatchMotorCanAddress);

	public HatchMotor() {
		m_hatch.configFactoryDefault();
		m_hatch.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.timeoutMs);

//		m_hatch.setSensorPhase(false);
				   
		m_hatch.setNeutralMode(NeutralMode.Brake);
		
		m_hatch.setSelectedSensorPosition(0, 0, Constants.timeoutMs);

    	m_hatch.configNominalOutputForward(0, Constants.timeoutMs);
		m_hatch.configNominalOutputReverse(0, Constants.timeoutMs);
		
    	m_hatch.configPeakOutputForward(1.0, Constants.timeoutMs);
		m_hatch.configPeakOutputReverse(-1.0, Constants.timeoutMs);

		m_hatch.config_kP(0, Constants.hatchMotorPidKp, Constants.timeoutMs);
		m_hatch.config_kI(0, Constants.hatchMotorPidKi, Constants.timeoutMs);
		m_hatch.config_kD(0, Constants.hatchMotorPidKd, Constants.timeoutMs);
		m_hatch.config_kF(0, Constants.hatchMotorPidKf, Constants.timeoutMs);

		m_hatch.configMotionCruiseVelocity((int)Constants.hatchMotorMotionCruiseVelocity, Constants.timeoutMs);
		m_hatch.configMotionAcceleration((int)Constants.hatchMotorMotionAcceleration, Constants.timeoutMs);

		m_hatch.configPeakOutputForward(Constants.hatchMotorPeakVoltageDown, Constants.timeoutMs);
		m_hatch.configPeakOutputReverse(Constants.hatchMotorPeakVoltageUp, Constants.timeoutMs);

		m_hatch.configAllowableClosedloopError(0, (int)Constants.hatchMotorAllowableClosedLoopError, Constants.timeoutMs);	   		   	
	}
	
    public void initDefaultCommand() {
//    	setDefaultCommand(new HatchUp());	
    }

	public void setHatchKp()
	{
		Constants.hatchMotorPidKp = SmartDashboard.getNumber("Hatch PID KP", Constants.hatchMotorPidKp);
		m_hatch.config_kP(0, Constants.hatchMotorPidKp, Constants.timeoutMs);
	}

	public void moveHatchPosition(double setpoint)
	{
		m_hatch.set(ControlMode.MotionMagic, setpoint);
	}

    public void holdUp() {
    	m_hatch.set(ControlMode.PercentOutput, -1.0 * SmartDashboard.getNumber("Hatch Up Duty Cycle", Constants.hatchMotorUpDutyCycle));
    }
    
    public void keepUp() {
    	m_hatch.set(ControlMode.PercentOutput, -1.0 * SmartDashboard.getNumber("Hatch Keep Up Duty Cycle", Constants.hatchMotorKeepUpDutyCycle));
    }
    
    public void holdDown() {
    	m_hatch.set(ControlMode.PercentOutput, SmartDashboard.getNumber("Hatch Down Duty Cycle", Constants.hatchMotorDownDutyCycle));
    }

    public void stop() {
    	m_hatch.set(ControlMode.PercentOutput, 0.0);
	}
	
	public double getEncoderCount() {
    	return m_hatch.getSelectedSensorPosition(0);
    }
    
    public int getClosedLoopError() {
    	return m_hatch.getClosedLoopError(0);
    }

    public double getMotorOutputVoltage() {
    	return m_hatch.getMotorOutputVoltage();
    }
    
    public void resetEncoder() {
    	m_hatch.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
    }
}

