package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.offer.HolidayOffer;
import application.offer.House;
import application.offer.Offer;
import application.users.MultiRoleUser;
import application.users.RegisteredUser.Role;
import controllers.GoBackController;
import controllers.OfferWindowController;

public class OfferWindow extends JFrame {

	private JButton viewHouseButton;
	private JButton viewOpinionsButton;
	private JButton bookOfferButton;
	private JButton purchaseOfferButton;

	public OfferWindow(Offer offer, Role role) {
		super("Offer");
		
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
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		viewHouseButton = new JButton("View house"); 		buttonsPanel.add(viewHouseButton);
		viewOpinionsButton = new JButton("View Opinions");	buttonsPanel.add(viewOpinionsButton);
		bookOfferButton = new JButton("Book this offer");
		purchaseOfferButton = new JButton("Purchase this offer");
		if (role == Role.GUEST || role == Role.MULTIROLE) {
			buttonsPanel.add(bookOfferButton);
			buttonsPanel.add(purchaseOfferButton);
		}
//		rateOfferButton = new JButton("Rate this offer");
		cont.add(buttonsPanel, BorderLayout.SOUTH);
		
		
		
		
		this.setSize(600,  400);
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
		
		Offer o = new HolidayOffer(LocalDate.now(), 2.0, 2.0, "This offer is perfect for mature lovers who enjoy walking through forests... Spend a weekend here and you will come back", h, null);
		
		OfferWindow w = new OfferWindow(o, Role.MULTIROLE);
		w.setController(new OfferWindowController(null, w));
		w.setGoBackController(new GoBackController(new LoginWindow(), w));
		
		w.setVisible(true);
	}
}
