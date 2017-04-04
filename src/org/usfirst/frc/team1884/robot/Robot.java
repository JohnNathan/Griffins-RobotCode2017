
package org.usfirst.frc.team1884.robot;

import java.util.HashMap;

import org.usfirst.frc.team1884.robot.commands.AutoBaseLine;
import org.usfirst.frc.team1884.robot.commands.AutoCrossField;
import org.usfirst.frc.team1884.robot.commands.AutoGearBlueBoiler;
import org.usfirst.frc.team1884.robot.commands.AutoGearMiddle;
import org.usfirst.frc.team1884.robot.commands.AutoGearRedBoiler;
import org.usfirst.frc.team1884.robot.commands.AutoGearRedRetzone;
import org.usfirst.frc.team1884.robot.commands.AutoTestCommand;
import org.usfirst.frc.team1884.robot.commands.DriveShiftHighSpeed;
import org.usfirst.frc.team1884.robot.commands.AutoGearBlueRetzone;
import org.usfirst.frc.team1884.robot.commands.autonomous.DoNothing;
import org.usfirst.frc.team1884.robot.subsystems.Climber;
import org.usfirst.frc.team1884.robot.subsystems.CurrentManager;
import org.usfirst.frc.team1884.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1884.robot.subsystems.GearIntake;
import org.usfirst.frc.team1884.robot.subsystems.GearManipulator;
import org.usfirst.frc.team1884.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Robot instance = null;
	
	public static class Map {
		//Constants go here
		
		//CAN
		public final static int
			DT_LEFT_1 = 0,
			DT_LEFT_2 = 1,
			DT_RIGHT_1 = 2,
			DT_RIGHT_2 = 3,
			CL_1 = 4,
			CL_2 = 5,
			SH_FEED = 6,
			SH_MAIN = 7,
			SH_BELT = 8;
		
		//PWM
		public final static int
			IN_LIFT = 0,
			IN_ROLLER = 1;
		
		//PCM
		public final static int
			DT_SHIFT_F = 1,
			DT_SHIFT_R = 0,
			GM_CLAW_F = 6,
			GM_CLAW_R = 5,
			GM_LINK_F = 3,
			GM_LINK_R = 4;
		
		//DIO
		public final static int
			DT_L_ENC_A = 0,
			DT_L_ENC_B = 1,
			DT_R_ENC_A = 2,
			DT_R_ENC_B = 3,
			CL_ENC_A = 4,
			CL_ENC_B = 5,
			IN_TOP_LIM = 6,
			IN_BOT_LIM = 7,
			GM_SWITCH = 8;
		
		//PDP
		//idk man just use the CAN functionality
	}
	
	static {
		InstanceMap.poke();
	}
	
	public static class InstanceMap {
		//Instances go here
		
		public final static DriveTrain driveTrain = new DriveTrain();
		public final static GearManipulator gearMan = new GearManipulator();
		public final static Climber climber = new Climber();
		public final static GearIntake intake = new GearIntake();
		public final static Shooter shooter = new Shooter();
		public final static CurrentManager ampman = new CurrentManager();
		
		private static void poke() { }
		
	}
	
	public static OI oi;

	public Compressor compressor;
	private SendableChooser<Command> autoChooser;
	public final static HashMap<String, Command> autos;
	static {
		autos = new HashMap<String, Command>();
		autos.put("Gear Middle", new AutoGearMiddle());
		autos.put("Blue Boiler", new AutoGearBlueBoiler());
		autos.put("Blue Retrieval Zone", new AutoGearBlueRetzone());
		autos.put("Red Boiler", new AutoGearRedBoiler());
		autos.put("Red Retrieval Zone", new AutoGearRedRetzone());
		autos.put("Base Line", new AutoBaseLine());
		autos.put("Cross Field", new AutoCrossField());
		autos.put("Test Auto", new AutoTestCommand());
	}
	
	@Override
	public void robotInit() {
		oi = new OI();
		
		compressor = new Compressor();
		CameraServer.getInstance().startAutomaticCapture();
		
		instance = this;
		
		autoChooser = new SendableChooser<Command>();
		for (java.util.Map.Entry<String, Command> entry : autos.entrySet()) {
			autoChooser.addObject(entry.getKey(), entry.getValue());
		}
		autoChooser.addDefault("Do Nothing", new DoNothing());
		SmartDashboard.putData("Autonomous Chooser", autoChooser);
	}

	@Override
	public void disabledInit() {
		compressor.stop();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	private Command autoCommand = null;
	@Override
	public void autonomousInit() {
		new DriveShiftHighSpeed().start();
		compressor.stop();
		
		autoCommand = autoChooser.getSelected();
		if (autoCommand != null) autoCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autoCommand != null) autoCommand.cancel();
		compressor.start();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public void startCompressor() {
		compressor.start();
	}
	
	public void stopCompressor() {
		compressor.stop();
	}
}
