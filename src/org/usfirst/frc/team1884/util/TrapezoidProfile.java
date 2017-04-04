package org.usfirst.frc.team1884.util;

public class TrapezoidProfile {
	
	public static class Config {
		public double v_cruise;
		public double a;
		
		public Config(double v_cruise, double a) {
			this.v_cruise = v_cruise;
			this.a = a;
		}
	}
	
	private double t, t_c, a, v_cruise;
	
	public TrapezoidProfile(double v_cruise, double a, double distance) {
		double t = 2 * Math.sqrt(distance / a);
		double t_c = v_cruise / a;
		
		if (t_c < t / 2) {
			double area = (t/2 - t_c)*(t/2 - t_c)*a;
			double t_ext = area / v_cruise;
			
			this.t = t + t_ext;
			this.t_c = t_c;
		} else {
			this.t = t;
			this.t_c = t / 2;
		}
		
		this.v_cruise = v_cruise;
		this.a = a;
	}
	
	public TrapezoidProfile(Config cfg, double distance) {
		this(cfg.v_cruise, cfg.a, distance);
	}
	
	public double position(double t) {
		if (t > this.t)
			return a*t_c*t_c + v_cruise*(this.t - 2*t_c);
		if (t > this.t - t_c)
			return a*t_c*t_c*.5 + v_cruise*(this.t - 2*t_c) + (a*t_c+a*(this.t-t))/2*(t-(this.t-t_c));
		if (t > this.t_c) {
			return a*t_c*t_c*.5 + v_cruise*(t - t_c);
		}
		return a*t*t*.5;
	}
	
	public double velocity(double t) {
		if (t > this.t)
			return 0.0;
		if (t < t_c)
			return a*t;
		if (t > this.t - t_c)
			return a*(this.t - t);
		return v_cruise;
	}
	
	public double acceleration(double t) {
		if (t < t_c) return a;
		if (t > this.t - t_c) return -a;
		return 0.0;
	}
	
	public boolean isFinished(double t) {
		return t > this.t;
	}
	
}
