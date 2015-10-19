/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: KCommand.java
  
  This file adds new time ticket randomly onto the gameWorld. 
*/

package a4.commands;
import javax.swing.AbstractAction;

import a4.GameWorld;
import a4.gameObjects.TimeTickets;

import java.awt.event.*;

@SuppressWarnings("serial")
public class KCommand extends AbstractAction {
	private GameWorld gw;
	public KCommand() {
		super("K");
	}
	public void actionPerformed (ActionEvent e) {
		
		//Add new time ticket randomly into gameWorld
		TimeTickets ticket = new TimeTickets(gw.getLevel());
		gw.getCollection().add(ticket);
		
		//Notify Observers
		gw.notifyObservers();
	}
	public void setTarget(GameWorld g) {
		gw = g;
	}
}