/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: SaveCommand.java
  
  This file outputs to console save command was pressed
*/

package a4.commands;
import javax.swing.AbstractAction;

import java.awt.event.*;

@SuppressWarnings("serial")
public class SaveCommand extends AbstractAction {
	public SaveCommand() {
		super("Save");
	}
	public void actionPerformed (ActionEvent e) {
		System.out.println("Save command requested from " + e.getActionCommand()
		+ " " + e.getSource().getClass() );
	}
}