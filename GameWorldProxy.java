/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: GameWorldProxy.java
  
  This file holds the GameWorldProxy to protected observers from accessing mutators
*/

package a4;

import a4.GameObjectCollection;
import a4.gameObjects.Cars;
import a4.interfaces.IGameWorld;
import a4.interfaces.IObservable;
import a4.interfaces.IObserver;

public class GameWorldProxy implements IObservable, IGameWorld {
	private GameWorld realGameWorld;
	public GameWorldProxy (GameWorld gw) {
		realGameWorld = gw;
	}
	
	//Add an observer
	public void addObserver (IObserver obs) {
		realGameWorld.getObserverList().add(obs);
	}
	
	//Not needed
	public void notifyObservers() {
		realGameWorld.notifyObservers();
	}
	
	//Method call to tell user game over and exit game cannot be used
	public void checkLives() {
		realGameWorld.checkLives();
	}
	
	public void advance() {
		realGameWorld.advance();
	}
	
	//Cannot restart
	public void restart() {
		realGameWorld.restart();
	}
	
	//Return collection
	public GameObjectCollection getCollection() {
		return realGameWorld.getCollection();
	}
	
	//Return car
	public Cars getCar() {
		return realGameWorld.getCar();
	}
	
	//Getter for total Squares
	public int getTotalSquares() {
		return realGameWorld.getTotalSquares();
	}
	
	//Getter for ownSquares variable
	public int getOwnSquares(){
		return realGameWorld.getOwnSquares();
	}
	
	//Setter for ownSquares variable cannot be used
	public void setOwnSquares(int newOwnSquares) {
		realGameWorld.setOwnSquares(newOwnSquares);
	}
	
	//Getter for level variable
	public int getLevel() {
		return realGameWorld.getLevel();
	}
	
	//Setter for level variable cannot be used
	public void setLevel(int newLevel) {
		realGameWorld.setLevel(newLevel);
	}
	
	//Getter for clock variable
	public int getClock() {
		return realGameWorld.getClock();
	}
	
	//Setter for clock variable 
	public void setClock(int newClock) {
		realGameWorld.setClock(newClock);
	}
	
	//Getter for lives variable
	public int getLives() {
		return realGameWorld.getLives();
	}
	
	//Setter for lives variable cannot be used
	public void setLives(int newLives) {
		realGameWorld.setLives(newLives);
	}
	
	//Getter for currentScore variable
	public int getCurrentScore() {
		return realGameWorld.getCurrentScore();
	}
	
	//Setter for currentScore variable 
	public void setCurrentScore(int newCurrentScore) {
		realGameWorld.setCurrentScore(newCurrentScore);
	}
	
	//Getter for minScoreToLevel variable
	public int getMinScoreToLevel() {
		return realGameWorld.getMinScoreToLevel();
	}
	
	//Setter for minScoreToLevel variable cannot be used
	public void setMinScoreToLevel(int newMinScoreToLevel) {
		realGameWorld.setMinScoreToLevel(newMinScoreToLevel);
	}
	
	//Getter for sound variable
	public String getSound() {
		return realGameWorld.getSound();
	}
	
	public void changeSound(boolean yesNo) {
		realGameWorld.changeSound(yesNo);
	}
	
	public boolean isSoundOn () {
		return realGameWorld.isSoundOn();
	}
	
	public boolean isPaused () {
		return realGameWorld.isPaused();
	}
	
	public void setPaused(boolean yesNo) {
		realGameWorld.setPaused(yesNo);
	}
	
	public float getBordersX() {
		return realGameWorld.getBordersX();
	}
	
	public float getBordersY() {
		return realGameWorld.getBordersY();
	}
	
	public void play() {
		realGameWorld.play();
	}
	
	public void stop() {
		realGameWorld.stop();
	}

}