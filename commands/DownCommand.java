/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: DownCommand.java
  
  This file changes car heading to south 
*/

package a4.commands;
import javax.swing.AbstractAction;

import a4.GameWorld;

import java.awt.event.*;

@SuppressWarnings("serial")
public class DownCommand extends AbstractAction {
	private GameWorld gw;
	public DownCommand() {
		super("Down");
	}
	public void actionPerformed (ActionEvent e) {
	
		//Change heading to south
		gw.getCar().changeHeading(180);
		
		//Notify observers
		gw.notifyObservers();
	}
	public void setTarget (GameWorld g) {
		gw = g;
	}
}