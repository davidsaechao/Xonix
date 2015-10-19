package a4.observers;
import a4.gameObjects.*;
import a4.interfaces.*;
import a4.Strategies.*;

import java.awt.*; 
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import javax.swing.*;
import javax.swing.border.*;

import a4.GameWorldProxy;

@SuppressWarnings("serial")
public class MapView extends JPanel implements IObserver, MouseListener, MouseMotionListener, MouseWheelListener, ActionListener {
	private GameWorldProxy gw;
	private Timer timer;
	private final int DELAY_IN_MSEC = 20;
	private int howManyToOneSec = 1000/DELAY_IN_MSEC;
	private int tenSec = 10;
	private double[] squaresLocations = new double[98];
	private FieldSquares fs;
	private int fromX, fromY;
	private int toX, toY;
	private double winBottom = 0;
	private double winLeft = 0;
	private double winRight = 503;
	private double winTop = 503;
	private AffineTransform worldToND, ndToScreen, inverseVTM, theVTM;
	public MapView(GameWorldProxy prox){
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		super.setBorder(new LineBorder(Color.black,2));
		int i = 0;
		for (double j = 7.5; j <= 492.5; j+=5) {
			squaresLocations[i] = j;
			i++;
		}
		gw=prox;
		timer = new Timer(DELAY_IN_MSEC, this);
		timer.start();
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public void update (IObservable obs, Object obj) {
		gw = (GameWorldProxy)obs;
		this.repaint();
	}
	
	public void addSweeper() {
		Sweeper b = new Sweeper();
		b.setTarget(gw);
		b.setTimeStart(gw.getClock()/2);
		gw.getCollection().add(b);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform saveAT = g2d.getTransform();
		worldToND = buildWorldToNDXform(winTop, winBottom, winRight, winLeft);
		ndToScreen = buildNDToScreenXform(this.getWidth(),this.getHeight());
		theVTM = (AffineTransform)ndToScreen.clone();
		theVTM.concatenate(worldToND);
		g2d.transform(theVTM);	
		Iterator theGameObjects = gw.getCollection().getIterator();
		while ( theGameObjects.hasNext() ) {
			GameObject gameObj = (GameObject) theGameObjects.getNext();	
			if (gameObj instanceof IDrawable) {
				((IDrawable) gameObj).draw(g2d);
			}
		}
		
		g2d.setTransform(saveAT);
		
	}
	

	private AffineTransform buildWorldToNDXform(double winTop2, double winBottom2,
			double winRight2, double winLeft2) {
		AffineTransform worldToND = new AffineTransform();
		worldToND.scale(1.0/winRight2, 1.0/winTop2);
		worldToND.translate( -winLeft2, -winBottom2);
		return worldToND;
	}
	
	private AffineTransform buildNDToScreenXform(double width, double height) {
		AffineTransform ndToScreen = new AffineTransform();
		ndToScreen.translate(0.0, this.getHeight());
		ndToScreen.scale(this.getWidth(), -this.getHeight());
		return ndToScreen;
	}
	
	public void mouseDragged(MouseEvent e) {
		if (timer.isRunning()) {
			toX = e.getX();
			toY = e.getY();
			int xShift = toX - fromX;
			int yShift = toY - fromY;
			fromX = toX;
			fromY = toY;
			winBottom += yShift;
			winLeft += xShift;
			this.repaint();
		}
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void zoomIn() {
		double h =  winTop - winBottom;
		double w = winRight - winLeft;
		winLeft += w*0.05;
		winRight -= w*0.05;
		winTop -= h*0.05;
		winBottom += h*0.05;
		this.repaint();
	}
	
	public void zoomOut() {
		double h = winBottom - winTop;
		double w = winLeft- winRight;
		winLeft += w*0.05;
		winRight -= w*0.05;
		winTop -= h*0.05;
		winBottom += h*0.05;
		this.repaint();
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (timer.isRunning()) {
			int count = e.getWheelRotation();
			if (count < 0) {
				zoomIn();
			} else {
				zoomOut();
			}
		}
      }

	public void mouseClicked(MouseEvent e) {
		if (!timer.isRunning()) {
			try {
				inverseVTM = theVTM.createInverse();
				} catch (NoninvertibleTransformException er) {
			}

			Point2D mouseScreenLoc = e.getPoint();
			Point2D mouseWorldLoc = inverseVTM.transform(mouseScreenLoc,null);
			if (e.isShiftDown()) {
				addSweeper();
			}
			Iterator theGameObjects = gw.getCollection().getIterator();
			while (theGameObjects.hasNext()) {
				GameObject gameObj = (GameObject) theGameObjects.getNext() ;
				if (gameObj instanceof ISelectable) {
					if (e.isControlDown()) {
						if ( ((ISelectable)gameObj).contains(mouseWorldLoc) ) {
							((ISelectable) gameObj).setSelected(true);
						}
					} else {
						if ( ((ISelectable)gameObj).contains(mouseWorldLoc) ) {
							((ISelectable) gameObj).setSelected(true);
						} else {
							((ISelectable) gameObj).setSelected(false);
						}
					}
				}
			}
			this.repaint();
		} else {
			fromX = e.getX();
			fromY = e.getY();
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		if (!timer.isRunning()) {
			try {
				inverseVTM = theVTM.createInverse();
				} catch (NoninvertibleTransformException er) {
			}
			Point2D mouseScreenLoc = e.getPoint();
			Point2D mouseWorldLoc =  inverseVTM.transform(mouseScreenLoc,null);
			Iterator theGameObjects = gw.getCollection().getIterator();
			while (theGameObjects.hasNext()) {
				GameObject gameObj = (GameObject) theGameObjects.getNext() ;
				if (gameObj instanceof ISelectable) {
					if (e.isControlDown()) {
						if ( ((ISelectable)gameObj).contains(mouseWorldLoc) ) {
							((ISelectable) gameObj).setSelected(true);
						}
					} else {
						if ( ((ISelectable)gameObj).contains(mouseWorldLoc) ) {
							((ISelectable) gameObj).setSelected(true);
							this.repaint();
						} else {
							((ISelectable) gameObj).setSelected(false);
						}
					}
				}
			}
			this.repaint();
		} else {
			fromX = e.getX();
			fromY = e.getY();
		}
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveObjects() {
		Iterator theGameObjects = gw.getCollection().getIterator();
		while (theGameObjects.hasNext()) {
			GameObject gameObj = (GameObject) theGameObjects.getNext();
			if (gameObj instanceof IMovable) {
				if (gameObj instanceof Cars) {
					((IMovable)gameObj).move(DELAY_IN_MSEC);
					double newX = gameObj.getX();
					double newY = gameObj.getY();
					for (int x = 0; x < squaresLocations.length; x++) {
						for (int y = 0; y < squaresLocations.length; y++) {
							if ( (newX-2.5 < squaresLocations[x]) && (newX+2.5 >= squaresLocations[x]) &&
								 (newY-2.5 < squaresLocations[y]) && (newY+2.5 >= squaresLocations[y]) ) {
								fs = new FieldSquares(squaresLocations[x],squaresLocations[y]);
								fs.setOwned(false);
								gw.getCollection().add(fs);
							}
						}
					}
					
					
					Iterator iter = gw.getCollection().getIterator();
					while (iter.hasNext()) {
						GameObject isFs = (GameObject) iter.getNext();
						if (isFs instanceof FieldSquares) {
							if ( (newX > gw.getBordersX()) || (newX < gw.getBordersY()) ||
								 (newY > gw.getBordersX()) || (newY < gw.getBordersY()) ) {
								if ( ((FieldSquares)isFs).isOwned() == false) {
									((FieldSquares)isFs).setOwned(true);
									gw.setOwnSquares(gw.getOwnSquares()+1);
									float percent = (float)((gw.getOwnSquares() * 100)/gw.getTotalSquares());
									gw.setCurrentScore((int)percent);
									
									//Check if current percentage of owned squares is greater than or equal to minimum score to advance level
									if (gw.getCurrentScore() >= gw.getMinScoreToLevel()) {
										gw.advance();
										gw.notifyObservers();
									}
								}
							} else if ( (isFs.getX() == fs.getX()) && (isFs.getY() == fs.getY())  
									&& (iter.isLast() == false) ) {
								gw.getCollection().remove(fs);
							}
						}
					}
					gw.notifyObservers();
					
				} else {
					//Movable object is found and typecast and tell the movable object to move
					((IMovable)gameObj).move(DELAY_IN_MSEC);
					
					
					//Sweeper check its time
					if (gameObj instanceof Sweeper) {
						if ( gw.getClock() == ((Sweeper)gameObj).getTimeStart() ) {
							gw.getCollection().remove(gameObj);
						}
					}
					gw.notifyObservers();
				}
			} 
		}
	}
	
	public void collisionCarDetection() {
		Iterator theGameObjects = gw.getCollection().getIterator();
		while (theGameObjects.hasNext()) {
			GameObject gameObj = (GameObject) theGameObjects.getNext();
			if (gameObj instanceof Cars) {
				gameObj = (Cars) gameObj; 
				Iterator iter2 = gw.getCollection().getIterator();
				while (iter2.hasNext()) {
					GameObject gameObj2 = (GameObject) iter2.getNext();
					if ( (gameObj2 instanceof ICollider) && ( !(gameObj2 instanceof Cars) ) ) {
						
						//If car collided with something
						if ( ((Cars)gameObj).collidesWith( ((ICollider)gameObj2) ) ) {
							( (Cars)gameObj).handleCollision( (ICollider)gameObj2, gw.isSoundOn() );
							( (ICollider) gameObj2).handleCollision( (ICollider)gameObj, gw.isSoundOn() );
							
							//If car collided with a SmartBomb or TimeTicket remove the SmartBomb or TimeTicket
							if ( gameObj2 instanceof SmartBomb ) {							
								gw.getCollection().remove(gameObj2);
								theGameObjects.goBack();
								//Decrement a life
								gw.setLives(gw.getLives()-1);
								//Reposition car to starting point
								gw.restart();
								//Notify observers
								gw.notifyObservers();
								//Check if have any more lives
								gw.checkLives();
							} else if ( gameObj2 instanceof TimeTickets ) {
								gw.getCollection().remove(gameObj2);
								theGameObjects.goBack();
								int add = ((TimeTickets)gameObj2).getTime();
								gw.setClock(gw.getClock() + add);
								gw.notifyObservers();
							} else {
								//Decrement a life
								gw.setLives(gw.getLives()-1);
								//Reset clock
								gw.restart();
								//Notify observers
								gw.notifyObservers();
								//Check if have any more lives
								gw.checkLives();
							}
							gw.notifyObservers();
						}
					}
					
				}
			}
		}
	}
	
	public void collisionSweeperDetection() {
		Iterator theGameObjects = gw.getCollection().getIterator();
		while (theGameObjects.hasNext()) {
			GameObject gameObj = (GameObject) theGameObjects.getNext();
			if (gameObj instanceof Sweeper) {
				gameObj = (Sweeper) gameObj; 
				Iterator iter2 = gw.getCollection().getIterator();
				while (iter2.hasNext()) {
					GameObject gameObj2 = (GameObject) iter2.getNext();
					if ( (gameObj2 instanceof ICollider) && ( !(gameObj2 instanceof Sweeper) ) ) {
						
						//If sweeper collided with something
						if ( ((Sweeper)gameObj).collidesWith( ((ICollider)gameObj2) ) ) {
							( (Sweeper)gameObj).handleCollision( (ICollider)gameObj2, gw.isSoundOn() );
							( (ICollider) gameObj2).handleCollision( (ICollider)gameObj, gw.isSoundOn() );
							
							//If sweeper collided with a car
							if ( gameObj2 instanceof Cars ) {							
								//Decrement a life
								gw.setLives(gw.getLives()-1);
								//Reposition car to starting point
								gw.restart();
								//Notify observers
								gw.notifyObservers();
								//Check if have any more lives
								gw.checkLives();
							} else {
								gw.getCollection().remove(gameObj2);
								theGameObjects.goBack();
								//Notify observers
								gw.notifyObservers();
							}
							gw.notifyObservers();
						}
					}
					
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		this.repaint();
		moveObjects();
		Iterator iter = gw.getCollection().getIterator();
		while (iter.hasNext()) {
			GameObject gameObj = (GameObject) iter.getNext();
			if (gameObj instanceof Cars) {
				collisionCarDetection();
			}
			if (gameObj instanceof Sweeper) {
				collisionSweeperDetection();
			}
				
		}

		howManyToOneSec--;
		
		if (howManyToOneSec == 0) {
			howManyToOneSec = 1000/DELAY_IN_MSEC;
			
			//Decrement clock and check if reach zero
			gw.setClock(gw.getClock()-1);
			if(gw.getClock() == 0) {
				Iterator theGameObjects = gw.getCollection().getIterator();
				while (theGameObjects.hasNext()) {
					GameObject gameObj = (GameObject) theGameObjects.getNext();
					if (gameObj instanceof FieldSquares) {
						if ( ((FieldSquares)gameObj).isOwned() == false ) {
							gw.getCollection().remove(gameObj);
							theGameObjects.goBack();
						}
					}
				}
				
				//Decrement a life
				gw.setLives(gw.getLives()-1);
				
				//Reset clock
				gw.setClock(30);
				
				//Reposition car to starting point
				gw.getCar().restart();
				
				//Notify observers
				gw.notifyObservers();
					
				//Check if have any more lives
				gw.checkLives();
			} else {
				//Notify observers
				gw.notifyObservers();
			}
			
			tenSec--;
			
			if (tenSec == 0) {
				tenSec = 10;
				Iterator theGameObjects = gw.getCollection().getIterator();
				while (theGameObjects.hasNext()) {
					GameObject gameObj = (GameObject) theGameObjects.getNext() ;
					if (gameObj instanceof SmartBomb) {
					
						//When a smart bomb is found in the game world collection of game objects
						SmartBomb smartBomb = (SmartBomb) gameObj;
							
						//Switch between two strategies: MoveInField and ChasesCar
						if (smartBomb.getStrategy() instanceof MoveInFieldStrategy) {
								
							//Change to ChaseCarStrategy
							smartBomb.setStrategy(new ChaseCarStrategy(smartBomb,gw));
							System.out.println("Strategy changed to " +
									smartBomb.getStrategy() );
							

								
						} else if (smartBomb.getStrategy() instanceof ChaseCarStrategy) {
								
							//Change to MoveInFieldStrategy
							smartBomb.setStrategy(new MoveInFieldStrategy(smartBomb));
							System.out.println("Strategy changed to " +
										smartBomb.getStrategy());
									

						}
			
					}
				}
				addSweeper();
				
			}
		}
	}

	
}