package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.App;
import application.offer.OfferStatus;
import exceptions.InvalidRolException;
import exceptions.OfferIsPendingForChangesExceptions;
import windows.ChangesWindow;
import windows.HouseWindow;
import windows.PendingOffersWindow;
import windows.WatchOfferWindow;

public class WatchOfferController implements ActionListener {

	private App app;
	private WatchOfferWindow window;
	private PendingOffersWindow previousWindow;
	
	public WatchOfferController(App app, PendingOffersWindow pw, WatchOfferWindow w) {
		this.app = app;
		this.window = w;
		this.previousWindow = pw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String pressedButton = e.getActionCommand();
		switch(pressedButton) {
		case("Accept"):
			try {
				this.app.approveOffer(this.window.getOffer());
			} catch (OfferIsPendingForChangesExceptions | InvalidRolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			this.previousWindow.setVisible(true);
			this.window.setVisible(false);
			break;
		case("Deny"):
			try {
				this.app.denyOffer(this.window.getOffer());
			} catch (OfferIsPendingForChangesExceptions | InvalidRolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.previousWindow.setVisible(true);
			this.window.setVisible(false);
			break;
		case("Suggest changes"):
			ChangesWindow newWindow1 = new ChangesWindow();
			ChangesController c = new ChangesController(this.app, newWindow1, this.previousWindow);
			newWindow1.setController(c);
			newWindow1.setGoBackController(new GoBackController(this.window, newWindow1));
			newWindow1.setVisible(true);
			this.window.setVisible(false);
			break;
		case("View house"):
			// TODO
			HouseWindow newWindow = new HouseWindow(this.window.getOffer().getHouse());
			HouseWindowController h = new HouseWindowController(this.app, newWindow, this.window.getOffer().getHouse());
			newWindow.setController(h);
			newWindow.setGoBackController(new GoBackController(this.window, newWindow));
			newWindow.setVisible(true);
			break;
		default:
			break;
		}
		
		
	}

}
