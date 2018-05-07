package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import application.App;
import exceptions.*;
import windows.*;

/**
 * Controller for the WatchOfferWindow. Performs actions when the admin clicks a button:
 * <ul>
 * <li><b>Approve: </b>Approves the offer, which is marked as available for other users to book or purchase</li>
 * <li><b>Deny: </b>Denies the offer, which is removed from the system</li>
 * <li><b>Suggest changes: </b>Marks the offer as pending for changes, and creates a message that can be seen by the host</li>
 * <li><b>View house: </b>Opens a new window to view the house</li>
 * </ul>
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class WatchOfferController implements ActionListener {

	/**
	 * Model field for the controller. Contains our app in its current state
	 */
	private App app;
	/**
	 * View field for the controller. Contains the WatchOfferWindow
	 */
	private WatchOfferWindow window;
	/**
	 * Previous window
	 */
	private PendingOffersWindow previousWindow;
	
	/**
	 * Constructor of the OfferWindowController class when accessing from MyOffersWindow
	 * 
	 * @param app Model for the controller
	 * @param pw Previous window 
	 * @param w View for the controller
	 */
	public WatchOfferController(App app, PendingOffersWindow pw, WatchOfferWindow w) {
		this.app = app;
		this.window = w;
		this.previousWindow = pw;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String pressedButton = e.getActionCommand();
		switch(pressedButton) {
		case("Approve"):
			try {
				this.app.approveOffer(this.window.getOffer());
				this.previousWindow.deleteOfferFromTable(this.window.getOffer());
			} catch (OfferIsPendingForChangesExceptions | InvalidRolException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Operation completed successfully!");
			this.previousWindow.setVisible(true);
			this.window.setVisible(false);
			break;
		case("Deny"):
			try {
				this.app.denyOffer(this.window.getOffer());
				this.previousWindow.deleteOfferFromTable(this.window.getOffer());
			} catch (OfferIsPendingForChangesExceptions | InvalidRolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Operation completed successfully!");
			this.previousWindow.setVisible(true);
			this.window.setVisible(false);
			break;
		case("Suggest changes"):
			JTextField changes = new JTextField();
			Object[] content = { "Suggest changes:", changes};
			
			int option = JOptionPane.showConfirmDialog(null, content, "Changes", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				String suggestedChanges = changes.getText();
				if (suggestedChanges.equals("") || suggestedChanges == null) {
					JOptionPane.showMessageDialog(null, "Please, suggest some changes", "Error", JOptionPane.ERROR_MESSAGE);
				}
				try {
					this.app.suggestChanges(this.window.getOffer(), suggestedChanges);
				} catch (InvalidOfferStatusException | InvalidRolException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				this.previousWindow.deleteOfferFromTable(this.window.getOffer());
				JOptionPane.showMessageDialog(null, "Operation completed successfully!");
				this.window.setVisible(false);
				this.previousWindow.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Operation cancelled correctly");
			}
			break;
		case("View house"):
			// TODO
			HouseWindow newWindow = new HouseWindow(this.window.getOffer().getHouse());
			HouseWindowController h = new HouseWindowController(this.app, newWindow, this.window.getOffer().getHouse());
			newWindow.setController(h);
			newWindow.setGoBackController(new GoBackController(this.window, newWindow));
			newWindow.setVisible(true);
			break;
		default:
			break;
		}
		
		
	}

}
