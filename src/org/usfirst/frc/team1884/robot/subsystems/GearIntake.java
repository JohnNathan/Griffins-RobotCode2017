package org.usfirst.frc.team1884.robot.subsystems;

import org.usfirst.frc.team1884.robot.Robot;
import org.usfirst.frc.team1884.robot.commands.IntakeHoldCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class GearIntake extends Subsystem implements IDebuggable, IRecordable {

	private VictorSP rollerMotor, liftMotor;
	private DigitalInput topLimit, botLimit;
	
	public GearIntake() {
		rollerMotor = new VictorSP(Robot.Map.IN_ROLLER);
		liftMotor = new VictorSP(Robot.Map.IN_LIFT);
		
		topLimit = new DigitalInput(Robot.Map.IN_TOP_LIM) { @Override public boolean get() { return !super.get(); } };
		botLimit = new DigitalInput(Robot.Map.IN_BOT_LIM) { @Override public boolean get() { return !super.get(); } };
	}
	
	public void up() {
		liftMotor.set(topLimit.get() ? 0.0 : 0.37);
	}
	public void down() {
		liftMotor.set(botLimit.get() || Robot.InstanceMap.gearMan.isUp() ? 0.0 : -0.1);
	}
	public void downHarder() {
		liftMotor.set(botLimit.get() || Robot.InstanceMap.gearMan.isUp() ? 0.0 : -0.4);
	}
	public void hold() {
		liftMotor.set(.10);
	}
	public void stop() {
		liftMotor.set(0.0);
	}
	public void intake() {
		rollerMotor.set(1.0);
	}
	public void outtake() {
		rollerMotor.set(-1.0);
	}
	public void rollerOff() {
		rollerMotor.set(0.0);
	}
	public boolean isAtTop() {
		return topLimit.get();
	}
	public boolean isAtBot() {
		return botLimit.get();
	}
    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeHoldCommand());
    }
    @Override
    public void updateSmartDashboard() {
    	SmartDashboard.putBoolean("IN Top Switch", this.isAtTop());
    	SmartDashboard.putBoolean("IN Bot Switch", this.isAtBot());
    }
    // arm direction(-1=down,0=hold,1=up), intake(1=in,-1=out,0=stop)
    @Override
    public double[] getData() {
    	return new double[]{ liftMotor.get() > 0.15 ? 1.0 : liftMotor.get() < 0.0 ? -1.0 : 0.0,
    			rollerMotor.get() > 0.0 ? 1 : rollerMotor.get() < 0 ? -1.0 : 0.0 };
    }
    // arm direction(-1=down,0=hold,1=up), intake(1=in,-1=out,0=stop)
    @Override
    public void putData(double[] data) {
    	if (data.length != 2) return;
    	double lift = data[0];
    	double intake = data[1];
    	if (lift < 0) this.down();
    	else if (lift > 0) this.up();
    	else this.stop();
    	if (intake > 0) this.intake();
    	else if (intake < 0) this.outtake();
    	else this.rollerOff();
    }
    
    @Override
    public void startFollowRecording() {
    }
    
    @Override
    public int getLength() {
    	return this.getData().length;
    }
}
