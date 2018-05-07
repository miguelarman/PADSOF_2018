package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import application.App;
import application.offer.Offer;
import application.opinion.*;
import exceptions.NoRowSelectedException;
import exceptions.NoUserLoggedException;
import windows.*;

/**
 * Controller for the OfferOpinionsWindow. This controller waits until the user
 * wants to reply or rate an offer, or view its replies
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez
 *         (alberto.gonzalezk@estudiante.uam.es)
 */
public class OfferOpinionsWindowController implements ActionListener {

	/**
	 * View field for the controller. Contains the OfferOpinionsWindow
	 */
	private OfferOpinionsWindow window;
	/**
	 * Model field for the controller. Contains our app in its current state
	 */
	private App app;
	/**
	 * Offer which is being displayed on the window. Needed to perform operations
	 */
	private Offer offer;
	/**
	 * Previous window. Needed to refresh its table
	 */
	private OfferWindow previousWindow;

	/**
	 * Constructor of the OfferOpinionsWindowController class
	 * 
	 * @param app Model for the controller
	 * @param w View for the controller
	 * @param o Displayed offer
	 * @param ow Previous window
	 */
	public OfferOpinionsWindowController(App app, OfferOpinionsWindow w, Offer o, OfferWindow ow) {
		this.app = app;
		this.window = w;
		this.offer = o;
		this.previousWindow = ow;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getActionCommand().equals("Comment on this offer")) {
			String comment = this.window.getWrittenComment();
			
			try {
				this.offer.rateOffer(comment);
				JOptionPane.showMessageDialog(null, "Your comment has been successfully recorded");
				this.window.addComment(comment);
			} catch (NoUserLoggedException e) {
				JOptionPane.showMessageDialog(null, "To rate offers you must be logged in the system. Please try again after logging in", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (arg0.getActionCommand().equals("Rate this offer")) {
			String rating = this.window.getWrittenRating();
			Double numericalRating;
			
			for(Opinion r: offer.getOpinions()) {
				if(r.getClass() == Rating.class) {
					if(App.getLoggedUser().getNIF().equals(r.getCommenter().getNIF())) {
						JOptionPane.showMessageDialog(null, "You can only rate an offer once",
								"Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
			}
			try {
				numericalRating = Double.parseDouble(rating);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "You must enter a numerical rating. For example 2.0 or 0.5",
						"Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}

			if (numericalRating < 0 || numericalRating > 5) {
				JOptionPane.showMessageDialog(null, "The rating must be between 0 and 5 (both included)", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				try {
					this.offer.rateOffer(numericalRating);
					JOptionPane.showMessageDialog(null, "Your rating has been successfully recorded");
					this.window.addRating(numericalRating);
					
					this.previousWindow.refreshLabels();
				} catch (NoUserLoggedException e) {
					JOptionPane.showMessageDialog(null, "To rate offers you must be logged in the system. Please try again after logging in", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (arg0.getActionCommand().equals("View replies")) {
			try {
				
				Opinion selectedOpinion = this.window.getSelection();
				
				if (selectedOpinion.getClass() != Comment.class) {
					JOptionPane.showMessageDialog(null, "Only text opinions can receive replies", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				// Show the next window
				RepliesWindow newWindow = new RepliesWindow((Comment)selectedOpinion);
				RepliesWindowController r = new RepliesWindowController(app, newWindow);
				newWindow.setController(r);
				newWindow.setVisible(true);
				this.window.setVisible(false);
			} catch (NoRowSelectedException e) {
				JOptionPane.showMessageDialog(null, "You must select an offer before clicking this button", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	}
}