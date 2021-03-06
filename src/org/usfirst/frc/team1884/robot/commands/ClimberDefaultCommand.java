package org.usfirst.frc.team1884.robot.commands;

import org.usfirst.frc.team1884.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberDefaultCommand extends Command {

    public ClimberDefaultCommand() {
        requires(Robot.InstanceMap.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.InstanceMap.climber.updateSmartDashboard();
    	
    	Robot.InstanceMap.climber.set(Robot.oi.getOperatorController().rt_a()); //TODO temporary
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
