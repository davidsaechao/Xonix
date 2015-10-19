/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: MovableGameObject.java
  
  This file holds the abstract MovableGameObject which is a GameObject. It has common attributes for 
  MovableGameObject. It also has common methods whose object is a MoveableObject
*/

package a4.gameObjects;

import a4.interfaces.IMovable;

public abstract class MovableGameObject extends GameObject implements IMovable {
	//Attributes of movable object
	private int heading;
	private int speed;
	
	//Move the movable game object by updating their x and y coordinates
	public void move(int time) {
		
	}
	
	//Getter for speed
	public int getSpeed() {
		return speed;
	}
	
	//Setter for speed
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}
	
	//Getter for heading
	public int getHeading() {
		return heading;
	}
	
	//Setter for heading
	public void setHeading(int newHeading) {
		heading = newHeading;
	}
}