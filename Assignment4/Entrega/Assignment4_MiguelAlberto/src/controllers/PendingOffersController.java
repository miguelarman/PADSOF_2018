package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import application.App;
import application.offer.Offer;
import exceptions.NoRowSelectedException;
import windows.PendingOffersWindow;
import windows.WatchOfferWindow;


/**
 * Controller for the PendingOffersWindow. This controller just waits for the
 * admin to select an offer, and then creates an WatchOfferWindow, where the
 * admin can approve, deny...
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class PendingOffersController implements ActionListener {
	
	/**
	 * Model field for the controller. Contains our app in its current state
	 */
	private App app;
	/**
	 * View field for the controller. Contains the PendingOffersWindow
	 */
	private PendingOffersWindow window;

	/**
	 * Constructor of the PendingOffersController class
	 * 
	 * @param app Model for the controller
	 * @param w View for the controller
	 */
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
			JOptionPane.showMessageDialog(null, "You must select an offer before clicking this button", "Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Show the next window
		WatchOfferWindow newWindow = new WatchOfferWindow(selectedOffer);
		WatchOfferController c = new WatchOfferController(app, this.window, newWindow);
		GoBackController g = new GoBackController(this.window, newWindow);
		newWindow.setController(c);
		newWindow.setGoBackController(g);
		newWindow.setVisible(true);
		// this.window.setVisible(false);

	}

}
