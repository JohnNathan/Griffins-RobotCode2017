package org.usfirst.frc.team1884.robot.commands.autonomous;

import org.usfirst.frc.team1884.robot.Robot;

public class RecordedCrossFieldLeft extends FollowRecordedAuto {

	@Override
	protected void initialize() {
		super.load(Robot.Map.REC_CROSS_FIELD_LEFT);
		super.initialize();
	}
	
}
