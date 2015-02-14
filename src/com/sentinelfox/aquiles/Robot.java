package com.sentinelfox.aquiles;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends IterativeRobot {
	
	private static final int JOYSTICK_PORT = 0;
	private static final int RIGHT_CHASSIS_TALON = 1;
	private static final int LEFT_CHASSIS_TALON = 0;
	private static final int LIFTER_TALON = 2;
	private static final int LEFT_JOYSTICK_AXIS = 1;
	private static final int RIGHT_JOYSTICK_AXIS = 3;
	private static final int UP_JOYSTICK_BUTTON = 6;
	private static final int DOWN_JOYSTICK_BUTTON = 5;


	Joystick stick;
	Talon right;
	Talon left;
	Talon lifter;
	double rightVel, leftVel, rightVLimit, leftVLimit;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		stick = new Joystick(JOYSTICK_PORT);
		right = new Talon(RIGHT_CHASSIS_TALON);
		left = new Talon(LEFT_CHASSIS_TALON);
		lifter = new Talon(LIFTER_TALON);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called when operator control starts.
	 */
	public void teleopInit() {
		rightVLimit = 0;
		leftVLimit = 0;
		rightVel = 0;
		leftVel = 0;
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		
		driveUpdate();
		lifterUpdate();

	}
	
	/**
	 * This function is called periodically during operator control to update the lifter system.
	 */
	private void lifterUpdate() {
		if (stick.getRawButton(DOWN_JOYSTICK_BUTTON)) {
			lifter.set(1);
		} else if (stick.getRawButton(UP_JOYSTICK_BUTTON)) {
			lifter.set(-1);
		} else {
			lifter.set(0);
		}
	}
	
	/**
	 * This function is called periodically during operator control to update the drive system.
	 */
	private void driveUpdate() {
		rightVLimit = Math.round(stick.getRawAxis(RIGHT_JOYSTICK_AXIS) * 100.0) / 100.0;
		leftVLimit = Math.round(stick.getRawAxis(LEFT_JOYSTICK_AXIS) * 100.0) / 100.0;
		if(rightVel < rightVLimit) {
			rightVel += 0.04;
		} else if(rightVel > rightVLimit) {
			rightVel -= 0.04;
		}
		
		if(leftVel < leftVLimit) {
			leftVel += 0.04;
		} else if(leftVel > leftVLimit) {
			leftVel -= 0.04;
		}
		
		right.set(rightVel);
		left.set(leftVel * -1);
		
		if(stick.getPOV() != -1) {
			int pov = stick.getPOV();
			if(pov == 315 || pov == 0 || pov ==45) {
				right.set(-.2);
				left.set(.2);
			} else if(pov == 225 || pov == 180 || pov == 135) {
				right.set(.2);
				left.set(-.2);
			} else if(pov == 270) {
				right.set(-.2);
				left.set(-.2);
			} else if(pov == 90) {
				right.set(.2);
				left.set(.2);
			}
		}
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
