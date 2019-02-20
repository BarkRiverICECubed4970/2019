package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import utils.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4970.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HatchMotor extends Subsystem {

	public enum HatchState
	{
		HATCH_UP, HATCH_DOWN
	};

	public static HatchState _hatchState = HatchState.HATCH_UP;

	public HatchMotor() {
		Robot.m_hatch.setNeutralMode(NeutralMode.Brake);
		
    	Robot.m_hatch.configNominalOutputForward(0, Constants.timeoutMs);
    	Robot.m_hatch.configNominalOutputReverse(0, Constants.timeoutMs);
    	Robot.m_hatch.configPeakOutputForward(1.0, Constants.timeoutMs);
    	Robot.m_hatch.configPeakOutputReverse(-1.0, Constants.timeoutMs);
	}
	
    public void initDefaultCommand() {
//    	holdUp();
    }
    
    public void holdUp() {
    	Robot.m_hatch.set(ControlMode.PercentOutput, SmartDashboard.getNumber("Hatch Up Duty Cycle", Constants.hatchMotorUpDutyCycle));
    }
    
    public void holdDown() {
    	Robot.m_hatch.set(ControlMode.PercentOutput, -1.0* SmartDashboard.getNumber("Hatch Down Duty Cycle", Constants.hatchMotorDownDutyCycle));
    }

    public void stop() {
    	Robot.m_hatch.set(ControlMode.PercentOutput, 0.0);
    }
}

