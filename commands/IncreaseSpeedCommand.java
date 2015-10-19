/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: IncreaseSpeedCommand.java
  
  This file increases car's speed by 1 
*/

package a4.commands;
import javax.swing.AbstractAction;

import a4.GameWorld;

import java.awt.event.*;

@SuppressWarnings("serial")
public class IncreaseSpeedCommand extends AbstractAction {
	private GameWorld gw;
	public IncreaseSpeedCommand() {
		super("Increase Speed");
	}
	public void actionPerformed (ActionEvent e) {
		
		//Increment speed by 1
		gw.getCar().setSpeed(gw.getCar().getSpeed()+1);
		
		//Notify observers
		gw.notifyObservers();
	}
	public void setTarget (GameWorld g) {
		gw = g;
	}
}