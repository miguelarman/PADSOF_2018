package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import application.App;
import application.offer.Offer;
import application.offer.Reservation;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import exceptions.NoRowSelectedException;
import exceptions.NotTheReserverException;
import exceptions.RestrictedUserException;
import exceptions.TimeIsUpException;
import windows.HouseWindow;
import windows.LoginWindow;
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
			for(Reservation r: App.getLoggedUser().getReservedOffers()) {
				if(r.getBookedOffer().equals(this.window.getOffer())) {
					Offer selectedOffer = this.window.getOffer();

					Object[] content = { "This operation will cost" + selectedOffer.getAmount() + "\n" + "The operation will be executed with de creditcard " + App.getLoggedUser().getCreditCard() + "\n" + "Do you want to buy the offer?"};
					
					int option = JOptionPane.showConfirmDialog(null, content, "Payment", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						try {
							app.payReservation(r);
							JOptionPane.showMessageDialog(null, "The offer has been paid successfully!");
						} catch (InvalidCardNumberException e1) {
							LoginWindow newWindow2 = new LoginWindow();
							newWindow2.setController(new LoginController(this.app, newWindow2));
							this.window.setVisible(false);
							newWindow2.setVisible(true);
							JOptionPane.showMessageDialog(null, "Your credit card number was not valid. You are now banned from the system", "Error", JOptionPane.ERROR_MESSAGE);
						} catch (NotTheReserverException e1) {
							// TODO A ver que se ve al saltar estas excepciones
							e1.printStackTrace();
						} catch (TimeIsUpException e1) {
							JOptionPane.showMessageDialog(null, "The 5-day period to pay this offer has finished.\nYou cannot book or pay this offer again", "Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "Operation cancelled correctly");
					}
					return;
				}
			}
			Offer selectedOffer = this.window.getOffer();

			Object[] content = { "This operation will cost" + selectedOffer.getAmount() + "\n" + "The operation will be executed with the creditcard " + App.getLoggedUser().getCreditCard() + "\n" + "Do you want to buy the offer?"};
			
			int option = JOptionPane.showConfirmDialog(null, content, "Payment", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				try {
					app.payOffer(selectedOffer);
				} catch (InvalidCardNumberException e1) {
					JOptionPane.showMessageDialog(null, "Your credit card number was not valid. You are now banned from the system", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (RestrictedUserException e) {
					JOptionPane.showMessageDialog(null, "You cannot contract this offer", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				JOptionPane.showMessageDialog(null, "The offer has been paid successfully!");
			} else {
				JOptionPane.showMessageDialog(null, "Operation cancelled correctly");
			}

			break;
		case("View suggestions"):
			SuggestionsWindow window2 = new SuggestionsWindow(this.app.getRequests(this.window.getOffer()));
			window2.setGoBackController(new GoBackController(this.window, window2));
			window2.setVisible(true);
			break;
		default:
			break;
		}
	}

}
