/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: AddSmartBombCommand.java
  
  This file adds a smart bomb to the gameWorld 
*/

package a4.commands;
import javax.swing.AbstractAction;

import a4.gameObjects.SmartBomb;
import a4.GameWorld;

import java.awt.event.*;

@SuppressWarnings("serial")
public class AddSmartBombCommand extends AbstractAction {
	private GameWorld gw;
	public AddSmartBombCommand() {
		super("Add Smart Bomb");
	}
	public void actionPerformed (ActionEvent e) {
		
		//Add new smart bomb to gameWorld
		SmartBomb bomb = new SmartBomb();
		gw.getCollection().add(bomb);
		
		//Notify Observers
		gw.notifyObservers();
	}
	public void setTarget (GameWorld g) {
		gw = g;
	}
}