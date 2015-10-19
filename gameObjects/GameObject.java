/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: GameObject.java
  
  This file holds a GameObject which is an abstract class with common attributes and methods for all 
  objects which are GameObject
*/

package a4.gameObjects;
import java.awt.Color;
import java.awt.geom.AffineTransform;


public abstract class GameObject {
	//Attributes of all game objects: x and y coordinates and a color
	private Color color;
	private AffineTransform myRotation, myTranslation, myScale;
	
	public void rotate (double radians) {
		myRotation.rotate (radians);
	}
	
	public void translate (double dx, double dy) {
		myTranslation.translate(dx,dy);
	}
	
	public double getX() {
		return myTranslation.getTranslateX();
	}
	
	public double getY() {
		return myTranslation.getTranslateY();
	}
	
	public void scale (double dx, double dy) {
		myScale.scale(dx, dy);
	}
	
	public void resetTransform() {
		myRotation.setToIdentity();
		myTranslation.setToIdentity();
	}
	
	public void resetRotation() {
		myRotation.setToIdentity();
	}
	
	public AffineTransform getRotation() {
		return myRotation;
	}
	
	public AffineTransform getTranslation() {
		return myTranslation;
	}
	
	public AffineTransform getScale() {
		return myScale;
	}
	
	public void setRotation() {
		myRotation = new AffineTransform();
	}
	
	public void setTranslation() {
		myTranslation = new AffineTransform();
	}
	
	public void setScale() {
		myScale = new AffineTransform();
	}
	
	//Return the int values of the RGB in an array 
	public int[] getColor() {
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		int[] rgb = { red, green, blue };
		return rgb;
	}
	
	public Color getColorO () {
		return color;
	}
	
	//Set intial color of game objects based on RGB values
	public void setColor(int red, int green, int blue) {
		this.color = new Color(red,green,blue);
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
}