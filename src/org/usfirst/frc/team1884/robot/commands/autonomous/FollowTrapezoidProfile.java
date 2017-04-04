package org.usfirst.frc.team1884.robot.commands.autonomous;

import org.usfirst.frc.team1884.robot.Robot;
import org.usfirst.frc.team1884.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1884.util.TrapezoidEncoderFollower;
import org.usfirst.frc.team1884.util.TrapezoidProfile;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FollowTrapezoidProfile extends Command {

	public final static FollowTrapezoidProfile instance = new FollowTrapezoidProfile();
	
	public final static double
		kp = 0.13,
		ki = 0.0,
		kd = 0.02,
		kv = 1.0 / DriveTrain.max_velocity,
		ka = 0.001;
	
    public FollowTrapezoidProfile() {
        requires(Robot.InstanceMap.driveTrain);
    }
    
    private TrapezoidEncoderFollower left = null, right = null;
    public void loadTrajectory(TrapezoidProfile traj) {
    	left = new TrapezoidEncoderFollower(traj);
    	left.setPIDVA(kp, ki, kd, kv, ka);
    	right = new TrapezoidEncoderFollower(traj);
    	right.setPIDVA(kp, ki, kd, kv, ka);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (left == null || right == null) return;
    	double timestamp = System.currentTimeMillis() / 1000.0;
    	left.setStartPoint(timestamp, Robot.InstanceMap.driveTrain.getLeftDistance());
    	right.setStartPoint(timestamp, Robot.InstanceMap.driveTrain.getRightDistance());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (left == null || right == null) return;
    	double timestamp = System.currentTimeMillis() / 1000.0;
    	Robot.InstanceMap.driveTrain.setLeftSpeed(left.calculate(timestamp, Robot.InstanceMap.driveTrain.getLeftDistance()));
    	Robot.InstanceMap.driveTrain.setRightSpeed(-right.calculate(timestamp, Robot.InstanceMap.driveTrain.getRightDistance()));
    }
    
    protected void executeReversed() {
    	if (left == null || right == null) return;
    	double timestamp = System.currentTimeMillis() / 1000.0;
    	Robot.InstanceMap.driveTrain.setLeftSpeed(-left.calculate(timestamp, Robot.InstanceMap.driveTrain.getLeftDistance()));
    	Robot.InstanceMap.driveTrain.setRightSpeed(right.calculate(timestamp, Robot.InstanceMap.driveTrain.getRightDistance()));
    }
    
    protected void executeTurnLeft() {
    	if (left == null || right == null) return;
    	double timestamp = System.currentTimeMillis() / 1000.0;
    	Robot.InstanceMap.driveTrain.setLeftSpeed(left.calculate(timestamp, Robot.InstanceMap.driveTrain.getLeftDistance()));
    	Robot.InstanceMap.driveTrain.setRightSpeed(right.calculate(timestamp, Robot.InstanceMap.driveTrain.getRightDistance()));
    }
    
    protected void executeTurnRight() {
    	if (left == null || right == null) return;
    	double timestamp = System.currentTimeMillis() / 1000.0;
    	Robot.InstanceMap.driveTrain.setLeftSpeed(-left.calculate(timestamp, Robot.InstanceMap.driveTrain.getLeftDistance()));
    	Robot.InstanceMap.driveTrain.setRightSpeed(-right.calculate(timestamp, Robot.InstanceMap.driveTrain.getRightDistance()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double timestamp = System.currentTimeMillis() / 1000.0;
        return left.isFinished(timestamp) && right.isFinished(timestamp);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.InstanceMap.driveTrain.setLeftSpeed(0);
    	Robot.InstanceMap.driveTrain.setRightSpeed(0);
    	left = null;
    	right = null;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.InstanceMap.driveTrain.setLeftSpeed(0);
    	Robot.InstanceMap.driveTrain.setRightSpeed(0);
    	left = null;
    	right = null;
    }
}
