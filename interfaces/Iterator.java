/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: Iterator.java
  
  This file holds an Iterator interface 
*/

package a4.interfaces;

import a4.gameObjects.GameObject;

public interface Iterator {
	public boolean hasNext();
	public GameObject getNext();
	public void goBack();
	public boolean isLast();
}