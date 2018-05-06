package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import application.App;
import application.offer.Offer;
import exceptions.NoRowSelectedException;
import windows.OfferWindow;
import windows.SearchResultWindow;

public class SearchResultController implements ActionListener {
	
	private SearchResultWindow window;
	private App app;

	public SearchResultController(App app, SearchResultWindow w) {
		this.window = w;
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Offer selectedOffer;
		try {
			selectedOffer = this.window.getSelection();
			
			// Show the next window
			OfferWindow newWindow;
			try {
				newWindow = new OfferWindow(selectedOffer, App.getLoggedUser().getRole());
			} catch (NullPointerException ex) {
				newWindow = new OfferWindow(selectedOffer, null);
			}
			OfferWindowController c = new OfferWindowController(app, newWindow, this.window);
			GoBackController g = new GoBackController(this.window, newWindow);
			newWindow.setController(c);
			newWindow.setGoBackController(g);
			newWindow.setVisible(true);
			this.window.setVisible(false);
		} catch (NoRowSelectedException e1) {
			JOptionPane.showMessageDialog(null, "You must select an offer before clicking this button", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}

	}
}