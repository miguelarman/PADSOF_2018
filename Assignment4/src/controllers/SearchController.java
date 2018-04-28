package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.App;
import windows.SearchWindow;

public class SearchController implements ActionListener {
	
	SearchWindow window;
	App app;
	
	public SearchController(App a, SearchWindow w) {
		window = w;
		app = a;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
