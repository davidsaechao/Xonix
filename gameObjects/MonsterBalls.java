/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: MonsterBalls.java
  
  This file holds a MonsterBalls object which is a MovableGameObject with default random attributes
*/

package a4.gameObjects;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;
import java.util.Arrays;

import a4.interfaces.ICollider;
import a4.interfaces.IDrawable;
import a4.sounds.Sound;

public class MonsterBalls extends MovableGameObject implements IDrawable, ICollider{
	private int radius;
	private String soundFile = "Crunch.wav";
	private Sound sound = new Sound(soundFile);
	
	public MonsterBalls() {
		
		super.setRotation();
		super.setScale();
		super.setTranslation();
		
		Random rand = new Random();
		//Get random color
		int red = rand.nextInt(256);		
		int green = rand.nextInt(256);
		int blue = rand.nextInt(256);
		setColor(red,green,blue);
		
		
		//Get random location, speed, heading and radius
		//Get a random float within the 500x500 field 
		setRadius(rand.nextInt(20) + 10);
		int iX = rand.nextInt(400) + getRadius();
		int iY = rand.nextInt(400) + getRadius();
		double fX = rand.nextDouble() + iX;
		double fY = rand.nextDouble() + iY;
		
		super.translate(fX,fY);
		super.setSpeed(rand.nextInt(20) + 30); 
		super.setHeading(rand.nextInt(361));
	}
	public void move(int time) {
		double elapsed = time;
		double dist = super.getSpeed() * (elapsed/1000);
		double cos = Math.cos(Math.toRadians(90-super.getHeading()));
		double sin = Math.sin(Math.toRadians(90-super.getHeading()));
		double deltaX =  (cos * dist);
		double deltaY = (sin*dist);
		double newX = super.getX() + deltaX;
		double newY = super.getY() + deltaY;
		int testY = 180-getHeading();
		int testX = getHeading()-180;
		if (testY <= 0) {
			testY = testY + 360;
		} 
		if (testX <= 0) {
			testX = getHeading()+270;
		} else {
			testX = getHeading()-90;
		}
		if ( (newY >= 495) || (newY <= 5) ) {
			setHeading(testY);
			sin = Math.sin(Math.toRadians(90-testY));
			deltaY = sin * dist;
			newY = getY() + deltaY;
			super.translate(deltaX,deltaY);
		} else if ( (newX >= 495) || (newX <= 5) ) {
			setHeading(testX);
			cos = Math.cos(Math.toRadians(90-testX));
			deltaX = cos * dist;
			newX = getX() + deltaX;
			super.translate(deltaX,deltaY);
		} else {
			super.translate(deltaX,deltaY);
		}
	
	}
	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.transform(getTranslation());
		g2d.transform(getScale());
		g2d.transform(getRotation());
		
		int[] c = getColor();
		Color color = new Color(c[0],c[1],c[2]);
		g2d.setColor(color);
		g2d.fillOval(0, 0, getRadius(), getRadius());
		
		g2d.setTransform(saveAT);
	}
	

	
	//Getter for radius variable
	public int getRadius() {
		return radius;
	}
	
	//Setter for radius variable
	private void setRadius(int newRadius) {
		radius = newRadius;
	}
	
	//toString method to print out all attributes of monster ball
	public String toString() {
		int[] rgb = super.getColor();
		return "Ball: loc=" + getX() + "," + getY() + " color=" + Arrays.toString(rgb) + " speed=" + getSpeed() + 
			" heading=" + getHeading() + " radius=" + getRadius();
	}
	@Override
	public boolean collidesWith(ICollider obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//Car collided with MonsterBall change color
	public void handleCollision(ICollider obj, boolean isSoundOn) {
		Random rand = new Random();
		int red = rand.nextInt(256);		
		int green = rand.nextInt(256);
		int blue = rand.nextInt(256);
		setColor(red,green,blue);
		if (isSoundOn) {
			sound.play();
		}
		
	}
	
}
	