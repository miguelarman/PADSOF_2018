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

import application.App;
import application.offer.House;
import application.offer.Offer;
import application.offer.OfferStatus;
import application.users.Host;
import application.users.MultiRoleUser;
import application.users.RegisteredUser.Role;
import controllers.GoBackController;
import controllers.OfferWindowController;
import exceptions.AUserIsAlreadyLoggedException;
import exceptions.IncorrectPasswordException;
import exceptions.InvalidDateException;
import exceptions.InvalidRolException;
import exceptions.NoUserLoggedException;
import exceptions.NotTheOwnerException;
import exceptions.OfferAlreadyCreatedException;
import exceptions.OfferIsPendingForChangesExceptions;
import exceptions.UnexistentUserException;
import exceptions.UserIsBannedException;

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
	private JButton modifyOffer;

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
		viewHouseButton = new JButton("View house");
		buttonsPanel.add(viewHouseButton);
		viewOpinionsButton = new JButton("View opinions");
		if (offer.getStatus() != OfferStatus.PENDING_FOR_APPROVAL && offer.getStatus() != OfferStatus.PENDING_FOR_CHANGES) {
			viewOpinionsButton = new JButton("View opinions");
			
		}
		bookOfferButton = new JButton("Book this offer");
		purchaseOfferButton = new JButton("Purchase this offer");
		if (role == Role.GUEST || role == Role.MULTIROLE) {
			buttonsPanel.add(bookOfferButton);
			buttonsPanel.add(purchaseOfferButton);
		}
		
		changesButton = new JButton("View suggestions");
		modifyOffer = new JButton("Modify offer");
		if (offer.getStatus() == OfferStatus.PENDING_FOR_CHANGES && App.getLoggedUser().getNIF().equals(offer.getHouse().getHost().getNIF())) {
			buttonsPanel.add(modifyOffer);
			buttonsPanel.add(changesButton);
		}
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
		this.changesButton.addActionListener(c);
		this.modifyOffer.addActionListener(c);
	}

	public void setGoBackController(GoBackController g) {
//		this.goBackButton.addActionListener(g);
	}
	
	public Offer getOffer() {
		return this.offer;
	}

	public static void main(String...strings) throws Exception {
		House h = new House("28049", null, new Host("host", "host", "host", "host", "host"));
		
		App app = App.openApp();
		app.login("host", "host");
		app.createHolidayOffer(LocalDate.now(), 2.0, 2.0, "This offer is perfect for mature lovers who enjoy walking through forests... Spend a weekend here and you will come back", h, LocalDate.now().plusDays(5));
		
		app.logout();
		app.login("admin", "admin");
		app.suggestChanges(app.getOffers().get(0), "Eres tonto acho");
		app.logout();
		app.login("host", "host");
		
		OfferWindow w = new OfferWindow(app.getOffers().get(0), App.getLoggedUser().getRole());
		w.setController(new OfferWindowController(app, w));
		w.setGoBackController(new GoBackController(new LoginWindow(), w));
		
		w.setVisible(true);
	}
}