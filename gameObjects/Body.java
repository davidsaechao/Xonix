package a4.gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import a4.interfaces.IDrawable;

public class Body extends GameObject implements IDrawable{
	private int radius;
	private boolean isSelected;
	public Body () {
		radius = 2;
		super.setColor(Color.yellow);
		super.setTranslation();
		super.setRotation();
		super.setScale();
	}
	
	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		
		g2d.transform(getTranslation());
		g2d.transform(getRotation());
		g2d.transform(getScale());
		
		g2d.setColor(super.getColorO());
		if (isSelected) {
			g2d.fillOval(-getRadius(), -getRadius(), getRadius()*2, getRadius()*2);
		} else {
			g2d.drawOval(-getRadius(), -getRadius(), getRadius()*2, getRadius()*2);
		}
		
		g2d.setTransform(saveAT);
		

	}
	private int getRadius() {
		return radius;
	}
	public void setSelected(boolean yesNo) {
		isSelected = yesNo;
		
	}
	
}
