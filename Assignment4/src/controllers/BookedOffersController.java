package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import application.App;
import application.offer.Offer;
import application.users.RegisteredUser;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import exceptions.InvalidRolException;
import exceptions.NoRowSelectedException;
import exceptions.NotTheReserverException;
import exceptions.TimeIsUpException;
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
		Offer selectedOffer;
		switch(e.getActionCommand()) {
		case("View offer"):
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
			try {
				selectedOffer = this.window.getSelection().getBookedOffer();

				Object[] content = { "This operation will cost" + selectedOffer.getAmount() + "\n" + "The operation will be executed with de creditcard " + App.getLoggedUser().getCreditCard() + "\n" + "Do you want to buy the offer?"};
				
				int option = JOptionPane.showConfirmDialog(null, content, "Payment", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					try {
						app.payReservation(this.window.getSelection());
					} catch (InvalidCardNumberException e1) {
						JOptionPane.showMessageDialog(null, "Your credit card number was not valid. You are now banned from the system", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (NotTheReserverException e1) {
						// TODO A ver que se ve al saltar estas excepciones
						e1.printStackTrace();
					} catch (TimeIsUpException e1) {
						JOptionPane.showMessageDialog(null, "The 5-day period to pay this offer has finished.\nYou cannot book or pay this offer again", "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "The offer has been paid successfully!");
				} else {
					JOptionPane.showMessageDialog(null, "Operation cancelled correctly");
				}
			} catch (NoRowSelectedException e1) {
				JOptionPane.showMessageDialog(null, "You must select an offer before clicking this button", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			break;
		default:
			break;
		}
		
	}

}
