package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import application.App;
import windows.LoginWindow;

/**
 * Controller without an specific window. It is added to the logout button, and when prompted, logs the user out for the system
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class LogoutController implements ActionListener {

	/**
	 * Window that was visible when the button was clicked
	 */
	private JFrame window;
	/**
	 * Model for the controller. Used to invoke the logout method
	 */
	private App app;

	/**
	 * Constructor of the LogoutController
	 * 
	 * @param app Model for the controller
	 * @param window View for the controller
	 */
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
