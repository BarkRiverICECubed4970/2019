package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.utils.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class RearClimb extends Subsystem {

	public enum LegState
	{
		LEGS_START_POSITION, LEGS_MOVING, LEGS_PLATFORM_POSITION, LEGS_FINAL_POSITION
	};
	
	public static LegState _legState = LegState.LEGS_START_POSITION;
	
	WPI_TalonSRX m_rearClimb = new WPI_TalonSRX(Constants.rearClimbMotorCanAddress);

	public RearClimb() {
		m_rearClimb.configFactoryDefault();
		m_rearClimb.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.timeoutMs);

		m_rearClimb.configNominalOutputForward(0, Constants.timeoutMs);
		m_rearClimb.configNominalOutputReverse(0, Constants.timeoutMs);
		
		m_rearClimb.setSensorPhase(false);
				   
		m_rearClimb.setNeutralMode(NeutralMode.Brake);
		
		/* 
		 * for now, forget about the absolute position, just set the relative position
		 * to 0 upon startup. This means the robot must be in the same position each
		 * time it's powered up!!
		 */
		m_rearClimb.setSelectedSensorPosition(0, 0, Constants.timeoutMs);

		m_rearClimb.config_kP(0, Constants.rearClimbMotorPidKp, Constants.timeoutMs);
		m_rearClimb.config_kI(0, Constants.rearClimbMotorPidKi, Constants.timeoutMs);
		m_rearClimb.config_kD(0, Constants.rearClimbMotorPidKd, Constants.timeoutMs);
		m_rearClimb.config_kF(0, Constants.rearClimbMotorPidKf, Constants.timeoutMs);

		m_rearClimb.configMotionCruiseVelocity((int)Constants.rearClimbMotorMotionCruiseVelocity, Constants.timeoutMs);
		m_rearClimb.configMotionAcceleration((int)Constants.rearClimbMotorMotionAcceleration, Constants.timeoutMs);

		m_rearClimb.configPeakOutputForward(Constants.rearClimbMotorPeakVoltage, Constants.timeoutMs);
		m_rearClimb.configPeakOutputReverse(-1.0 * Constants.rearClimbMotorPeakVoltage, Constants.timeoutMs);

		m_rearClimb.configAllowableClosedloopError(0, (int)Constants.rearClimbMotorAllowableClosedLoopError, Constants.timeoutMs);	   		   	


	}

	public void moveToPosition(double setpoint)
	{
		m_rearClimb.set(ControlMode.MotionMagic, setpoint);
	}

	public void moveManual(double dutyCycle)
	{
		m_rearClimb.set(ControlMode.PercentOutput, dutyCycle);
	}

    public void initDefaultCommand() {
    }
	  
	public void hold() {
		moveToPosition(getEncoderCount());
	}

    public void stop() {
    	m_rearClimb.set(ControlMode.PercentOutput, 0.0);
    }  

	public double getEncoderCount() {
    	return m_rearClimb.getSelectedSensorPosition(0);
    }
    
    public int getClosedLoopError() {
    	return m_rearClimb.getClosedLoopError(0);
    }

    public double getMotorOutputVoltage() {
    	return m_rearClimb.getMotorOutputVoltage();
    }
    
    public void resetEncoder() {
    	m_rearClimb.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
	}
}

