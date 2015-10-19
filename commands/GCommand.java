/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: GCommand.java
  
  This file tells user that it now owns the surrounded group of squares
*/

package a4.commands;
import javax.swing.AbstractAction;

import a4.GameWorld;

import java.awt.event.*;
import java.util.Random;

@SuppressWarnings("serial")
public class GCommand extends AbstractAction {
	private GameWorld gw;
	public GCommand() {
		super("G");
	}
	public void actionPerformed (ActionEvent e) {
		
		//Check if all squares are owned
		if (gw.getTotalSquares() < gw.getCurrentScore()) {
			System.out.println("You own all the squares");
			System.exit(0);
		}
		
		//Generate random number between 1 and maximum number of non-owned squares and set new current score and print it 
		Random rand = new Random();
		int newScore = rand.nextInt(gw.getTotalSquares() - gw.getOwnSquares() + 1);
		gw.setOwnSquares(gw.getOwnSquares()+newScore);
		float percent = (float)((gw.getOwnSquares()*100)/gw.getTotalSquares());
		gw.setCurrentScore((int)percent);
		System.out.println("The car has surrounded a group of squares and now owns them all. Your current score: " + gw.getCurrentScore() + "%");
		
		//Check if current percentage of owned squares is greater than or equal to minimum score to advance level
		if (gw.getCurrentScore() >= gw.getMinScoreToLevel()) {
			gw.advance();
		}
		
		//Notify Observers
		gw.notifyObservers();
	}
	public void setTarget (GameWorld g) {
		gw = g;
	}
}