/*Name: David Saechao
  Assignment #2
  Class: CSC 133
  File: AboutCommand.java
  
  This file holds the about command printing name, course and version 
*/

package a4.commands;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class AboutCommand extends AbstractAction {
	public AboutCommand() {
		super("About");
	}
	public void actionPerformed (ActionEvent e) {

		//Shows message with name, course and version
		JOptionPane.showMessageDialog(null,"Name: David Saechao\nCourse: CSC 133\nVersion: 1",
		"About", JOptionPane.INFORMATION_MESSAGE);
	}
}