package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.offer.HolidayOffer;
import application.offer.House;
import application.offer.Offer;
import application.users.MultiRoleUser;
import controllers.GoBackController;
import controllers.OfferWindowController;

public class OfferWindow extends JFrame {

	public OfferWindow(Offer offer) {
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		JLabel titleLabel = new JLabel("Check out this offer:");
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(titleLabel);
		cont.add(titlePanel, BorderLayout.NORTH);
		
		JPanel offerPanel = new JPanel();
		// TODO
//		offerPanel.setLayout(new GridLayout());
		JLabel startingDateLabel = new JLabel("Starting on " + offer.getDate());		offerPanel.add(startingDateLabel);
		JLabel priceLabel = new JLabel("Amount to be paid: " + offer.getAmount());		offerPanel.add(priceLabel);
		JLabel descriptionLabel = new JLabel("Description: " + offer.getDescription());	offerPanel.add(descriptionLabel);
		JLabel statusLabel = new JLabel("This offer is " + offer.getStatus());			offerPanel.add(statusLabel);
		JLabel avgRatingLabel = new JLabel("Average rating: " + offer.getAvgRating());	offerPanel.add(avgRatingLabel);
		cont.add(offerPanel, BorderLayout.CENTER);
		
		this.setSize(400,  400);
		this.setVisible(false);
	}

	public void setController(OfferWindowController c) {
		// TODO Auto-generated method stub
		
	}

	public void setGoBackController(GoBackController g) {
//		this.goBackButton.addActionListener(g);
	}

	public static void main(String...strings) {
		House h = new House(28049, null, new MultiRoleUser(null, null, null, null, null));
		
		Offer o = new HolidayOffer(LocalDate.now(), 2.0, 2.0, "This offer is perfect for nature lovers. Spend a weekend here and you will come back", h, null);
		
		OfferWindow w = new OfferWindow(o);
		w.setController(new OfferWindowController(null, w));
		w.setGoBackController(new GoBackController(new LoginWindow(), w));
		
		w.setVisible(true);
	}
}
