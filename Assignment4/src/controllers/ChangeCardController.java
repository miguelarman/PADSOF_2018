package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import application.users.RegisteredUser;
import exceptions.NoRowSelectedException;
import windows.ChangeCardWindow;
import windows.IntroduceCardWindow;

public class ChangeCardController implements ActionListener {
	
	private ChangeCardWindow window;
	
	public ChangeCardController(ChangeCardWindow w) {
		this.window = w;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RegisteredUser selectedUser = null;
		try {
			selectedUser = this.window.getSelection();
		} catch (NoRowSelectedException e1) {
			JOptionPane.showMessageDialog(null, "You must select a user before clicking this button", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// Show the next window
		IntroduceCardWindow newWindow = new IntroduceCardWindow();
		IntroduceCardController c = new IntroduceCardController(this.window, newWindow, selectedUser);
		GoBackController g = new GoBackController(this.window, newWindow);
		newWindow.setController(c);
		newWindow.setGoBackController(g);
		newWindow.setVisible(true);
		this.window.setVisible(false);
		
	}
}
