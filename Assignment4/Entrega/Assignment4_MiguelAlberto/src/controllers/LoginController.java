package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import application.App;
import exceptions.*;
import windows.LoginWindow;
import windows.PersonalWindow;

/**
 * Controller for LoginWindow. Waits for the user to try to log in and, then calls the login method in the app.
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class LoginController implements ActionListener {
	
	/**
	 * View field for the controller. Contains the LoginWindow
	 */
	private LoginWindow window;
	/**
	 * Model field for the controller. Contains our app in its current state
	 */
	private App app;
	
	/**
	 * Constructor of the LoginController class
	 * 
	 * @param app Model for the controller
	 * @param w View for the controller
	 */
	public LoginController(App app, LoginWindow w) {
		this.window = w;
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		PersonalWindow newWindow;

		if (arg0.getActionCommand().equals("Log in")) {

			String id = this.window.getIdField();
			String password = this.window.getPasswordField();

			try {
				this.app.login(id, password);
			} catch (UserIsBannedException e) {
				JOptionPane.showMessageDialog(null, "The user with id " + id
						+ " has been banned from the system. Please contact our Customer Service from Mondays to Fridays",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (IncorrectPasswordException | UnexistentUserException e) {
				JOptionPane.showMessageDialog(null, "Incorrect password or user. Please try again", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			} catch (AUserIsAlreadyLoggedException e) {
				JOptionPane.showMessageDialog(null, "I really don't know how you got here", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			JOptionPane.showMessageDialog(null,
					"<html>Welcome dear" + App.getLoggedUser().getName() + " " + App.getLoggedUser().getSurname());

			newWindow = new PersonalWindow(App.getLoggedUser().getRole());

		} else {
			newWindow = new PersonalWindow(null);
		}
		
		// Show the next window
		PersonalWindowController c = new PersonalWindowController(app, newWindow);
		LogoutController l = new LogoutController(app, newWindow);
		newWindow.setController(c);
		newWindow.setLogoutController(l);
		newWindow.setVisible(true);
		this.window.setVisible(false);
	}
}