package controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import application.App;
import application.opinion.Comment;
import application.opinion.Opinion;
import exceptions.NoRowSelectedException;
import windows.RepliesWindow;

public class RepliesWindowController implements ActionListener {

	private App app;
	private RepliesWindow window;

	public RepliesWindowController(App app, RepliesWindow w) {
		this.app = app;
		this.window = w;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Rate this comment")) {
			// TODO
		} else if (arg0.getActionCommand().equals("Reply to this comment")) {
			// TODO
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
				GoBackController g = new GoBackController(this.window, newWindow);
				newWindow.setController(r);
				newWindow.setGoBackController(g);
				newWindow.setVisible(true);
//				this.window.setVisible(false);
				
//				newWindow.setLocation(this.window.location().x + 20, this.window.location().y + 20);
			} catch (NoRowSelectedException e) {
				JOptionPane.showMessageDialog(null, "You must select an offer before clicking this button", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	}

}
