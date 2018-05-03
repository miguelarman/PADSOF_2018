package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import application.App;
import application.offer.Offer;
import exceptions.NoRowSelectedException;
import windows.BookedOffersWindow;
import windows.OfferWindow;

public class BookedOffersController implements ActionListener {

	private BookedOffersWindow window;
	private App app;
	
	public BookedOffersController(App app, BookedOffersWindow window) {
		this.window = window;
		this.app = app;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case("View offer"):
			Offer selectedOffer;
			try {
				selectedOffer = this.window.getSelection().getBookedOffer();
				
				// Show the next window
				OfferWindow newWindow;
				try {
					newWindow = new OfferWindow(selectedOffer, App.getLoggedUser().getRole());
				} catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(null, "You must be logged in te system before accessing this feature", "Please log in", JOptionPane.ERROR_MESSAGE);
					return;
				}
				OfferWindowController c = new OfferWindowController(app, newWindow);
				GoBackController g = new GoBackController(this.window, newWindow);
				newWindow.setController(c);
				newWindow.setGoBackController(g);
				newWindow.setVisible(true);
				this.window.setVisible(false);
			} catch (NoRowSelectedException e1) {
				JOptionPane.showMessageDialog(null, "You must select an offer before clicking this button", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			break;
		case("Pay reservation"):
			//TODO 
			break;
		default:
			break;
		}
		
	}

}
