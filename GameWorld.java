/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: GameWorld.java
  
  This file constructs a GameWorld object with default attributes. It also constructs a car with its default 
  attributes. This file manipulates a collection of GameObjects and GameWorld data states. 
*/

package a4;
import a4.GameObjectCollection;
import a4.gameObjects.Cars;
import a4.gameObjects.FieldSquares;
import a4.gameObjects.GameObject;
import a4.interfaces.IGameWorld;
import a4.interfaces.IObservable;
import a4.interfaces.IObserver;
import a4.interfaces.Iterator;
import a4.sounds.Sound;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GameWorld implements IObservable, IGameWorld{
	private String soundFile = "Background.wav";
	private Sound background = new Sound(soundFile);
	private int totalSquares = 10000;
	private int borderSquares = 396;
	//Length and width of the entire field 500x500 default
	private final float BORDERS_X = (float)497.5;
	private final float BORDERS_Y = (float)2.5;
	
	//ownSquares indicates how many squares are owned by user
	private int ownSquares;
	
	//Default level, clock, lives, current score, minimum score, sound, car
	//gameObjectCollection and observerList when creating game world
	private int level = 1;
	private int clock = 30;
	private int lives = 3;
	private int currentScore = 0;
	private int minScoreToLevel = 50;
	
	private boolean isPaused;
	private boolean sound;
	private Cars car;
	private GameObjectCollection myGameObjectList;
	private ArrayList<IObserver> myObserverList = new ArrayList<IObserver>();
	
	//Construct game world with default values and create a new car to be added onto array list
	public GameWorld() {
		setLevel(level);
		setClock(clock);
		setLives(lives);
		setCurrentScore(currentScore);
		setMinScoreToLevel(minScoreToLevel);
		myGameObjectList = new GameObjectCollection();
		
		//Create a car and add it onto array list of game objects
		car = new Cars();
		myGameObjectList.add(car);
		
		FieldSquares iFs = new FieldSquares();
		double fs_size = iFs.getSize();
		for (double x = 2.5; x <= BORDERS_X; x+=fs_size ) {
			for (double y = 2.5; y <= BORDERS_X; y+=fs_size) {
				if ( (x == BORDERS_X) || (x == BORDERS_Y) ||
					 (y == BORDERS_X) || (y == BORDERS_Y) ) {
					FieldSquares fs = new FieldSquares(x,y);
					myGameObjectList.add(fs);
				}
			}
		}
		ownSquares = borderSquares;
	}
	
	//Getter for GameObjectCollection
	public GameObjectCollection getCollection() {
		return myGameObjectList;
	}
	
	//Getter for observer list
	public ArrayList<IObserver> getObserverList() {
		return myObserverList;
	}
	
	//Getter for car
	public Cars getCar() {
		return car;
	}
	
	//Add observer to list
	public void addObserver (IObserver obs) {
		myObserverList.add(obs);
	}
	
	//Notify Observers
	public void notifyObservers() {
		GameWorldProxy proxy = new GameWorldProxy(this);
		for (IObserver o : myObserverList) {
			o.update((IObservable)proxy, null);
		}
	}
	
	//Method call to check lives
	public void checkLives() {
		//Check if have any more lives
		if (getLives() == 0) {
			JOptionPane.showMessageDialog(null,"Game Over! You have no more lives.",
			"Game Over", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		} 
		
	}
	
	//Restart changes the level and re-initialize game world when advancing to next level
	public void advance() {
	
		//Advance to next level. Reset current score to 0 and owned squares to 0. Set new minimum score to reach.
		setLevel(level+1);
		setCurrentScore(0);
		setOwnSquares(0);
		setMinScoreToLevel(10 + getMinScoreToLevel());
		System.out.println("You advanced to level " + getLevel());
		
		Iterator iter = myGameObjectList.getIterator();
		while (iter.hasNext()) {
			myGameObjectList.remove(iter.getNext());
			iter.goBack();
		}
		
		car = new Cars();
		myGameObjectList.add(car);
		setClock(30);
		//Rebuild field
		FieldSquares iFs = new FieldSquares();
		float fs_size = iFs.getSize();
		for (float x = (float)2.5; x <= BORDERS_X; x+=fs_size ) {
			for (float y = (float)2.5; y <= BORDERS_X; y+=fs_size) {
				if ( (x == BORDERS_X) || (x == BORDERS_Y) ||
					 (y == BORDERS_X) || (y == BORDERS_Y) ) {
					FieldSquares fs = new FieldSquares(x,y);
					myGameObjectList.add(fs);
				}
			}
		} 
		
	}
	
	public void restart() {
		
		Iterator iter = myGameObjectList.getIterator();
		while (iter.hasNext()) {
			GameObject fs = (GameObject)iter.getNext();
			if (fs instanceof FieldSquares) {
				boolean owned = ((FieldSquares)fs).isOwned();
				if (!owned) {
					myGameObjectList.remove(fs);
					iter.goBack();
				}
			}
		}
		
		car.restart();
		setClock(30);
		
	}
	
	//Get total Squares
	public int getTotalSquares() {
		return totalSquares;
	}
	
	//Getter for ownSquares variable
	public int getOwnSquares() {
		return ownSquares;
	}
	
	//Setter for ownSquares variable
	public void setOwnSquares(int newOwnSquares) {
		ownSquares = newOwnSquares;
	}
	
	//Getter for level variable
	public int getLevel() {
		return level;
	}
	
	//Setter for getter variable
	public void setLevel(int newLevel) {
		level = newLevel;
	}
	
	//Getter for clock variable
	public int getClock() {
		return clock;
	}
	
	//Setter for clock variable
	public void setClock(int newClock) {
		clock = newClock;
	}
	
	//Getter for lives variable
	public int getLives() {
		return lives;
	}
	
	//Setter for lives variable
	public void setLives(int newLives) {
		lives = newLives;
	}
	
	//Getter for currentScore variable
	public int getCurrentScore() {
		return currentScore;
	}
	
	//Setter for currentScore variable
	public void setCurrentScore(int newCurrentScore) {
		currentScore = newCurrentScore;
	}
	
	//Getter for minScoreToLevel variable
	public int getMinScoreToLevel() {
		return minScoreToLevel;
	}
	
	//Setter for minScoreToLevel variable
	public void setMinScoreToLevel(int newMinScoreToLevel) {
		minScoreToLevel = newMinScoreToLevel;
	}
	
	//Getter for sound variable
	public String getSound() {
		if (sound) {
			return "ON";
		} else {
			return "OFF";
		}
	}
	
	//Change sound variable
	public void changeSound(boolean yesNo) {
		sound = yesNo;
	}
	
	public boolean isSoundOn () {
		return sound;
	}
	
	public boolean isPaused () {
		return isPaused;
	}
	
	public void setPaused(boolean yesNo) {
		isPaused = yesNo;
	}
	
	public float getBordersX() {
		return BORDERS_X;
	}
	
	public float getBordersY() {
		return BORDERS_Y;
	}
	
	public void play() {
		if (sound) {
			background.loop();
		} else {
			background.stop();
		}
	}
	
	public void stop() {
		background.stop();
	}
	
}