package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.users.RegisteredUser;
import windows.ChangeCardWindow;
import windows.IntroduceCardWindow;

public class IntroduceCardController implements ActionListener {
	
	private ChangeCardWindow previousWindow;
	private IntroduceCardWindow window;
	private RegisteredUser user;

	public IntroduceCardController(ChangeCardWindow window, IntroduceCardWindow newWindow, RegisteredUser selectedUser) {
		this.window = newWindow;
		this.user = selectedUser;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String newNumber = window.getNumber();
		
		user.changeCreditCard(newNumber);
		
		this.window.setVisible(false);
		this.previousWindow.setVisible(true);
	}

}
