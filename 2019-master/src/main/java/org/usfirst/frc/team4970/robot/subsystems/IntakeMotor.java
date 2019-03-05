package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import utils.Constants;

import org.usfirst.frc.team4970.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class IntakeMotor extends Subsystem {
	
	private double intakeDc = 0.0;
	private double outputDc = 0.0;
	
	public IntakeMotor() {
		Robot.m_intake.configFactoryDefault();

		Robot.m_intake.setNeutralMode(NeutralMode.Brake);
		
    	Robot.m_intake.configNominalOutputForward(0, Constants.timeoutMs);
    	Robot.m_intake.configNominalOutputReverse(0, Constants.timeoutMs);
    	Robot.m_intake.configPeakOutputForward(1.0, Constants.timeoutMs);
    	Robot.m_intake.configPeakOutputReverse(-1.0, Constants.timeoutMs);
	}
	
    public void initDefaultCommand() {
    }
    
    public void intakeBall() {
    	intakeDc = Math.max(0.0, Robot.m_oi.joystick.getRawAxis(4));
    	Robot.m_intake.set(ControlMode.PercentOutput, -1.0 * intakeDc);
    }
    
    public void intakeBallSlow(double dutyCycle) {
    	Robot.m_intake.set(ControlMode.PercentOutput, -1.0 * dutyCycle);
    }

    public void outputBall() {
    	outputDc = Math.max(0.0, Robot.m_oi.joystick.getRawAxis(3)); 
   		Robot.m_intake.set(ControlMode.PercentOutput, outputDc);
    }

    public void outputBallSlow(double dutyCycle) {
   		Robot.m_intake.set(ControlMode.PercentOutput, dutyCycle);
    }
 
    public void stop() {
    	Robot.m_intake.set(ControlMode.PercentOutput, 0.0);
    }

}

