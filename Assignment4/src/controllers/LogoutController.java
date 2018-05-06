package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import application.App;
import windows.LoginWindow;

public class LogoutController implements ActionListener {

	private JFrame window;
	private App app;

	public LogoutController(App app, JFrame window) {
		this.window = window;
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		app.logout();
		app.closeApp();
		
		app = App.openApp();
		LoginWindow newWindow2 = new LoginWindow();
		newWindow2.setController(new LoginController(this.app, newWindow2));
		this.window.setVisible(false);
		newWindow2.setVisible(true);
	}

}
