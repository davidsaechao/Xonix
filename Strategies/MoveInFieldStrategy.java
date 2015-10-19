/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: MoveInFieldStrategy.java
  
  This file holds MoveInFieldStrategy where objects can only move within field
*/

package a4.Strategies;

import a4.gameObjects.SmartBomb;
import a4.interfaces.IStrategy;

public class MoveInFieldStrategy implements IStrategy {
	private SmartBomb smartBomb;
	public MoveInFieldStrategy(SmartBomb newB){
		smartBomb = newB;
	}
	
	//Move within field only. Check if its within field in SwitchStrategyCommand.java
	public void apply(int time) {
		float elapsed = time;
		float dist = smartBomb.getSpeed() * (elapsed/1000);
		double cos = Math.cos(Math.toRadians(90-smartBomb.getHeading()));
		double sin = Math.sin(Math.toRadians(90-smartBomb.getHeading()));
		double deltaX = cos*dist;
		double deltaY = sin*dist;
		double newX = smartBomb.getX() + deltaX;
		double newY = smartBomb.getY() + deltaY;
		int testY = 180-smartBomb.getHeading();
		int testX = smartBomb.getHeading()-180;
		if (testY <= 0) {
			testY = testY + 360;
		} 
		if (testX <= 0) {
			testX = smartBomb.getHeading()+270;
		} else {
			testX = smartBomb.getHeading()-90;
		}
		if ( (newY >= 495) || (newY <= 5) ) {
			smartBomb.setHeading(testY);
			sin = Math.sin(Math.toRadians(90-testY));
			deltaY = sin*dist;
			smartBomb.translate(deltaX, deltaY);
		} else if ( (newX >= 495) || (newX <= 5) ) {
			smartBomb.setHeading(testX);
			cos = Math.cos(Math.toRadians(90-testX));
			deltaX = cos*dist;
			smartBomb.translate(deltaX, deltaY);
		} else {
			smartBomb.translate(deltaX, deltaY);
		}

	}
	
	public String toString() {
		return "MoveInFieldStrategy";
	}


}
