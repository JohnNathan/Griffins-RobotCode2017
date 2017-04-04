package org.usfirst.frc.team1884.robot.subsystems;

import org.usfirst.frc.team1884.robot.Robot;
import org.usfirst.frc.team1884.robot.commands.ClimberDefaultCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climber extends Subsystem implements Debuggable {
	
	private CANTalon motor1, motor2;
	
	private Encoder enc;
	private final static double k_distance_per_pulse = 1.0; //TODO
	
	private final static double k_total_distance = 1.0; //TODO
	private final static double k_current_draw_threshold = 60.0; //TODO
	
	public Climber() {
		motor1 = new CANTalon(Robot.Map.CL_1);
		motor2 = new CANTalon(Robot.Map.CL_2);
		
		enc = new Encoder(Robot.Map.CL_ENC_A, Robot.Map.CL_ENC_B, false, Encoder.EncodingType.k4X);
		enc.setDistancePerPulse(k_distance_per_pulse);
	}
	
	public void set(double speed) {
		speed = Math.abs(speed);
		motor1.set(speed);
		motor2.set(speed);
		
	}
	
	public void stop() {
		this.set(0.0);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new ClimberDefaultCommand());
    }
    
    public double getCurrentDraw() {
    	return motor1.getOutputCurrent() + motor2.getOutputCurrent();
    }
    
    public double getDistance() {
    	return enc.getDistance();
    }
    
    public boolean reachedStageOne() {
    	return this.getDistance() >= k_total_distance;
    }
    
    public boolean isExceedAmpThreshold() {
    	return this.getCurrentDraw() > k_current_draw_threshold;
    }
    
    public void reset() {
    	enc.reset();
    }
    
    @Override
    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("CL Encoder Distance", enc.getDistance());
    	SmartDashboard.putNumber("CL Current Draw", this.getCurrentDraw());
    }

}