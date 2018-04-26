package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.App;
import windows.PersonalWindow;
import windows.SearchWindow;

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
		if(arg0.getActionCommand() == "Search") {
			SearchWindow newWindow = new SearchWindow(App.getLoggedUser());
			SearchController s = new SearchController(app, newWindow);
			newWindow.setVisible(true);
			this.window.setVisible(false);
		}
		
	}

}
