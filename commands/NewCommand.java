/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: NewCommand.java
  
  This file outputs to console new command was pressed
*/

package a4.commands;
import javax.swing.AbstractAction;

import java.awt.event.*;

@SuppressWarnings("serial")
public class NewCommand extends AbstractAction {
	public NewCommand() {
		super("New");
	}
	public void actionPerformed (ActionEvent e) {
		System.out.println("New command requested fromm " + e.getActionCommand()
		+ " " + e.getSource().getClass() );
	}
}