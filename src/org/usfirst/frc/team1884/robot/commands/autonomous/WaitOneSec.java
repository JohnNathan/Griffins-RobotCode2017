package org.usfirst.frc.team1884.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitOneSec extends Command {

	private long t;
	
    public WaitOneSec() {
    	t = Long.MAX_VALUE;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	t = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis() - t > 1000;
    }

    // Called once after isFinished returns true
    protected void end() {
    	t = Long.MAX_VALUE;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	t = Long.MAX_VALUE;
    }
}
