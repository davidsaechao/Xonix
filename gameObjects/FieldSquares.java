/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: FieldSquares.java
  
  This file holds a FieldSquares object which is a GameObject. It has a fixed size and
  default color blue.
*/

package a4.gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Arrays;

import a4.interfaces.IDrawable;

public class FieldSquares extends GameObject implements IDrawable{
	//Fixed size of field squares
	private final int size = 5;
	private boolean owned = true;
	
	//Constructor used for getting field squares size
	public FieldSquares() {
		
	}
	
	public FieldSquares(double x, double y) {

		super.setRotation();
		super.setScale();
		super.setTranslation();
		
		super.translate(x, y);
		
		//Initial color of squares is blue
		setColor(0,0,255);
	}
	
	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		
		g2d.transform(getTranslation());
		g2d.transform(getScale());
		g2d.transform(getRotation());
		if (owned) {
			g2d.setColor(Color.blue);
		} else {
			g2d.setColor(Color.gray);
		}
		g2d.fillRect(0, 0, size, size);
		g2d.setColor(Color.black);
		g2d.drawRect(0, 0, size, size);
		g2d.setTransform(saveAT);
		
	}
	
	public String toString() {
		int[] rgb = super.getColor();
		return "FieldSquares: loc=" + getX() + "," + getY() + " color=" + Arrays.toString(rgb);
	}
	
	//Get size of field squares
	public int getSize() {
		return size;
	}
	
	public boolean isOwned() {
		return owned;
	}
	
	public void setOwned(boolean yesNo) {
		owned = yesNo;
	}
	
}