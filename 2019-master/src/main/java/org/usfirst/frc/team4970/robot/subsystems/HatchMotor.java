package org.usfirst.frc.team4970.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import utils.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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

		m_hatch.setNeutralMode(NeutralMode.Brake);
		
    	m_hatch.configNominalOutputForward(0, Constants.timeoutMs);
    	m_hatch.configNominalOutputReverse(0, Constants.timeoutMs);
    	m_hatch.configPeakOutputForward(1.0, Constants.timeoutMs);
		m_hatch.configPeakOutputReverse(-1.0, Constants.timeoutMs);
	}
	
    public void initDefaultCommand() {
//    	setDefaultCommand(new HatchUp());	
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
}

