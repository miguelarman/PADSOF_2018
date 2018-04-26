package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application.App;
import exceptions.AUserIsAlreadyLoggedException;
import exceptions.IncorrectPasswordException;
import exceptions.UnexistentUserException;
import exceptions.UserIsBannedException;
import windows.LoginWindow;
import windows.PersonalWindow;

public class LoginController implements ActionListener {
	private LoginWindow window;
	private App app;
	
	public LoginController(App app, LoginWindow w) {
		this.window = w;
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		PersonalWindow newWindow;
		
		if (arg0.getActionCommand() == "Log in") {
		
		String id = this.window.getIdField();
		String password = this.window.getPasswordField();
		
		try {
			this.app.login(id, password);
		} catch (UserIsBannedException e) {
			JOptionPane.showMessageDialog(null, "The user with id " + id + " has been banned from the system. Please contact our Customer Service from Mondays to Fridays", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (IncorrectPasswordException | UnexistentUserException e) {
			JOptionPane.showMessageDialog(null, "Incorrect password or user. Please try again", "Warning",
			        JOptionPane.WARNING_MESSAGE);
			return;
		} catch (AUserIsAlreadyLoggedException e) {
			JOptionPane.showMessageDialog(null, "I really don't know how you got here", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(null, "Welcome dear " + id + " with password " + password + "("+ arg0.getActionCommand() + ")");
		
		
		newWindow = new PersonalWindow(App.getLoggedUser().getRole());
		
		} else {
			newWindow = new PersonalWindow(null);
		}
		
		// Show the next window
		PersonalWindowController c = new PersonalWindowController(app, newWindow);
		newWindow.setController(c);
		newWindow.setVisible(true);
		this.window.setVisible(false);
	}
}
//WindowXX ventana = new WindowXX();
//MyController c = new MyCOntroller();
//ventana.setControlador(c);
//ventana.setVisible(true);
//ventanaAnterior.setVisible(false);