package a4.gameObjects;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import a4.interfaces.IDrawable;

public class Part extends GameObject implements IDrawable {
	private int pX = 1;
	private int pY = 2;
	private boolean isSelected;
	private Point top, botL, botR;
	public Part() {
		top = new Point(0,pY);
		botL = new Point(-pX,-pY);
		botR = new Point(pX,-pY);
		super.setTranslation();
		super.setRotation();
		super.setScale();
	}
	@Override
	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.transform(getRotation());
		g2d.transform(getScale());
		g2d.transform(getTranslation());
		
		g2d.setColor(super.getColorO());
		Polygon p = new Polygon();
		p.addPoint(botR.x, botR.y);
		p.addPoint(top.x, top.y);
		p.addPoint(botL.x, botL.y);
		
		if (isSelected) {
			g2d.fillPolygon(p);
		} else {
			g2d.drawPolygon(p);
		}
		g2d.setTransform(saveAT);

	}
	
	public void setSelected(boolean yesNo) {
		isSelected = yesNo;
		
	}
	
	

}
