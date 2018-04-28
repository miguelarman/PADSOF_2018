package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.App;
import windows.OfferOpinionsWindow;
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
		switch(arg0.getActionCommand()) {
		case("View house"):
			// TODO
			break;
		case("View opinions"):
			// TODO
			OfferOpinionsWindow w = new OfferOpinionsWindow(this.window.getOffer());
			OfferOpinionsWindowController c = new OfferOpinionsWindowController(this.app, w);
			w.setController(c);
			w.setVisible(true);
			break;
		case("Book this offer"):
			// TODO
			break;
		case("Purchase this offer"):
			// TODO
			break;
		default:
			break;
		}
	}

}
