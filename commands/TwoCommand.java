/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: TwoCommand.java
  
  This file increments total squares owned by 1
*/

package a4.commands;
import javax.swing.AbstractAction;

import a4.GameWorld;

import java.awt.event.*;

@SuppressWarnings("serial")
public class TwoCommand extends AbstractAction {
	private GameWorld gw;
	public TwoCommand() {
		super("Car Owns Square");
	}
	public void actionPerformed (ActionEvent e) {
		
		System.out.println("The car has driven over one square and now owns the square");
		
		//Increment total squares by 1 and update current percentage of squares owned
		gw.setOwnSquares(gw.getOwnSquares() + 1);
		float percent = (float)((gw.getOwnSquares() * 100)/gw.getTotalSquares());
		gw.setCurrentScore((int)percent);
		
		//Check if current percentage of owned squares is greater than or equal to minimum score to advance level
		if (gw.getCurrentScore() >= gw.getMinScoreToLevel()) {
			gw.restart();
		}
		gw.notifyObservers();
	}
	public void setTarget (GameWorld g) {
		gw = g;
	}
}