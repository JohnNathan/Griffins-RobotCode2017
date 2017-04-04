package org.usfirst.frc.team1884.util;

import jaci.pathfinder.Trajectory;

public class EncoderFollower {
	
	@SuppressWarnings("unused")
	private double kp, ki, kd, kv, ka;
	private Trajectory traj;
	private double initialPosition;
	
	public EncoderFollower(Trajectory traj) {
		this.traj = traj.copy();
	}
	
	public void setPIDVA(double kp, double ki, double kd, double kv, double ka) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		this.kv = kv;
		this.ka = ka;
	}
	
	public void setStartPoint(double initialDistance) {
		this.initialPosition = initialDistance;
	}
	
	public void reset() {
		currentSegment = 0;
		err_last = 0;
	}
	
	int currentSegment = 0;
	double err_last = 0;
	public double calculate(double distance) {
		distance -= initialPosition;
		
		if (currentSegment < traj.length()) {
			Trajectory.Segment seg = traj.get(currentSegment);
			double err = seg.position - distance;
			double value = kp * err
//					+ ki * (err + err_last) * seg.dt
					+ kd * (err - err_last) / seg.dt
					+ kv * seg.velocity
					+ ka * seg.acceleration;
			err_last = err;
			++currentSegment;
			
			//debug statement
			System.out.println(err);
			
			return value;
		}
		
		return 0.0;
	}
	
	public boolean isFinished() {
		return currentSegment >= traj.length();
	}
	
}
