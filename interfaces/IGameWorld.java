/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: IGameWorld.java
  
  This file holds the IGameWorld interface holding methods of gameWorld
*/

package a4.interfaces;

import a4.gameObjects.Cars;
import a4.GameObjectCollection;

public interface IGameWorld {
	//Method call to return collection
	public GameObjectCollection getCollection();
	
	//Method call to check for lives
	public void checkLives();
	
	public void advance();
	
	//Restart changes the level and re-initialize game world when advancing to next level
	public void restart();
	
	//Getter for car
	public Cars getCar();
	
	//Getter for total Squares
	public int getTotalSquares();
	
	//Getter for ownSquares variable
	public int getOwnSquares();
	
	//Setter for ownSquares variable
	public void setOwnSquares(int newOwnSquares);
	
	//Getter for level variable
	public int getLevel();
	
	//Setter for getter variable
	public void setLevel(int newLevel);
	
	//Getter for clock variable
	public int getClock();
	
	//Setter for clock variable
	public void setClock(int newClock);
	
	//Getter for lives variable
	public int getLives();
	
	//Setter for lives variable
	public void setLives(int newLives);
	
	//Getter for currentScore variable
	public int getCurrentScore();
	
	//Setter for currentScore variable
	public void setCurrentScore(int newCurrentScore);
	
	//Getter for minScoreToLevel variable
	public int getMinScoreToLevel();
	
	//Setter for minScoreToLevel variable
	public void setMinScoreToLevel(int newMinScoreToLevel);
	
	//Getter for sound variable
	public String getSound();
	
	//Change sound variable
	public void changeSound(boolean yesNo);
	
	
	public boolean isSoundOn ();
	
	public boolean isPaused ();
	
	public void setPaused(boolean yesNo);
	
	public float getBordersX() ;
	
	public float getBordersY();
	
	public void play();
	
	public void stop();
}