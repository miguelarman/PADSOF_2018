package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import application.App;
import application.offer.Offer;
import application.users.RegisteredUser;
import exceptions.NoRowSelectedException;
import windows.IntroduceCardWindow;
import windows.PendingOffersWindow;
import windows.WatchOfferWindow;

public class PendingOffersController implements ActionListener {
	
	private App app;
	private PendingOffersWindow window;

	public PendingOffersController(App app, PendingOffersWindow w) {
		this.app = app;
		this.window = w;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Offer selectedOffer = null;
		try {
			selectedOffer = this.window.getSelection();
		} catch (NoRowSelectedException e1) {
			JOptionPane.showMessageDialog(null, "You must select an offer before clicking this button", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// Show the next window
		WatchOfferWindow newWindow = new WatchOfferWindow(selectedOffer);
		WatchOfferController c = new WatchOfferController(app, this.window, newWindow);
		GoBackController g = new GoBackController(this.window, newWindow);
		newWindow.setController(c);
		newWindow.setGoBackController(g);
		newWindow.setVisible(true);
		this.window.setVisible(false);
		
	}

}
