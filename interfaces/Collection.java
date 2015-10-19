/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: Collection.java
  
  This file is a collection interface for use with GameObjectCollection.java
*/

package a4.interfaces;
import a4.gameObjects.GameObject;

public interface Collection {
	public void add(GameObject newObject);
	public void remove(GameObject remObject);
	public Iterator getIterator();
}
