package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.utils.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class RearClimbDriveSystem extends Subsystem {
	
	private double intakeDc = 0.0;
	private double outputDc = 0.0;
	
	WPI_TalonSRX m_rearClimbDrive = new WPI_TalonSRX(Constants.rearClimbDriveMotorCanAddress);

	public RearClimbDriveSystem() {
		m_rearClimbDrive.configFactoryDefault();

		m_rearClimbDrive.setNeutralMode(NeutralMode.Brake);
		
    	m_rearClimbDrive.configNominalOutputForward(0, Constants.timeoutMs);
    	m_rearClimbDrive.configNominalOutputReverse(0, Constants.timeoutMs);
    	m_rearClimbDrive.configPeakOutputForward(1.0, Constants.timeoutMs);
    	m_rearClimbDrive.configPeakOutputReverse(-1.0, Constants.timeoutMs);
	}
	
    public void initDefaultCommand() {
    }
    
    public void driveForward(double dutyCycle) {
//    	intakeDc = Math.max(0.0, Robot.m_oi.joystick2.getRawAxis(4));
    	m_rearClimbDrive.set(ControlMode.PercentOutput, -1.0 * dutyCycle);
    }
    
    public void driveReverse(double dutyCycle) {
//    	outputDc = Math.max(0.0, Robot.m_oi.joystick2.getRawAxis(3)); 
   		m_rearClimbDrive.set(ControlMode.PercentOutput, dutyCycle);
    }

    public void stop() {
    	m_rearClimbDrive.set(ControlMode.PercentOutput, 0.0);
    }

}

