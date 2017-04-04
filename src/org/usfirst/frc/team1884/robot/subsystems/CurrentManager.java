package org.usfirst.frc.team1884.robot.subsystems;

import org.usfirst.frc.team1884.robot.commands.CurrentManagerCommand;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CurrentManager extends Subsystem {

    private PowerDistributionPanel pdp;
    
    public CurrentManager() {
    	pdp = new PowerDistributionPanel();
    }
    
    public double getTotalCurrent() {
    	return pdp.getTotalCurrent();
    }

    public boolean isExceedMax() {
    	return pdp.getTotalCurrent() > 120.0;
    }
    
    public boolean isVoltageTooLow() {
    	return pdp.getVoltage() < 8.5;
    }
    
    public boolean isVoltageHighEnough() {
    	return pdp.getVoltage() > 10.0;
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new CurrentManagerCommand());
    }
}

