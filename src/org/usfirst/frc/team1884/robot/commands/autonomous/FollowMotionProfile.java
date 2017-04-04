package org.usfirst.frc.team1884.robot.commands.autonomous;

import org.usfirst.frc.team1884.robot.Robot;
import org.usfirst.frc.team1884.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1884.util.EncoderFollower;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class FollowMotionProfile extends Command {
	
	public final static FollowMotionProfile instance = new FollowMotionProfile();

	public final static double k_wheelbase_width_inches = 26.0; //TODO
	public final static double
		kp = 0.1,
		ki = 0.0,
		kd = 0.02,
		kv = 1.0 / DriveTrain.max_velocity,
		ka = 0.001;
	
    public FollowMotionProfile() {
        requires(Robot.InstanceMap.driveTrain);
    }
    
    private EncoderFollower left = null, right = null;
    public void loadTrajectory(Trajectory traj) {
    	TankModifier mod = new TankModifier(traj).modify(k_wheelbase_width_inches);
    	
    	left = new EncoderFollower(mod.getLeftTrajectory());
    	left.setPIDVA(kp, ki, kd, kv, ka);
    	right = new EncoderFollower(mod.getRightTrajectory());
    	right.setPIDVA(kp, ki, kd, kv, ka);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (left == null || right == null) return;
    	left.setStartPoint(Robot.InstanceMap.driveTrain.getLeftDistance());
    	right.setStartPoint(Robot.InstanceMap.driveTrain.getRightDistance());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (left == null || right == null) return;
    	Robot.InstanceMap.driveTrain.setLeftSpeed(left.calculate(Robot.InstanceMap.driveTrain.getLeftDistance()));
    	Robot.InstanceMap.driveTrain.setRightSpeed(-right.calculate(Robot.InstanceMap.driveTrain.getRightDistance()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return left.isFinished() && right.isFinished();
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
    }
}
