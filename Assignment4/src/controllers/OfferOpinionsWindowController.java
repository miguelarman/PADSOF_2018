package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import application.App;
import application.offer.Offer;
import application.opinion.Comment;
import application.opinion.Opinion;
import exceptions.NoRowSelectedException;
import exceptions.NoUserLoggedException;
import windows.OfferOpinionsWindow;
import windows.RepliesWindow;

public class OfferOpinionsWindowController implements ActionListener {

	private OfferOpinionsWindow window;
	private App app;
	private Offer offer;

	public OfferOpinionsWindowController(App app, OfferOpinionsWindow w, Offer o) {
		this.app = app;
		this.window = w;
		this.offer = o;
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
				GoBackController g = new GoBackController(this.window, newWindow);
				newWindow.setController(r);
				newWindow.setGoBackController(g);
				newWindow.setVisible(true);
				this.window.setVisible(false);
			} catch (NoRowSelectedException e) {
				JOptionPane.showMessageDialog(null, "You must select an offer before clicking this button", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	}
}