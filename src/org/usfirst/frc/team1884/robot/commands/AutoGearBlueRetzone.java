package org.usfirst.frc.team1884.robot.commands;

import org.usfirst.frc.team1884.robot.commands.autonomous.TrapezoidBackup;
import org.usfirst.frc.team1884.robot.commands.autonomous.TrapezoidForwardRetzonePeg;
import org.usfirst.frc.team1884.robot.commands.autonomous.TrapezoidSideTurnLeft;
import org.usfirst.frc.team1884.robot.commands.autonomous.TrapezoidToRetzonePeg;
import org.usfirst.frc.team1884.robot.commands.autonomous.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearBlueRetzone extends CommandGroup {

    public AutoGearBlueRetzone() {
    	addSequential(new PutGearInUpPosition());
    	addSequential(new WaitCommand(.5));
    	addSequential(new TrapezoidForwardRetzonePeg()); //    	addSequential(new DriveToPeg());
    	addSequential(new TrapezoidSideTurnLeft());
    	addSequential(new TrapezoidToRetzonePeg());
    	addSequential(new WaitCommand(.5));
    	addSequential(new GearManOpen());
    	addSequential(new WaitCommand(.2));
    	addSequential(new TrapezoidBackup());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
