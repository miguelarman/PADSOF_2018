package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import application.users.RegisteredUser.Role;
import controllers.PersonalWindowController;

public class PersonalWindow extends JFrame {

	private JTabbedPane pestanias;
	
	private JComponent personalPanel;
	private JButton logoutButton;
	private JLabel searchLabel;
	private JButton searchButton;
	
	private JComponent guestPanel;
	private JLabel bookedOffersLabel;
	private JButton bookedOffersButton;
	private JLabel paidOffersLabel;
	private JButton paidOffersButton;
	
	private JComponent hostPanel;
	private JLabel offersLabel;
	private JButton offersButton;
	private JLabel housesLabel;
	private JButton housesButton;
	
	private JComponent adminPanel;
	private JLabel pendingLabel;
	private JButton pendingButton;
	private JLabel creditCardLabel;
	private JButton creditCardButton;

	private Role role;
	
	public PersonalWindow(Role role) {
		super("Pestañas");
		
		this.role = role;
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		pestanias = new JTabbedPane();
		
		logoutButton = new JButton("Log out");
		
		personalPanel = new JPanel();

		// TODO Adding content to personalPanel
		searchLabel = new JLabel("Search offers in our database!");
		searchButton = new JButton("Search");
		
		personalPanel.add(searchLabel); personalPanel.add(searchButton);
		
		pestanias.addTab("Personal zone", null, personalPanel);
		
		// We add panels depending on the user's role
		if (role == Role.GUEST || role == Role.MULTIROLE) {
			guestPanel = new JPanel();
			
			// TODO Adding content to guestPanel
			bookedOffersLabel = new JLabel("Check out your booked offers:");
			bookedOffersButton = new JButton("Booked offers");
			paidOffersLabel = new JLabel("Check out your paid offers:");
			paidOffersButton = new JButton("Paid offers");
			
			guestPanel.add(bookedOffersLabel); guestPanel.add(bookedOffersButton);
			guestPanel.add(paidOffersLabel); guestPanel.add(paidOffersButton);
			
			
			pestanias.addTab("Guest zone", null, guestPanel);
		}
		
		if (role == Role.HOST || role == Role.MULTIROLE) {
			hostPanel = new JPanel();
			
			// TODO Adding content to hostPanel
			offersLabel = new JLabel("Check out your offers:");
			offersButton = new JButton("My offers");
			housesLabel = new JLabel("Check out your houses:");
			housesButton = new JButton("My houses");
			
			hostPanel.add(offersLabel); hostPanel.add(offersButton);
			hostPanel.add(housesLabel); hostPanel.add(housesButton);
			
			pestanias.addTab("Host zone", null, hostPanel);
		}
		
		if (role == Role.ADMIN) {
			adminPanel = new JPanel();
			
			// TODO Adding content to adminPanel
			pendingLabel = new JLabel("See all the offers pending for approval:");
			pendingButton = new JButton("Pending offers");
			creditCardLabel = new JLabel("Modify credit card number of any user:");
			creditCardButton = new JButton("Modify credit cards");
			
			adminPanel.add(pendingLabel); adminPanel.add(pendingButton);
			adminPanel.add(creditCardLabel); adminPanel.add(creditCardButton);
			
			pestanias.addTab("Admin zone", null, adminPanel);
		}

		cont.add(pestanias,BorderLayout.CENTER);
		cont.add(logoutButton, BorderLayout.SOUTH);
		
		this.setSize(400, 500);
		this.setVisible(false);
	}

	public void setLogoutController(ActionListener c) {
		logoutButton.addActionListener(c);
	}
	
	public void setController(ActionListener c) {
		this.searchButton.addActionListener(c);
		
		if (this.role == Role.GUEST || this.role == Role.MULTIROLE) {
			bookedOffersButton.addActionListener(c);
			paidOffersButton.addActionListener(c);
		}
		
		if (role == Role.HOST || role == Role.MULTIROLE) {
			offersButton.addActionListener(c);
			housesButton.addActionListener(c);
		}
		
		if (role == Role.ADMIN) {
			pendingButton.addActionListener(c);
			creditCardButton.addActionListener(c);
		}
	}

}
