/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: RightCommand.java
  
  This file changes car's heading to east
*/

package a4.commands;
import javax.swing.AbstractAction;

import a4.GameWorld;

import java.awt.event.*;

@SuppressWarnings("serial")
public class RightCommand extends AbstractAction {
	private GameWorld gw;
	public RightCommand() {
		super("Right");
	}
	public void actionPerformed (ActionEvent e) {
		
		//Change heading to east
		gw.getCar().changeHeading(90);
		
		//Notify observers
		gw.notifyObservers();
	}
	public void setTarget (GameWorld g) {
		gw = g;
	}
}