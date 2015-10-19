/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: SoundCommand.java
  
  This file changes sound to on or off
*/

package a4.commands;
import javax.swing.AbstractAction;

import a4.GameWorld;

import java.awt.event.*;

@SuppressWarnings("serial")
public class SoundCommand extends AbstractAction {
	private GameWorld gw;
	public SoundCommand() {
		super("Sound");
	}
	public void actionPerformed (ActionEvent e) {
		
		if (gw.isSoundOn()) {
			gw.changeSound(false);
		} else {
			gw.changeSound(true);
		}
		
		if (gw.isPaused()) {
			gw.stop();
		} else {
			gw.play();
		}
		
		//Notify Observers
		gw.notifyObservers();
	}
	public void setTarget(GameWorld g) {
		gw = g;
	}
}