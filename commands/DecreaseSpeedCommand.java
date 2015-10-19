/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: DecreaseSpeedCommand.java
  
  This file decrease car's speed by 1
*/

package a4.commands;
import javax.swing.AbstractAction;

import a4.GameWorld;

import java.awt.event.*;

@SuppressWarnings("serial")
public class DecreaseSpeedCommand extends AbstractAction {
	private GameWorld gw;
	public DecreaseSpeedCommand() {
		super("Decrease Speed");
	}
	public void actionPerformed (ActionEvent e) {
		
		//Check if the new speed will be below zero. If not set new car speed
		int newSpeed = gw.getCar().getSpeed()-1;
		if (newSpeed >= 0) {
			gw.getCar().setSpeed(newSpeed);
		} else {
			System.out.println("Speed cannot be decreased below zero");
		}	
		
		//Notify observers
		gw.notifyObservers();
	}
	public void setTarget (GameWorld g) {
		gw = g;
	}
}