package org.usfirst.frc.team1884.robot.commands.autonomous;

import org.usfirst.frc.team1884.robot.Robot;

public class RecordedHopperBlueLeft extends FollowRecordedAuto {

	@Override
	protected void initialize() {
		super.load(Robot.Map.REC_TRIGGER_HOPPER_BLUE_LEFT);
		super.initialize();
	}
	
}
