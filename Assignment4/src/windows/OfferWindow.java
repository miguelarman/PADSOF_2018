package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.offer.HolidayOffer;
import application.offer.House;
import application.offer.Offer;
import application.offer.OfferStatus;
import application.users.MultiRoleUser;
import application.users.RegisteredUser.Role;
import controllers.GoBackController;
import controllers.OfferWindowController;

public class OfferWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2920513769048554961L;
	private Offer offer;
	private JButton viewHouseButton;
	private JButton viewOpinionsButton;
	private JButton bookOfferButton;
	private JButton purchaseOfferButton;
	private JButton changesButton;

	public OfferWindow(Offer offer, Role role) {
		super("Offer");
		
		this.offer = offer;
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		JLabel titleLabel = new JLabel("Check out this offer:");
		// TODO descomentar los siguiente
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 20));
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(titleLabel);
		cont.add(titlePanel, BorderLayout.NORTH);
		
		JPanel offerPanel = new JPanel();
		
//		offerPanel.setLayout(new GridLayout(5, 2));
		
		GridLayout l = new GridLayout(5, 2);
		l.setVgap(1);
		offerPanel.setLayout(l);
		
		offerPanel.add(new JLabel("Starting on:"));			offerPanel.add(new JLabel(offer.getDate().toString()));
		offerPanel.add(new JLabel("Amount to be paid:"));	offerPanel.add(new JLabel(offer.getAmount().toString()));
		offerPanel.add(new JLabel("Description:"));			offerPanel.add(new JLabel("<html>" + offer.getDescription() + "</html>")); // This way enables multiline text
		offerPanel.add(new JLabel("This offer is:"));		offerPanel.add(new JLabel(offer.getStatus().toString()));
		offerPanel.add(new JLabel("Average rating:"));		offerPanel.add(new JLabel(offer.getAvgRating() + " out of 5 stars"));
		cont.add(offerPanel, BorderLayout.CENTER);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		viewHouseButton = new JButton("View house"); 		buttonsPanel.add(viewHouseButton);
		viewOpinionsButton = new JButton("View opinions");	buttonsPanel.add(viewOpinionsButton);
		bookOfferButton = new JButton("Book this offer");
		purchaseOfferButton = new JButton("Purchase this offer");
		if (role == Role.GUEST || role == Role.MULTIROLE) {
			buttonsPanel.add(bookOfferButton);
			buttonsPanel.add(purchaseOfferButton);
		}
		if(offer.getStatus().equals(OfferStatus.PENDING_FOR_CHANGES)) {
			changesButton = new JButton("View suggestions");
			buttonsPanel.add(changesButton);
		}
//		rateOfferButton = new JButton("Rate this offer");
		cont.add(buttonsPanel, BorderLayout.SOUTH);
		
		// We add left and right margins
		JPanel leftMargin = new JPanel(); JPanel rightMargin = new JPanel();
		leftMargin.setPreferredSize(new Dimension(50, 0)); rightMargin.setPreferredSize(new Dimension(50, 0));
		cont.add(leftMargin, BorderLayout.WEST); cont.add(rightMargin, BorderLayout.EAST);
		
		
		this.setSize(600,  400);
		this.setVisible(false);
	}

	public void setController(OfferWindowController c) {
		this.viewHouseButton.addActionListener(c);
		this.viewOpinionsButton.addActionListener(c);
		this.bookOfferButton.addActionListener(c);
		this.purchaseOfferButton.addActionListener(c);
	}

	public void setGoBackController(GoBackController g) {
//		this.goBackButton.addActionListener(g);
	}
	
	public Offer getOffer() {
		return this.offer;
	}

	public static void main(String...strings) {
		House h = new House("28049", null, new MultiRoleUser(null, null, null, null, null));
		
		Offer o = new HolidayOffer(LocalDate.now(), 2.0, 2.0, "This offer is perfect for mature lovers who enjoy walking through forests... Spend a weekend here and you will come back", h, null);
		
		OfferWindow w = new OfferWindow(o, Role.MULTIROLE);
		w.setController(new OfferWindowController(null, w));
		w.setGoBackController(new GoBackController(new LoginWindow(), w));
		
		w.setVisible(true);
	}
}
