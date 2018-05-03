package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.App;
import windows.HouseWindow;
import windows.OfferOpinionsWindow;
import windows.OfferWindow;
import windows.SuggestionsWindow;

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
			HouseWindow newWindow = new HouseWindow(this.window.getOffer().getHouse());
			HouseWindowController h = new HouseWindowController(this.app, newWindow, this.window.getOffer().getHouse());
			newWindow.setController(h);
			newWindow.setGoBackController(new GoBackController(this.window, newWindow));
			newWindow.setVisible(true);
			break;
		case("View opinions"):
			OfferOpinionsWindow w = new OfferOpinionsWindow(this.window.getOffer());
			OfferOpinionsWindowController o = new OfferOpinionsWindowController(this.app, w, this.window.getOffer());
			w.setController(o);
			w.setVisible(true);

			// w.setLocation(this.window.location().x + 20, this.window.location().y + 20);
			break;
		case("Book this offer"):
			// TODO
			break;
		case("Purchase this offer"):
			// TODO
			break;
		case("View suggestions"):
			SuggestionsWindow window2 = new SuggestionsWindow(this.app.getChangesRequests(this.window.getOffer()));
			window2.setGoBackController(new GoBackController(this.window, window2));
			window2.setVisible(true);
			break;
		default:
			break;
		}
	}

}
