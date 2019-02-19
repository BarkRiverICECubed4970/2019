package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import utils.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class LiftMotor extends Subsystem {

	public enum LiftState
	{
		LIFT_START_HEIGHT, LIFT_INTAKE_HEIGHT, LIFT_HATCH_HEIGHT, LIFT_ROCKET_HEIGHT, LIFT_MOVING
	};
	
	public static LiftState _liftState = LiftState.LIFT_START_HEIGHT;
	
	WPI_TalonSRX m_lift = new WPI_TalonSRX(Constants.liftMotorCanAddress);

	public LiftMotor() {
		m_lift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.timeoutMs);

		m_lift.configNominalOutputForward(0, Constants.timeoutMs);
		m_lift.configNominalOutputReverse(0, Constants.timeoutMs);
		
		m_lift.setSensorPhase(false);
//		m_lift.setInverted(true);
				   
		m_lift.setNeutralMode(NeutralMode.Brake);
		
		Constants.liftSecondsFromNeutral = SmartDashboard.getNumber("Lift PID Ramp", Constants.liftSecondsFromNeutral);
		m_lift.configClosedloopRamp(Constants.liftSecondsFromNeutral, Constants.timeoutMs);
		
		/* 
		 * for now, forget about the absolute position, just set the relative position
		 * to 0 upon startup. This means the robot must be in the same position each
		 * time it's powered up!!
		 */
		m_lift.setSelectedSensorPosition(0, 0, Constants.timeoutMs);

	}

	public void moveLift(double setpoint)
	{
		Constants.liftMotorPidKp = SmartDashboard.getNumber("Lift PID KP", Constants.liftMotorPidKp);
    	Constants.liftMotorPidKi = SmartDashboard.getNumber("Lift PID KI", Constants.liftMotorPidKi);
    	Constants.liftMotorPidKd = SmartDashboard.getNumber("Lift PID KD", Constants.liftMotorPidKd);
    	Constants.liftMotorAllowableClosedLoopError = SmartDashboard.getNumber("Lift PID Allowable Error", Constants.liftMotorAllowableClosedLoopError);

	   	m_lift.config_kP(0, Constants.liftMotorPidKp, Constants.timeoutMs);
	   	m_lift.config_kI(0, Constants.liftMotorPidKi, Constants.timeoutMs);
	   	m_lift.config_kD(0, Constants.liftMotorPidKd, Constants.timeoutMs);
	   	m_lift.config_kF(0, Constants.liftMotorPidKf, Constants.timeoutMs);

	   	Constants.liftMotorPeakVoltage = SmartDashboard.getNumber("Lift Peak Voltage", Constants.liftMotorPeakVoltage);
	   	m_lift.configPeakOutputForward(Constants.liftMotorPeakVoltage, Constants.timeoutMs);
	   	m_lift.configPeakOutputReverse(Constants.liftMotorPeakVoltage, Constants.timeoutMs);

	   	m_lift.configAllowableClosedloopError(0, (int)Constants.liftMotorAllowableClosedLoopError, Constants.timeoutMs);	   		   	

		m_lift.set(ControlMode.Position, setpoint);
	}

    public void initDefaultCommand() {
    }
        
    public void stop() {
    	m_lift.set(0.0);
    }  

	public double getEncoderCount() {
    	return m_lift.getSelectedSensorPosition(0);
    }
    
    public int getClosedLoopError() {
    	return m_lift.getClosedLoopError(0);
    }

    public double getMotorOutputVoltage() {
    	return m_lift.getMotorOutputVoltage();
    }
    
    public void resetEncoder() {
    	m_lift.setSelectedSensorPosition(0, 0, Constants.timeoutMs);
    }
}

