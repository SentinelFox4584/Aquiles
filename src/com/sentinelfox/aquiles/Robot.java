
package com.sentinelfox.aquiles;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends IterativeRobot {
	
	Joystick stick;
	Talon right;
	Talon left;
	Talon lifter;
	double vel;
	Timer time;
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	stick = new Joystick(0);
    	right = new Talon(1);
    	left = new Talon(0);
    	lifter = new Talon(2);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    }
    
    public void teleopInit() {
    	vel = 0;
    	time = new Timer();
    	time.start();
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
        right.set(stick.getRawAxis(3) * .5);
        left.set(stick.getRawAxis(1) * -0.5);
        if(stick.getRawButton(2)) {
        	lifter.set(1);
        } else if(stick.getRawButton(4)) {
        	lifter.set(-1);
        } else {
        	lifter.set(0);
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
