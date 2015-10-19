package a4.gameObjects;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;

import a4.GameWorldProxy;
import a4.interfaces.ICollider;
import a4.interfaces.IDrawable;


public class Sweeper extends MovableGameObject implements IDrawable, ICollider{
	private Points[] p;
	Points [] left;
	Points [] right;
	private int timeStart;
	private GameWorldProxy gw;
	public Sweeper() {
		super.setRotation();
		super.setScale();
		super.setTranslation();
		
		Random rand = new Random();
		p = new Points[4];
		int x0 = rand.nextInt(200)+20;
		int x1 = rand.nextInt(200)+20;
		int x2 = rand.nextInt(200)+20;
		int x3 = rand.nextInt(200)+20;
		double compX = rand.nextDouble()+x0;
		double compX1 = rand.nextDouble()+x1;
		double compX2 = rand.nextDouble()+x2;
		double compX3 = rand.nextDouble()+x3;
		
		p[0] = new Points(compX,compX);
		p[1] = new Points(compX1,compX1);
		p[2] = new Points(compX2,compX1/2);
		p[3] = new Points(compX3,compX/2);
		
		int red = rand.nextInt(256);		
		int green = rand.nextInt(256);
		int blue = rand.nextInt(256);
		setColor(red,green,blue);
	
		
		super.setSpeed(rand.nextInt(20) + 30); 
		super.setHeading(rand.nextInt(361));
	}
	
	public void setTarget(GameWorldProxy g) {
		gw = g;
	}
	
	public int getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(int time) {
		timeStart = time;
	}
	
	public void move(int time) {
		double elapsed = time;
		double dist = getSpeed() * (elapsed/1000);
		double cos = Math.cos(Math.toRadians(90-getHeading()));
		double sin = Math.sin(Math.toRadians(90-getHeading()));
		double deltaX = cos * dist;
		double deltaY = sin * dist;
		for (int i=0;i<p.length;i++) {
			p[i].translate(deltaX, deltaY);
		}
	}
	
	
	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();

		g2d.transform(getTranslation());
		g2d.transform(getScale());
		g2d.transform(getRotation());
		
		
		g2d.setColor(getColorO());
		drawBezierCurve(p,g2d,0);

		g2d.setTransform(saveAT);
	}

	private void drawBezierCurve(Points [] points, Graphics2D g2d, int next) {
		Points [] left = new Points[4];
		Points [] right = new Points[4];
		this.left = left;
		this.right = right;
		if (next > 10) {
			Points p1 = points[0];
			Points p2 = points[3];
			int p1X = (int)p1.getX();
			int p1Y = (int)p1.getY();
			int p2X = (int)p2.getX();
			int p2Y = (int)p2.getY();
			g2d.drawLine(p1X,p1Y,p2X,p2Y);
		}	else	{
			subdivideCurve(points, left, right);
			drawBezierCurve(left,g2d,next+1);
			drawBezierCurve(right,g2d,next+1);
		}
	}

	private void subdivideCurve(Points [] A, Points [] B, Points [] C) {
		int x;
		int y;

		x = (int)A[0].getX();
		y = (int)A[0].getY();
		B[0] = new Points(x,y);

		x = (int)(A[0].getX()+A[1].getX())/2;
		y = (int)(A[0].getY()+A[1].getY())/2;
		B[1] = new Points(x,y);

		x = (int)((B[1].getX()/2)+((A[1].getX()+A[2].getX())/4));
		y = (int)((B[1].getY()/2)+((A[1].getY()+A[2].getY())/4));
		B[2] = new Points(x,y);

		x = (int)A[3].getX();
		y = (int)A[3].getY();
		C[3] = new Points(x,y);

		x = (int)(A[2].getX()+A[3].getX())/2;
		y = (int)(A[2].getY()+A[3].getY())/2;
		C[2] = new Points(x,y);

		x = (int)(((A[1].getX()+A[2].getX())/4)+(C[2].getX()/2));
		y = (int)(((A[1].getY()+A[2].getY())/4)+(C[2].getY()/2));
		C[1] = new Points(x,y);

		x = (int)(B[2].getX()+C[1].getX())/2;
		y = (int)(B[2].getY()+C[1].getY())/2;
		B[3] = new Points(x,y);

		x = (int)B[3].getX();
		y = (int)B[3].getY();
		C[0] = new Points(x,y);
	}

	@Override
	public boolean collidesWith(ICollider obj) {
		if (obj instanceof TimeTickets) {
			double tWidth = ((TimeTickets)obj).getWidth();
			double tHeight = ((TimeTickets)obj).getHeight();
			double tX1 =  ( ((TimeTickets)obj).getX() - (tWidth/2) ); 
			double tY1 =  ( ((TimeTickets)obj).getY() - (tHeight/2) );
			double tX2 =  ( ((TimeTickets)obj).getX() + (tWidth/2) ); 
			double tY2 =  ( ((TimeTickets)obj).getY() + (tHeight/2) );
			if ( (p[3].getX() < tX2) && (tX1 < p[1].getX())	&& (p[2].getY() < tY2) && (tY1 < p[3].getY()) ) {
				return true;
			}	else	{
				return false;
			}
		} else if (obj instanceof SmartBomb) {
			double sX1 = ((SmartBomb)obj).getX() - ((SmartBomb)obj).getSize();
			double sY1 = ((SmartBomb)obj).getY() - ((SmartBomb)obj).getSize();
			double sX2 = ((SmartBomb)obj).getX() + ((SmartBomb)obj).getSize();
			double sY2 = ((SmartBomb)obj).getY() + ((SmartBomb)obj).getSize();
			
			if ( (p[0].getX() < sX2) && (sX1 < p[3].getX()) && (p[1].getY() < sY2) && (sY1 < p[2].getY()) ) {
				return true;
			} else {
				return false;
			}
			
			
		} else if (obj instanceof MonsterBalls) {
			int radius = ((MonsterBalls)obj).getRadius();
			double mX = ((MonsterBalls)obj).getX();
			double mY = ((MonsterBalls)obj).getY();
			double distX = (mX-p[0].getX());
			double distY = (mY-p[0].getY());
			double distBetween = (distX*distX)+(distY*distY);
			double radSqr = (radius*radius);
			if (distBetween<=radSqr) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public void handleCollision(ICollider obj, boolean isSoundOn) {
		if (obj instanceof Cars) {
			
		} else {
			gw.getCollection().remove((GameObject)obj);
		}
		
	}

}