package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import application.App;
import exceptions.InvalidOfferStatusException;
import exceptions.InvalidRolException;
import exceptions.OfferIsPendingForChangesExceptions;
import windows.HouseWindow;
import windows.PendingOffersWindow;
import windows.WatchOfferWindow;

public class WatchOfferController implements ActionListener {

	private App app;
	private WatchOfferWindow window;
	private PendingOffersWindow previousWindow;
	
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
