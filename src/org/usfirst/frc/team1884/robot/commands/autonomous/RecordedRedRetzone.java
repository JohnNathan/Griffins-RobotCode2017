package org.usfirst.frc.team1884.robot.commands.autonomous;

import org.usfirst.frc.team1884.robot.Robot;

public class RecordedRedRetzone extends FollowRecordedAuto {

	@Override
    protected void initialize() {
    	super.load(Robot.Map.REC_RED_RETZONE_AUTO);
    	super.initialize();
    }
}
