package a4.observers;
import a4.interfaces.IObserver;
import a4.interfaces.IObservable;

import java.awt.*; 

import javax.swing.*;
import javax.swing.border.*;

import a4.GameWorld;
import a4.GameWorldProxy;

@SuppressWarnings("serial")
public class ScoreView extends JPanel implements IObserver {
	private JLabel currentLevel;
	private JLabel remainingTime;
	private JLabel livesLeft;
	private JLabel currentScore;
	private JLabel minScoreToLevel;
	private JLabel sound;
	public ScoreView(GameWorld gw) {
		
		//Set default score view JLabels
		setLayout(new GridLayout(1, 6));
		currentLevel = new JLabel("Current Level: " + gw.getLevel(), JLabel.CENTER);
		remainingTime = new JLabel("Remaining Time: " + gw.getClock(), JLabel.CENTER);
		livesLeft = new JLabel("Lives Left: " + gw.getLives(), JLabel.CENTER);
		currentScore = new JLabel("Current Score: " + gw.getCurrentScore(), JLabel.CENTER);
		minScoreToLevel = new JLabel("Required Score: " + gw.getMinScoreToLevel(), JLabel.CENTER);
		sound = new JLabel("Sound: " + gw.getSound(), JLabel.CENTER);
		
		//Add JLabels to components
		super.add(currentLevel);
		super.add(remainingTime);
		super.add(livesLeft);
		super.add(currentScore);
		super.add(minScoreToLevel);
		super.add(sound);
		super.setBorder(new LineBorder(Color.blue,2));
	}
	public void update(IObservable obs, Object obj) {
		//Update the score view based on GameWorld changes
		GameWorldProxy gw = (GameWorldProxy)obs;
		currentLevel.setText("Current Level: " + gw.getLevel());
		remainingTime.setText("Remaining Time: " + gw.getClock());
		livesLeft.setText("Lives Left: " + gw.getLives());
		currentScore.setText("Current Score: " + gw.getCurrentScore());
		minScoreToLevel.setText("Required Score: " + gw.getMinScoreToLevel());
		sound.setText("Sound: " + gw.getSound());
	
	}
}