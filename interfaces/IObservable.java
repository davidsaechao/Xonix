/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: IObservable.java
  
  This file is a observable interface 
*/

package a4.interfaces;

public interface IObservable {
	public void addObserver (IObserver obs);
	public void notifyObservers();
}