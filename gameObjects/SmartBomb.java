/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: SmartBomb.java
  
  This file holds a SmartBomb object which is a MovableGameObject
  with default random attributes
*/

package a4.gameObjects;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Arrays;

import a4.GameWorld;
import a4.Strategies.MoveInFieldStrategy;
import a4.interfaces.ICollider;
import a4.interfaces.IDrawable;
import a4.interfaces.ISelectable;
import a4.interfaces.IStrategy;
import a4.sounds.Sound;

public class SmartBomb extends MovableGameObject implements IDrawable, ISelectable, ICollider {
	private int size = 10;
	private boolean isSelected;
	private String soundFile = "Explosion.wav";
	private Sound sound = new Sound(soundFile);
	
	private Body body = new Body();
	private Part[] part;
	
	//Set default strategy to move within field
	protected IStrategy curStrategy = new MoveInFieldStrategy(this);
	
	public SmartBomb() {
		super.setRotation();
		super.setScale();
		super.setTranslation();
		
		body = new Body();
		part = new Part[4];
		for (int i = 0; i < part.length; i++) {
			part[i] = new Part();
		}

		part[0].translate(0, 4);
		part[0].setColor(Color.red);
		

		part[1].translate(0,7);
		part[1].rotate(Math.toRadians(270));
		part[1].setColor(Color.red);
		
		part[2].translate(0, 4);
		part[2].rotate(Math.toRadians(180));
		part[2].setColor(Color.red);
		
		part[3].translate(0, 7);
		part[3].rotate(Math.toRadians(90));
		part[3].setColor(Color.red);

		
				
		Random rand = new Random();
		//Get random color
		int red = rand.nextInt(256);		
		int green = rand.nextInt(256);
		int blue = rand.nextInt(256);
		setColor(red,green,blue);
		
		//Get random location, speed, heading and radius
		//Get a random double within the 500x500 field 
		int iX = rand.nextInt(449) + 20;
		int iY = rand.nextInt(449) + 20;
		double fX = rand.nextDouble() + iX;
		double fY = rand.nextDouble() + iY;
		fX = Math.round( 10.0*fX/10.0);
		fY = Math.round( 10.0*fY/10.0);
		super.translate(fX, fY);
		super.setSpeed(rand.nextInt(20) + 20); 
		super.setHeading(rand.nextInt(361));
	}
	
	public void move(int time) {
		invokeStrategy(time);
	
	}
	
	public int getSize() {
		return size;
	}
	
	public Body getBody() {
		return body;
	}
	
	public Part[] getPart() {
		return part;
	}
	
	
	//toString method to print out all attributes of smartBomb
	public String toString() {
		int[] rgb = super.getColor();
		return "SmartBomb: loc=" + getX() + "," + getY() + " color=" + Arrays.toString(rgb) + " speed=" + getSpeed() + 
			" heading=" + getHeading();
	}
	
	//Check if smart bomb is within field
	public boolean isInField() {
		double deltaX = (double)(Math.cos(90-getHeading()) * getSpeed());
		double deltaY = (double)(Math.sin(90-getHeading()) * getSpeed());
		double newX = getX() + deltaX;
		double newY = getY() + deltaY;
		
		//Check if its within field
		int fieldSize = new GameWorld().getTotalSquares();
		if ((fieldSize >= newX) && (fieldSize >= newY)) {
			return true;
		} else {
			return false;
		}
	}
	
	//Set strategies for smart bomb object
	public IStrategy getStrategy() {
		return curStrategy;
	}
	
	//Set strategies for smart bomb object
	public void setStrategy(IStrategy s) {
		curStrategy = s;
	}
	
	//Call apply to do the actual strategy
	public void invokeStrategy(int time) {
		curStrategy.apply(time);
		
	}
	
	@Override
	public void setSelected(boolean yesNo) {
		isSelected = yesNo;
		
	}

	@Override
	public boolean isSelected() {
		return isSelected;
	}

	@Override
	public boolean contains(Point2D p) {
		double pX =  p.getX(); 
		double pY =  p.getY();
		double tX = getX(); 
		double tY = getY();
		if ( (pX >= tX-size) && (pX <= tX+size)	&& (pY >= tY-size) && (pY <= tY+size) ) {
			return true;
		}	else	{
			return false;
		}
	}
	
	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.transform(getTranslation());
		g2d.transform(getScale());
		g2d.transform(getRotation());
		
		body.setSelected(isSelected);
		for (Part p: part) {
			p.setSelected(isSelected);
		}
		
		body.draw(g2d);
		for (Part p: part) {
			p.draw(g2d);
		}
		
		g2d.setTransform(saveAT);
	}

	@Override
	public boolean collidesWith(ICollider obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleCollision(ICollider obj, boolean isSoundOn) {
		if (isSoundOn) {
			sound.play();
		}
	}

}
	