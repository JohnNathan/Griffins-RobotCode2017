package org.usfirst.frc.team1884.robot.subsystems;

public interface IRecordable {
	
	public double[] getData();
	public void putData(double[] data);
	public int getLength();
	public void startFollowRecording();
	
}
