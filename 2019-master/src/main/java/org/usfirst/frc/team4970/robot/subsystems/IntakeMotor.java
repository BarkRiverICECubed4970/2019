package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import utils.Constants;

import org.usfirst.frc.team4970.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class IntakeMotor extends Subsystem {
	
	private double intakeDc = 0.0;
	private double outputDc = 0.0;
	
	WPI_TalonSRX m_intake = new WPI_TalonSRX(Constants.intakeMotorCanAddress);

	public IntakeMotor() {
		m_intake.setNeutralMode(NeutralMode.Brake);
		
    	m_intake.configNominalOutputForward(0, Constants.timeoutMs);
    	m_intake.configNominalOutputReverse(0, Constants.timeoutMs);
    	m_intake.configPeakOutputForward(1.0, Constants.timeoutMs);
    	m_intake.configPeakOutputReverse(-1.0, Constants.timeoutMs);
	}
	
    public void initDefaultCommand() {
    }
    
    public void intakeBall() {
    	intakeDc = Math.max(0.0, Robot.m_oi.joystick.getRawAxis(4));
    	m_intake.set(ControlMode.PercentOutput, intakeDc);
    }
    
    public void intakeBallSlow(double dutyCycle) {
    	m_intake.set(ControlMode.PercentOutput, dutyCycle);
    }

    public void outputBall() {
    	outputDc = Math.max(0.0, Robot.m_oi.joystick.getRawAxis(3)); 
   		m_intake.set(ControlMode.PercentOutput, -1.0*outputDc);
    }

    public void outputBallSlow(double dutyCycle) {
   		m_intake.set(ControlMode.PercentOutput, -1.0*dutyCycle);
    }
 
    public void stop() {
    	m_intake.set(ControlMode.PercentOutput, 0.0);
    }

}

