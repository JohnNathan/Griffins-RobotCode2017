package org.usfirst.frc.team1884.robot.subsystems;

import org.usfirst.frc.team1884.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem implements Debuggable {

	private CANTalon shooter, feed, conveyor;
	private final static double k_shooter_speed = 180.0;
	
    public Shooter() {
    	feed = new CANTalon(Robot.Map.SH_FEED);
    	conveyor = new CANTalon(Robot.Map.SH_BELT);
    	
    	shooter = new CANTalon(Robot.Map.SH_MAIN);
    	shooter.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
    	shooter.setProfile(0);
    	shooter.setF(0.75);
    	shooter.setP(.01);
    	shooter.setI(.0);
    	shooter.setD(.007);
    	shooter.changeControlMode(CANTalon.TalonControlMode.Speed);
    	shooter.SetVelocityMeasurementPeriod(CANTalon.VelocityMeasurementPeriod.Period_20Ms);
    }
    
    public void spinShooter() {
    	shooter.changeControlMode(CANTalon.TalonControlMode.Speed);
    	shooter.setSetpoint(k_shooter_speed);
    }
    
    public void stopShooter() {
    	shooter.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	shooter.set(0.0);
    }
    
    public void feed() {
    	feed.set(-0.35);
    }
    public void feedStop() {
    	feed.set(0.0);
    }
    
    public void beltIn() {
    	conveyor.set(1.0);
    }
    public void beltInStop() {
    	conveyor.set(0.0);
    }

    public void initDefaultCommand() {
    }
    
    @Override
    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("SH Enc Speed", shooter.getEncVelocity());
    }
}

