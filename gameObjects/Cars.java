/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: Cars.java
  
  This file holds a Car object which is a MovableGameObject with default attributes. The Car object's color cannot be changed
  and implements a steerable interface allowing other objects to change its heading attribute.
*/

package a4.gameObjects;
import a4.interfaces.ICollider;
import a4.interfaces.IDrawable;
import a4.interfaces.ISteerable;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.Random;

//Car is a movable game object and is steerable
public class Cars extends MovableGameObject implements ISteerable, IDrawable, ICollider {
	//Attributes of width and height of the car
	private int width;
	private int height;
	
	public Cars() {
		
		super.setRotation();
		super.setScale();
		super.setTranslation();
		
		
		super.translate(247.5, 7.5);
		//Start location of a car
		
		Random rand = new Random();
		//Red car 
		super.setColor(255,0,0);
		
		//Initialize car speed between 30 and 70
		int inSpeed = rand.nextInt(40) + 30;
		setSpeed(inSpeed);
		
		//Initialize car heading to north
		setHeading(0);
		
		//Make sure width and height of car can fit in field square
		setWidth(10);
		setHeight(10);
		

	}
	
	//Override abstract move. Can have negative values and move offscreen
	public void move(int time) {
		double elapsed = time;
		double dist = getSpeed() * (elapsed/1000);
		double cos = Math.cos(Math.toRadians(90-getHeading()));
		double sin = Math.sin(Math.toRadians(90-getHeading()));
		double deltaX = Math.round( 10.0*(cos * dist)/10.0);
		double deltaY = Math.round( 10.0*(sin * dist)/10.0);
		super.translate(deltaX, deltaY);

	}
	
	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		
		g2d.transform(getTranslation());
		g2d.transform(getScale());
		g2d.transform(getRotation());
		
		Polygon p = new Polygon();
		p.addPoint(-width, -height/2);
		p.addPoint( -width/2, height);
		p.addPoint( width/2, height);
		p.addPoint(width,-height/2);
		g2d.fillPolygon(p);
		
		g2d.setTransform(saveAT);
	}
	
	public void restart() {
		super.resetTransform();
		super.translate(247.5, 7.5);
		setHeading(0);
	}
	
	//Overrides GameObject method. Cars color cannot be changed
	public void setColor(int red, int green, int blue) {
		System.out.println("Cars cannot have their color changed");
	}
	
	
	//Steerable interface method allowing other objects to change heading
	public void changeHeading(int newHeading) {
		setHeading(newHeading);
		switch (newHeading) {
			case 0:		super.resetRotation();
						break;
			case 90: 	super.resetRotation();
						super.rotate(Math.toRadians(270));
					 	break;
			case 180: 	super.resetRotation();
						super.rotate(Math.toRadians(180));
					  	break;
			case 270: 	super.resetRotation();
						super.rotate(Math.toRadians(90));
						break;
		}
	}
	
	//Getter for width variable
	public int getWidth() {
		return width;
	}
	
	//Setter for width variable
	private void setWidth(int newWidth) {
		width = newWidth;
	}
	
	//Getter for height variable
	public int getHeight() {
		return height;
	}
	
	//Setter for height variable
	private void setHeight(int newHeight) {
		height = newHeight;
	}
	
	//Car to string method for easy printing of the car's attributes
	public String toString() {
		int[] rgb = super.getColor();
		return "Car: loc=" + getX() + "," + getY() + " color=" + Arrays.toString(rgb) + " speed=" + getSpeed() + 
			" heading=" + getHeading() + " width=" + getWidth() + " height=" + getHeight();
	}

	@Override
	public boolean collidesWith(ICollider obj) {
		
		double carX = getX();
		double carY = getY();
		double carX1 = carX-(((double)width)/2);
		double carY1 = carY-(((double)height)/2);
		double carX2 = carX+(((double)width)/2);
		double carY2 = carY+(((double)height)/2);
		
		if (obj instanceof TimeTickets) {
			double tWidth = ((TimeTickets)obj).getWidth();
			double tHeight = ((TimeTickets)obj).getHeight();
			double tX1 =  ( ((TimeTickets)obj).getX() - (tWidth/2) ); 
			double tY1 =  ( ((TimeTickets)obj).getY() - (tHeight/2) );
			double tX2 =  ( ((TimeTickets)obj).getX() + (tWidth/2) ); 
			double tY2 =  ( ((TimeTickets)obj).getY() + (tHeight/2) );
			if ( (carX1 < tX2) && (tX1 < carX2)	&& (carY1 < tY2) && (tY1 < carY2) ) {
				return true;
			}	else	{
				return false;
			}
		} else if (obj instanceof SmartBomb) {
			double sX1 = ((SmartBomb)obj).getX() - ((SmartBomb)obj).getSize();
			double sY1 = ((SmartBomb)obj).getY() - ((SmartBomb)obj).getSize();
			double sX2 = ((SmartBomb)obj).getX() + ((SmartBomb)obj).getSize();
			double sY2 = ((SmartBomb)obj).getY() + ((SmartBomb)obj).getSize();
			
			if ( (carX1 < sX2) && (sX1 < carX2) && (carY1 < sY2) && (sY1 < carY2) ) {
				return true;
			} else {
				return false;
			}
			
			
		} else if (obj instanceof MonsterBalls) {
			int radius = ((MonsterBalls)obj).getRadius();
			double mX = ((MonsterBalls)obj).getX();
			double mY = ((MonsterBalls)obj).getY();
			double distX = (mX-carX);
			double distY = (mY-carY);
			double distBetween = (distX*distX)+(distY*distY);
			double radSqr = (radius*radius);
			if (distBetween<=radSqr) {
				return true;
			} else {
				return false;
			}
		} else if (obj instanceof Sweeper) {
			
		}

		return false;
	}

	@Override
	public void handleCollision(ICollider obj, boolean isSoundOn) {
		if (obj instanceof TimeTickets) {
			
		} else {
			
			super.resetTransform();
			//Collided with a MonsterBall or SmartBomb
			super.translate(247.5,7.5);
			Random rand = new Random();
			
			//Initialize car speed between 30 and 70
			int inSpeed = rand.nextInt(40) + 30;
			setSpeed(inSpeed);
			
			//Initialize car heading to north
			setHeading(0);
	
		}
		
	}



}
	