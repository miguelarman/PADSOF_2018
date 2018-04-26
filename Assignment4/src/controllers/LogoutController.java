package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import application.App;
import windows.PersonalWindow;

public class LogoutController implements ActionListener {

	private PersonalWindow window;
	private App app;

	public LogoutController(App app, PersonalWindow window) {
		this.window = window;
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.app.logout();
		this.app.closeApp();
		
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setVisible(false);
	}

}
