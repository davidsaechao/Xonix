/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: QuitCommand.java
  
  This file asks user if they are sure they want to exit
*/

package a4.commands;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;

import java.awt.event.*;

@SuppressWarnings("serial")
public class QuitCommand extends AbstractAction {
	public QuitCommand() {
		super("Quit");
	}
	public void actionPerformed (ActionEvent e) {
		
		//Check if user is sure they want to exit
		int result = JOptionPane.showConfirmDialog
		(null, "Are you sure you want to exit ?", "Confirm Exit",
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		return; 
	}
}