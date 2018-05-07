package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import application.App;
import application.opinion.Comment;
import application.opinion.Opinion;
import application.opinion.Rating;
import exceptions.NoRowSelectedException;
import exceptions.NoUserLoggedException;
import windows.RepliesWindow;

/**
 * Controller for RepliesWindow. When the user clicks on a button, this
 * controller performs one of this actions:
 * <ul>
 * <li>Add a reply to the comment</li>
 * <li>Add a rating to the comment</li>
 * </ul>
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez
 *         (alberto.gonzalezk@estudiante.uam.es)
 */
public class RepliesWindowController implements ActionListener {

	/**
	 * Model field for the controller. Contains our app in its current state
	 */
	private App app;
	/**
	 * View field for the controller. Contains the RepliesWindow
	 */
	private RepliesWindow window;

	/**
	 * Constructor of the RepliesWindowController
	 * 
	 * @param app Model for the controller
	 * @param w View for the controller
	 */
	public RepliesWindowController(App app, RepliesWindow w) {
		this.app = app;
		this.window = w;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Rate this comment")) {
			
			for(Opinion r: this.window.getComment().getComments()) {
				if(r.getClass() == Rating.class) {
					if(App.getLoggedUser().getNIF().equals(r.getCommenter().getNIF())) {
						JOptionPane.showMessageDialog(null, "You can only rate a comment once",
								"Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
			}
			Double rating;
			try {
				rating = Double.parseDouble(this.window.getWrittenRating());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "You must enter a numerical rating. For example 2.0 or 0.5",
						"Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			Comment c = this.window.getComment();
			try {
				c.rateComment(rating);
			} catch (NoUserLoggedException e) {
				JOptionPane.showMessageDialog(null, "Please log in before rating comments");
			}
		} else if (arg0.getActionCommand().equals("Reply to this comment")) {
			String answer = this.window.getWrittenComment();
			if (answer.equals("") || answer == null) {
				JOptionPane.showMessageDialog(null, "Please write something before clicking", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Comment c = this.window.getComment();
			try {
				c.addReply(answer);
			} catch (NoUserLoggedException e) {
				JOptionPane.showMessageDialog(null, "Please log in before answering to comments", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} else if (arg0.getActionCommand().equals("View replies")) {
			try {

				Opinion selectedOpinion = this.window.getSelection();

				if (selectedOpinion.getClass() != Comment.class) {
					JOptionPane.showMessageDialog(null, "Only text opinions have replies", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (((Comment)selectedOpinion).getReplies().size() == 0) {
					JOptionPane.showMessageDialog(null, "The comment you selected does not have any replies");
					return;
				}

				// Show the next window
				RepliesWindow newWindow = new RepliesWindow((Comment) selectedOpinion);
				RepliesWindowController r = new RepliesWindowController(app, newWindow);
				newWindow.setController(r);
				newWindow.setVisible(true);

			} catch (NoRowSelectedException e) {
				JOptionPane.showMessageDialog(null, "You must select an offer before clicking this button", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	}

}
