package a4.commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.Timer;

import a4.GameWorldProxy;
import a4.gameObjects.GameObject;
import a4.interfaces.ISelectable;
import a4.interfaces.Iterator;


@SuppressWarnings("serial")
public class PausePlayCommand extends AbstractAction{
	private GameWorldProxy gw;
	private JButton pausePlay;
	private Timer timer;
	private DeleteCommand dC;
	public PausePlayCommand() {
		super("Pause");
	}
	public void actionPerformed (ActionEvent e) {
		if (timer.isRunning()) {
			gw.stop();
			gw.setPaused(true);
			timer.stop();
			dC.setEnabled(true);
			pausePlay.setText("Play");
		} else {
			Iterator theGameObjects = gw.getCollection().getIterator();
			while (theGameObjects.hasNext()) {
				GameObject gameObj = (GameObject) theGameObjects.getNext() ;
				if (gameObj instanceof ISelectable) {
					((ISelectable) gameObj).setSelected(false);
				}
			}
			gw.setPaused(false);
			gw.play();
			timer.start();
			dC.setEnabled(false);
			pausePlay.setText("Pause");
		}	
	}
	
	public void setTarget(GameWorldProxy g, JButton cP, DeleteCommand d, Timer t) {
		gw = g;
		pausePlay = cP;
		dC = d;
		timer = t;
	}
}
