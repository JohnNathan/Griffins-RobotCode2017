package org.usfirst.frc.team1884.util;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class DoubleSolenoidWrapper extends DoubleSolenoid {

	public final static DoubleSolenoid.Value
			kExt = DoubleSolenoid.Value.kForward,
			kRet = DoubleSolenoid.Value.kReverse,
			kOff = DoubleSolenoid.Value.kOff;
	
	private long timestamp = Long.MAX_VALUE;
	private final long k_time;
	
	private DoubleSolenoid.Value physical_state;
	
	public DoubleSolenoidWrapper(int forwardChannel, int reverseChannel, long k_time) {
		super(forwardChannel, reverseChannel);
		this.k_time = k_time;
		this.physical_state = kRet;
	}
	
	public DoubleSolenoidWrapper(int moduleNumber, int forwardChannel, int reverseChannel, long k_time) {
		super(moduleNumber, forwardChannel, reverseChannel);
		this.k_time = k_time;
		this.physical_state = kRet;
	}
	
	public DoubleSolenoid.Value getActualState() {
		return this.physical_state;
	}
	
	public DoubleSolenoid.Value getElectricalState() {
		return this.get();
	}
	
	public boolean finishedExtension() {
		return System.currentTimeMillis() - this.timestamp > k_time;
	}
	
	@Override
	public void set(DoubleSolenoid.Value value) {
		super.set(value);
		this.timestamp = System.currentTimeMillis();
		this.physical_state = value != kOff ? value : this.physical_state;
	}
	
	public void update() {
		if (System.currentTimeMillis() - this.timestamp > k_time) {
			super.set(kOff);
		}
	}
	
}
