package org.usfirst.frc.team1884.robot.commands.autonomous;

import org.usfirst.frc.team1884.robot.Robot;

public class RecordedRedBoiler extends FollowRecordedAuto {

	@Override
    protected void initialize() {
    	super.load(Robot.Map.REC_RED_BOILER_PATH);
    	super.initialize();
    }
}
