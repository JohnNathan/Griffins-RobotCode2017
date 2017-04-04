package org.usfirst.frc.team1884.util;

public class TrapezoidEncoderFollower {
	
	@SuppressWarnings("unused")
	private double kp, ki, kd, kv, ka;
	private TrapezoidProfile traj;
	private double initialTime, initialDistance;
	
	public TrapezoidEncoderFollower(TrapezoidProfile traj) {
		this.traj = traj;
	}
	
	public void setPIDVA(double kp, double ki, double kd, double kv, double ka) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		this.kv = kv;
		this.ka = ka;
	}
	
	public void setStartPoint(double initialTime, double initialDistance) {
		this.initialTime = initialTime;
		this.initialDistance = initialDistance;
	}
	
	public void reset() {
		err_last = 0;
	}
	
	double err_last = 0;
	double t_last = 0.0;
	public double calculate(double t, double distance) {
		t -= initialTime;
		distance -= initialDistance;
		double dt = t - t_last;
		double err = traj.position(t) - distance;
		double value = kp * err
				+ kd * (err - err_last) / dt
				+ kv * traj.velocity(t)
				+ ka * traj.acceleration(t);
		err_last = err;
		t_last = t;
		
		//debug statements
		System.out.println(err);
		
		return value;
	}
	
	public boolean isFinished(double t) {
		return traj.isFinished(t-initialTime);
	}
	
}
