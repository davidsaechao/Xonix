/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: TimeTickets.java
  
  This file holds a TimeTickets object which is a GameObject. It has a random location. It stores amount
  of time possibly added to GameWorld clock attribute. Default color is red and color is changeable
*/

package a4.gameObjects;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Arrays;

import a4.interfaces.ICollider;
import a4.interfaces.IDrawable;
import a4.interfaces.ISelectable;
import a4.sounds.Sound;

public class TimeTickets extends GameObject implements IDrawable, ISelectable, ICollider{
	//Fixed width and height
	private final int width = 10;
	private final int height = 10;
	private int time;
	private boolean isSelected;
	private String soundFile = "Happy.wav";
	private Sound sound = new Sound(soundFile);
	
	//Used constant to calculate time ticket length for future 
	private final int TIME_CHANGE = 6;
	
	public TimeTickets(int level) {
		super.setRotation();
		super.setScale();
		super.setTranslation();
		
		//Set time ticket to random location
		Random rand = new Random();
		int iX = rand.nextInt(460) + 15;
		int iY = rand.nextInt(460) + 15;
		double fX = rand.nextDouble() + iX;
		double fY = rand.nextDouble() + iY;
		super.translate(fX,fY);
		
		//Red TimeTickets 
		super.setColor(255,0,0);
		
		//set length of time tickets based on level
		setTime(TIME_CHANGE - level);
		
	}
	
	
	//Overrides GameObject setX method to make the object fixed
	public void setX(float newX) {
		System.out.println("Object is fixed. Coordinates x cannot be changed");
	}
	
	//Overrides GameObject setY method to make the object fixed
	public void setY(float newY) {
		System.out.println("Object is fixed. Coordinates y cannot be changed");
	}
	
	//Overrides GameObject method. TimeTickets color cannot be changed
	public void setColor(int red, int green, int blue) {
		System.out.println("TimeTickets cannot have their color changed");
	}
	
	//Getter for height variable
	public int getHeight() {
		return height;
	}
	
	//Getter for width variable
	public int getWidth() {
		return width;
	}
	
	//Getter for time variable
	public int getTime() {
		return time;
	}
	
	//Setter for time variable
	private void setTime(int newTime) {
		time = newTime;
	}
	
	//Time ticket to string method to print all attributes of the time ticket
	public String toString() {
		int[] rgb = getColor();
		return "TimeTickets: loc=" + getX() + "," + getY() + " color=" + Arrays.toString(rgb) + " width=" + getWidth() + " height=" + getHeight() + " time=" + getTime();
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
		int pX = (int) p.getX(); 
		int pY = (int) p.getY();
		int tX = (int)getX(); 
		int tY = (int)getY();
		if ( (pX >= tX-width) && (pX <= tX+width)	&& (pY >= tY-height) && (pY <= tY+height) ) {
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
		g2d.setColor(Color.green);
		if (isSelected()) {
			g2d.fillRect(-width/2, -height/2, width, height);
		} else {
			g2d.drawRect(-width/2, -height/2, width, height);
		}
		
		g2d.setTransform(saveAT);
	}

	public boolean collidesWith(ICollider obj) {

		return false;
	}


	public void handleCollision(ICollider obj, boolean isSoundOn) {
		if (isSoundOn) {
			sound.play();
		}
	}
	
	
}
	