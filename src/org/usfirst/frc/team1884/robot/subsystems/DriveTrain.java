package org.usfirst.frc.team1884.robot.subsystems;

import org.usfirst.frc.team1884.robot.Robot;
import org.usfirst.frc.team1884.robot.commands.DriveCommand;
import org.usfirst.frc.team1884.util.DoubleSolenoidWrapper;
import org.usfirst.frc.team1884.util.RecordingFollower;
import org.usfirst.frc.team1884.util.TrapezoidProfile;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Trajectory;

public class DriveTrain extends Subsystem implements Debuggable, Recordable {

	private CANTalon left1, left2, right1, right2;
	private DoubleSolenoidWrapper shifter;
	private Encoder left_enc, right_enc;
	
	private final static long shift_timer = 250L;
	
	private final static double k_distance_per_pulse = 4.0*Math.PI/256.0; //TODO
	public final static DoubleSolenoid.Value
			k_shifter_high_speed = DoubleSolenoid.Value.kForward,
			k_shifter_low_speed = DoubleSolenoid.Value.kReverse,
			k_off = DoubleSolenoid.Value.kOff;
	
	public final static double max_velocity = 175.0, max_accel = 1600, max_jerk = 56000;
	public final static Trajectory.Config cfg =
			new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
				.02, max_velocity*.65, max_accel*.3, max_jerk);
	public final static TrapezoidProfile.Config tCfg = new TrapezoidProfile.Config(max_velocity, max_accel*.25);
	public final static double d_center_from_back = 19.5;
	
	public DriveTrain() {
		
		left1 = new CANTalon(Robot.Map.DT_LEFT_1);
		left2 = new CANTalon(Robot.Map.DT_LEFT_2);
		right1 = new CANTalon(Robot.Map.DT_RIGHT_1);
		right2 = new CANTalon(Robot.Map.DT_RIGHT_2);
		
		shifter = new DoubleSolenoidWrapper(Robot.Map.DT_SHIFT_F, Robot.Map.DT_SHIFT_R, shift_timer);
		
		shiftHigh();
		
		left_enc = new Encoder(Robot.Map.DT_L_ENC_A, Robot.Map.DT_L_ENC_B, false, EncodingType.k4X);
		right_enc = new Encoder(Robot.Map.DT_R_ENC_A, Robot.Map.DT_R_ENC_B, true, EncodingType.k4X);
		left_enc.setDistancePerPulse(k_distance_per_pulse);
		right_enc.setDistancePerPulse(k_distance_per_pulse);
	}
	
	public void setLeftSpeed(double speed) {
		left1.set(speed);
		left2.set(speed);
	}
	
	public void setRightSpeed(double speed) {
		right1.set(speed);
		right2.set(speed);
	}
	
	public double getLeftDistance() {
		return left_enc.getDistance();
	}
	
	public double getRightDistance() {
		return right_enc.getDistance();
	}
	
	public void resetEncoders() {
		left_enc.reset();
		right_enc.reset();
	}
	
	public void shiftHigh() {
		shifter.set(k_shifter_high_speed);
	}
	
	public void shiftLow() {
		shifter.set(k_shifter_low_speed);
	}
	
	public void update_pistons() {
		shifter.update();
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveCommand());
    }
    
    @Override
    public void updateSmartDashboard() {
    	SmartDashboard.putNumber("DT Left Encoder Distance", left_enc.getDistance());
    	SmartDashboard.putNumber("DT Right Encoder Distance", right_enc.getDistance());
    	SmartDashboard.putNumber("DT Left Current Draw", left1.getOutputCurrent() + left2.getOutputCurrent());
    	SmartDashboard.putNumber("DT Right Current Draw", right1.getOutputCurrent() + right2.getOutputCurrent());
    	SmartDashboard.putString("DT Shifter State", shifter.getActualState() == k_shifter_high_speed ? "FAST" : "STRONG");
    }

    // leftSpeed, rightSpeed, leftDistance, rightDistance
    @Override
    public double[] getData() {
    	return new double[]{left1.get(), right1.get(), this.getLeftDistance(), this.getRightDistance()};
    }
    
    private RecordingFollower rf;
    {
    	rf = new RecordingFollower(this);
    }
    // dt, leftSpeed, rightSpeed, leftPosition, rightPosition
    @Override
    public void putData(double[] data) {
    	if (data.length != 5) return;
    	rf.drive(data[0], data[1], data[2], data[3], data[4]);
    }
    
}
