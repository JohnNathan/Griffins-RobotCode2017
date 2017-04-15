package org.usfirst.frc.team1884.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitCommand extends Command {

	private long t;
	private final long timeout;
	
    public WaitCommand() {
    	t = Long.MAX_VALUE;
    	timeout = 1000L;
    }
    
    public WaitCommand(double timeoutInSeconds) {
    	t = Long.MAX_VALUE;
    	timeout = (long)(timeoutInSeconds*1000);
    }
    
    public WaitCommand(long timeoutInMillis) {
    	t = Long.MAX_VALUE;
    	timeout = timeoutInMillis;
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
        return System.currentTimeMillis() - t > timeout;
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
