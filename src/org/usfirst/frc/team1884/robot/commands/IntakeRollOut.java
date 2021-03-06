package org.usfirst.frc.team1884.robot.commands;

import org.usfirst.frc.team1884.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeRollOut extends Command {

    public IntakeRollOut() {
        requires(Robot.InstanceMap.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.InstanceMap.intake.outtake();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.InstanceMap.intake.outtake();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.InstanceMap.intake.rollerOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.InstanceMap.intake.rollerOff();
    }
}
