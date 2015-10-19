/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: LeftCommand.java
  
  This file changes car's heading to west 
*/

package a4.commands;
import javax.swing.AbstractAction;

import a4.GameWorld;

import java.awt.event.*;

@SuppressWarnings("serial")
public class LeftCommand extends AbstractAction {
	private GameWorld gw;
	public LeftCommand() {
		super("Left");
	}
	public void actionPerformed (ActionEvent e) {
		
		//Change heading to west
		gw.getCar().changeHeading(270);
		
		//Notify observers
		gw.notifyObservers();
	}
	public void setTarget (GameWorld g) {
		gw = g;
	}
}