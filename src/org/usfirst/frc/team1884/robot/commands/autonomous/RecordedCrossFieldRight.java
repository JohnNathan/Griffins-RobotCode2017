package org.usfirst.frc.team1884.robot.commands.autonomous;

import org.usfirst.frc.team1884.robot.Robot;

public class RecordedCrossFieldRight extends FollowRecordedAuto {

	@Override
	protected void initialize() {
		super.load(Robot.Map.REC_CROSS_FIELD_RIGHT);
		super.initialize();
	}
	
}
