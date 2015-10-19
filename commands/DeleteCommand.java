package a4.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a4.GameWorld;
import a4.gameObjects.GameObject;
import a4.interfaces.ISelectable;
import a4.interfaces.Iterator;

@SuppressWarnings("serial")
public class DeleteCommand extends AbstractAction {
	private GameWorld gw;
	public DeleteCommand() {
		super("Delete");
		setEnabled(false);
	}
	public void actionPerformed (ActionEvent e) {
		Iterator theGameObjects = gw.getCollection().getIterator();
		while (theGameObjects.hasNext()) {
			GameObject gameObj = (GameObject) theGameObjects.getNext() ;
			if (gameObj instanceof ISelectable) {
				if ( ((ISelectable)gameObj).isSelected() ) {
					gw.getCollection().remove(gameObj);
					theGameObjects.goBack();
				}
			}
		}
		gw.notifyObservers();
		
	}
	public void setTarget (GameWorld g) {
		gw = g;
	}
}
