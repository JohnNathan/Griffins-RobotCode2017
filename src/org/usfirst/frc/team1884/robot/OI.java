package org.usfirst.frc.team1884.robot;

import org.usfirst.frc.team1884.robot.commands.DriveShiftHighSpeed;
import org.usfirst.frc.team1884.robot.commands.DriveShiftLowSpeed;
import org.usfirst.frc.team1884.robot.commands.GearManClose;
import org.usfirst.frc.team1884.robot.commands.GearManOpen;
import org.usfirst.frc.team1884.robot.commands.IntakeDownMore;
import org.usfirst.frc.team1884.robot.commands.IntakeRollIn;
import org.usfirst.frc.team1884.robot.commands.IntakeRollOut;
import org.usfirst.frc.team1884.robot.commands.IntakeUp;
import org.usfirst.frc.team1884.robot.commands.PutGearInDownPosition;
import org.usfirst.frc.team1884.robot.commands.PutGearInUpPosition;
import org.usfirst.frc.team1884.robot.commands.autonomous.DriveToLeftPeg;
import org.usfirst.frc.team1884.util.input.XBoxController;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	private XBoxController driverController, operatorController;
	
	public OI() {
		driverController = new XBoxController(0);
		operatorController = new XBoxController(1);
		
		new Trigger() {
			@Override
			public boolean get() {
				return Robot.InstanceMap.gearMan.isGearIn();
			}
		}.whenActive(new GearManClose());
		driverController.rt_b.whenActive(new DriveShiftLowSpeed());
		driverController.lt_b.whenActive(new DriveShiftHighSpeed());
		
//		operatorController.rb.whileHeld(new ShooterShootCommand());
//		operatorController.back.whileHeld(new ShooterFeedCommand());
		operatorController.a.whenPressed(new PutGearInUpPosition());
		operatorController.b.whenPressed(new PutGearInDownPosition());
		operatorController.lb.whileHeld(new IntakeRollIn());
		operatorController.lt_b.whileActive(new IntakeRollOut());
		operatorController.x.whenPressed(new GearManOpen());
		operatorController.y.whenPressed(new GearManClose());
		operatorController.r_stick.whenPressed(new IntakeUp());
		operatorController.l_stick.whenPressed(new IntakeDownMore());
	}
	
	public XBoxController getDriverController() {
		return driverController;
	}
	
	public XBoxController getOperatorController() {
		return operatorController;
	}
	
	public boolean getRecordingButton() {
		return driverController.lb();
	}
	
}
