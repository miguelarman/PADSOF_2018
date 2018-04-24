package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.App;
import windows.PersonalWindow;

public class PersonalWindowController implements ActionListener {

	private PersonalWindow window;
	private App app;

	public PersonalWindowController(App app, PersonalWindow w) {
		this.window = w;
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		
		this.window.setVisible(false);
	}

}
