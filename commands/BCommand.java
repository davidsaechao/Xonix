/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: BCommand.java
  
  This file adds a monster ball to the gameWorld 
*/

package a4.commands;
import javax.swing.AbstractAction;

import a4.gameObjects.MonsterBalls;
import a4.GameWorld;

import java.awt.event.*;

@SuppressWarnings("serial")
public class BCommand extends AbstractAction {
	private GameWorld gw;
	public BCommand() {
		super("B");
	}
	public void actionPerformed (ActionEvent e) {
		
		//Add monster ball to gameWorld
		MonsterBalls ball = new MonsterBalls();
		gw.getCollection().add(ball);
		
		//Notify Observers
		gw.notifyObservers();
	}
	public void setTarget (GameWorld g) {
		gw = g;
	}
}