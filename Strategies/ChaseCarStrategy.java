
/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: ChaseCarStrategy.java
  
  This file holds ChaseCarStrategy smart bomb chases car
*/

package a4.Strategies;

import a4.GameWorldProxy;
import a4.gameObjects.Part;
import a4.gameObjects.SmartBomb;
import a4.interfaces.IStrategy;

public class ChaseCarStrategy implements IStrategy {
	private SmartBomb smartBomb;
	private GameWorldProxy gw;
	private double partDist = 0;
	private double incr = 0.5;
	private double max = 0.5;
	public ChaseCarStrategy(SmartBomb newB, GameWorldProxy g){
		smartBomb = newB;
		gw = g;
	}
	
	//Change to same heading as car and chases it 
	public void apply(int time) {
		double carX = gw.getCar().getX();
		double carY = gw.getCar().getY();
		double bombX = smartBomb.getX();
		double bombY = smartBomb.getY();
		if ( (carX-0.5 < bombX) && (carX+0.5 >= bombX) && (carY > bombY) ) {
			smartBomb.setHeading(0);
		} else if ( (carX-0.5 < bombX) && (carX+0.5 >= bombX) && (carY < bombY) ) {
			smartBomb.setHeading(180);
		} else if ( (carX > bombX) && (carY-0.5 < bombY) && (carY+0.5 >= bombY) ) {
			smartBomb.setHeading(90);
		} else if ( (carX < bombX) && (carY-0.5 < bombY) && (carY+0.5 >= bombY) ) {
			smartBomb.setHeading(270);
		} else if ( (carX > bombX) && (carY > bombY) ) {
			smartBomb.setHeading(45);
		} else if ( (carX > bombX) && (carY < bombY) ) {
			smartBomb.setHeading(135);
		} else if ( (carX < bombX) && (carY < bombY) ) {
			smartBomb.setHeading(225);
		} else if ( (carX < bombX) && (carY > bombY) ) {
			smartBomb.setHeading(315);
		}
		
		double elapsed = time;
		double dist = smartBomb.getSpeed() * (elapsed/1000);
		double cos = Math.cos(Math.toRadians(90-smartBomb.getHeading()));
		double sin = Math.sin(Math.toRadians(90-smartBomb.getHeading()));
		double deltaX = cos*dist;
		double deltaY = sin*dist;
		
		Part[] part = smartBomb.getPart();
		
		smartBomb.translate(deltaX, deltaY);
		smartBomb.rotate(Math.toRadians(1));
		
		partDist += incr;
		for (int i = 0; i < part.length; i++) {
			part[i].translate(0, partDist);
		}
		
		if (Math.abs(partDist) >= max) {
			incr *= -1;
		}

	}
	
	public String toString() {
		return "ChaseCarStrategy";
	}


}
