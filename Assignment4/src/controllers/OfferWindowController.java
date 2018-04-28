package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.App;
import windows.OfferWindow;

public class OfferWindowController implements ActionListener {

	private OfferWindow window;
	private App app;

	public OfferWindowController(App app, OfferWindow window) {
		this.app = app;
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
