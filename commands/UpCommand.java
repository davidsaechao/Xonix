/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: UpCommand.java
  
  This file changes car's heading to north
*/

package a4.commands;
import javax.swing.AbstractAction;

import a4.GameWorldProxy;

import java.awt.event.*;

@SuppressWarnings("serial")
public class UpCommand extends AbstractAction {
	private GameWorldProxy gw;
	public UpCommand() {
		super("Up");
	}
	public void actionPerformed (ActionEvent e) {
		
		//Change heading to north
		gw.getCar().changeHeading(0);
		
		//Notify observers
		gw.notifyObservers();
	}
	public void setTarget (GameWorldProxy g) {
		gw = g;
	}
}	