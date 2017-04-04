package org.usfirst.frc.team1884.robot.commands.autonomous;

import org.usfirst.frc.team1884.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToLeftPeg extends Command {

//	private Trajectory traj;
//	private final static Waypoint[] waypoints = {new Waypoint(0, 0, 0), new Waypoint(114.3, 0, Pathfinder.d2r(-60)),
//			new Waypoint(131.953, -30.5562, Pathfinder.d2r(-60))};
	
    public DriveToLeftPeg() {
        requires(Robot.InstanceMap.driveTrain);
//        traj = Pathfinder.generate(waypoints, DriveTrain.cfg);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	FollowMotionProfile.instance.loadTrajectory(traj);
//    	FollowMotionProfile.instance.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	FollowMotionProfile.instance.execute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return FollowMotionProfile.instance.isFinished();
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	FollowMotionProfile.instance.end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//    	FollowMotionProfile.instance.interrupted();
    }
}
