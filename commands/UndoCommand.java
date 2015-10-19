/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: UndoCommand.java
  
  This file outputs to console undo command was pressed
*/

package a4.commands;
import javax.swing.AbstractAction;

import java.awt.event.*;

@SuppressWarnings("serial")
public class UndoCommand extends AbstractAction {
	public UndoCommand() {
		super("Undo");
	}
	public void actionPerformed (ActionEvent e) {
		System.out.println("Undo command requested from " + e.getActionCommand()
		+ " " + e.getSource().getClass() );
	}
}