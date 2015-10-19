/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: Game.java
  
  This file holds a GameWorld object and constructs a GameWorld object. Then it calls a play method which
  continually asks user for a command and calls a method from the GameWorld object corresponding to the user input
  while the user has lives from the GameWorld. 
*/
package a4;

import java.awt.*; 

import javax.swing.*;
import javax.swing.border.*;

import a4.observers.*;
import a4.commands.*;

@SuppressWarnings("serial")
public class Game extends JFrame {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private final int WIDTH = 1000;
	private final int HEIGHT = 800;
	
	public Game() {
		super("My Xonix Game");
		gw = new GameWorld();
		GameWorldProxy prox = new GameWorldProxy(gw);
		mv = new MapView(prox);
		sv = new ScoreView(gw);
		gw.addObserver(mv); // register map observer
		gw.addObserver(sv); // register score observer
		
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout(new BorderLayout());
		//Create menu bar
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		//Create file menu
		JMenu fileMenu = new JMenu("File");
		bar.add(fileMenu);
		//Create command menu
		JMenu commandMenu = new JMenu("Commands");
		bar.add(commandMenu);
		
		//Create an instance of each command
		NewCommand newCommand = new NewCommand();
		SaveCommand saveCommand = new SaveCommand();
		UndoCommand undoCommand = new UndoCommand();
		SoundCommand soundCommand = new SoundCommand();
		AboutCommand aboutCommand = new AboutCommand();
		QuitCommand quitCommand = new QuitCommand();
		BCommand bCommand = new BCommand();
		KCommand kCommand = new KCommand();
		GCommand gCommand = new GCommand();
		UpCommand upCommand = new UpCommand();
		RightCommand rightCommand = new RightCommand();
		DownCommand downCommand = new DownCommand();
		LeftCommand leftCommand = new LeftCommand();
		IncreaseSpeedCommand iCommand = new IncreaseSpeedCommand();
		DecreaseSpeedCommand lCommand = new DecreaseSpeedCommand();
		PausePlayCommand pausePlayCommand = new PausePlayCommand();
		DeleteCommand deleteCommand = new DeleteCommand();
		AddSmartBombCommand addSmartBombCommand = new AddSmartBombCommand();

		
		//Set command that needs targets. Set targets to game world
		soundCommand.setTarget(gw);
		bCommand.setTarget(gw);
		kCommand.setTarget(gw);
		gCommand.setTarget(gw);
		upCommand.setTarget(prox);
		rightCommand.setTarget(gw);
		downCommand.setTarget(gw);
		leftCommand.setTarget(gw);
		iCommand.setTarget(gw);
		lCommand.setTarget(gw);
		addSmartBombCommand.setTarget(gw);
		
		//Create file menu items
		JMenuItem fileNew = new JMenuItem("New");
		JMenuItem fileSave = new JMenuItem("Save");
		JMenuItem fileUndo = new JMenuItem("Undo");
		JCheckBoxMenuItem fileSound = new JCheckBoxMenuItem("Sound");
		JMenuItem fileAbout = new JMenuItem("About");
		JMenuItem fileQuit = new JMenuItem("Quit");
		
		//Create command menu items
		JMenuItem commandB = new JMenuItem("B Command");
		JMenuItem commandK = new JMenuItem("K Command");
		JMenuItem commandG = new JMenuItem("G Command");
		JMenuItem commandAddSmartBomb = new JMenuItem("Add SmartBomb");
		
		//Add file menu items to file menu
		fileMenu.add(fileNew);
		fileMenu.add(fileSave);
		fileMenu.add(fileUndo);
		fileMenu.add(fileSound);
		fileMenu.add(fileAbout);
		fileMenu.add(fileQuit);
		
		//Add command menu items to command menu
		commandMenu.add(commandB);
		commandMenu.add(commandK);
		commandMenu.add(commandG);
		commandMenu.add(commandAddSmartBomb);
		
		//Set file menu items to file menu commands
		fileNew.setAction(newCommand);
		fileSave.setAction(saveCommand);
		fileUndo.setAction(undoCommand);
		fileSound.setAction(soundCommand);
		fileAbout.setAction(aboutCommand);
		fileQuit.setAction(quitCommand);
		
		//Set command menu items to command menu commands
		commandB.setAction(bCommand);
		commandK.setAction(kCommand);
		commandG.setAction(gCommand);
		commandAddSmartBomb.setAction(addSmartBombCommand);
		
		//Control panel with the command buttons
		JPanel controlPanel = new JPanel(new GridLayout(15, 1));
		controlPanel.setBorder(new TitledBorder("Commands:"));
		//Create Buttons
		JButton pausePlayButton = new JButton("Pause");
		JButton deleteButton = new JButton("Delete");
		JButton quitButton = new JButton("Quit");
		
		deleteCommand.setTarget(gw);
		pausePlayCommand.setTarget(prox, pausePlayButton,deleteCommand, mv.getTimer());
		
		//Add command buttons to control panel
		controlPanel.add(pausePlayButton);
		controlPanel.add(deleteButton);
		controlPanel.add(quitButton);
		
		//Set commands buttons to action commands
		pausePlayButton.setAction(pausePlayCommand);
		deleteButton.setAction(deleteCommand);
		quitButton.setAction(quitCommand);
		
		
		//Key binding
		InputMap imap = mv.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		KeyStroke upKey = KeyStroke.getKeyStroke("UP");
		KeyStroke rightKey = KeyStroke.getKeyStroke("RIGHT");
		KeyStroke downKey = KeyStroke.getKeyStroke("DOWN");
		KeyStroke leftKey = KeyStroke.getKeyStroke("LEFT");
		KeyStroke iKey = KeyStroke.getKeyStroke('i');
		KeyStroke lKey = KeyStroke.getKeyStroke('l');
		imap.put(upKey, "North");
		imap.put(rightKey, "East");
		imap.put(downKey, "South");
		imap.put(leftKey, "West");
		imap.put(iKey, "Increase Speed");
	  	imap.put(lKey, "Decrease Speed");
		ActionMap amap = mv.getActionMap();
		amap.put("North", upCommand);
		amap.put("East", rightCommand);
		amap.put("South", downCommand);
		amap.put("West", leftCommand);
		amap.put("Increase Speed", iCommand);
		amap.put("Decrease Speed", lCommand);
		mv.requestFocus();
		
		getContentPane().add(sv, BorderLayout.NORTH);
		getContentPane().add(mv, BorderLayout.CENTER);
		getContentPane().add(controlPanel, BorderLayout.WEST);
		
		setVisible(true);
	}	
	
}