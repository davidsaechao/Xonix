/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: GameObjectCollection.java
  
  This file implements the collection/iterator of game objects 
*/

package a4;

import a4.gameObjects.GameObject;
import a4.interfaces.Collection;
import a4.interfaces.Iterator;

import java.util.ArrayList;

public class GameObjectCollection implements Collection {
	private ArrayList<GameObject> collection;
	
	public GameObjectCollection () {
		collection = new ArrayList<GameObject>();
	}
	
	public void add(GameObject newGameObject) {
		collection.add(newGameObject);
	}
	
	public void remove(GameObject remGameObject) {
		collection.remove(remGameObject);
	}
	
	public Iterator getIterator () {
		return new GameObjectIterator();
	}
	
	private class GameObjectIterator implements Iterator {
		private int index;
		public GameObjectIterator () {
			index = -1;
		}
		//Check if has next
		public boolean hasNext () {
			if (collection.size() <= 0) {
		  		return false;
			}
			if (index >= collection.size()-1) {
				return false;
			} else {
				return true;
			}
		}
		public GameObject getNext () {
			index++;
			return(collection.get(index));
		}
		public void goBack() {
			index--;
		}
		
		public boolean isLast() {
			if (index < collection.size()-1) {
				return false;
			} else {
				return true;
			}
		}
	}
}
