/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: SpaceCommand.java
  
  This file outputs to console space command was pressed
*/

package a4.commands;
import javax.swing.AbstractAction;

import java.awt.event.*;

@SuppressWarnings("serial")
public class SpaceCommand extends AbstractAction {
	public SpaceCommand() {
		super("Space");
	}
	public void actionPerformed (ActionEvent e) {
		System.out.println("Space command requested from " + e.getActionCommand()
		+ " " + e.getSource().getClass() );
	}
}