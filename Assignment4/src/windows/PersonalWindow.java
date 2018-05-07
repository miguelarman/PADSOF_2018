package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import application.users.RegisteredUser.Role;
import controllers.LogoutController;
import controllers.PersonalWindowController;

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class PersonalWindow extends JFrame {

	/**
	* ID needed for the class to be Serializable
	*/
	private static final long serialVersionUID = -3900066860728430861L;

	/**
	 * Tabs to display the information needed depending on the type of user
	 */
	private JTabbedPane pestanias;
	
	/**
	 * Component that contains the buttons that all users can use
	 */
	private JComponent personalPanel;
	
	/**
	 * Button that logs you out of the system
	 */
	private JButton logoutButton;
	
	/**
	 * Label that indicates the searchButton
	 */
	private JLabel searchLabel;
	
	/**
	 * Button to search in the system
	 */
	private JButton searchButton;
	
	/**
	 * Component that contains the buttons that only guests can use
	 */
	private JComponent guestPanel;
	
	/**
	 * Label that indicates the bookedOffersButton
	 */
	private JLabel bookedOffersLabel;
	
	/**
	 * Button to see the offers booked by a user
	 */
	private JButton bookedOffersButton;
	
	/**
	 * Component that contains the buttons that only hosts can use
	 */
	private JComponent hostPanel;
	
	/**
	 * Label that indicates the offerButton
	 */
	private JLabel offersLabel;
	
	/**
	 * Button to see the offers created by the logged user
	 */
	private JButton offersButton;
	
	/**
	 * Label that indicates the housesButton
	 */
	private JLabel housesLabel;
	
	/**
	 * Button to see the houses created by the logged user
	 */
	private JButton housesButton;
	
	/**
	 * Component that contains the buttons that only admins can use
	 */
	private JComponent adminPanel;
	
	/**
	 * Label that indicates the pendingButton
	 */
	private JLabel pendingLabel;
	
	/**
	 * Button to see the offers pending for approval
	 */
	private JButton pendingButton;
	
	/**
	 * Label that indicates the creditCardButton
	 */
	private JLabel creditCardLabel;
	
	/**
	 * Button to see the banned users with an incorrect credit card number
	 */
	private JButton creditCardButton;

	/**
	 * Role of the logged user
	 */
	private Role role;
	
	/**
	 * Constructor of the class PersonalWindow
	 * @param role Role of the logged user
	 */
	public PersonalWindow(Role role) {
		super("Pestañas");
		
		this.role = role;
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		pestanias = new JTabbedPane();
		
		logoutButton = new JButton("Log out");
		
		personalPanel = new JPanel();

		// Adding content to personalPanel
		searchLabel = new JLabel("Search offers in our database!");
		searchButton = new JButton("Search");
		
		personalPanel.add(searchLabel); personalPanel.add(searchButton);
		
		// TODO JLabel personalIcon = new JLabel(); personalIcon.setIcon(new ImageIcon("icons/personalZoneIcon.png")); personalPanel.add(personalIcon, BorderLayout.EAST);
		
		pestanias.addTab("Personal zone", null, personalPanel);
		
		// We add panels depending on the user's role
		if (role == Role.GUEST || role == Role.MULTIROLE) {
			guestPanel = new JPanel();
			
			// Adding content to guestPanel
			bookedOffersLabel = new JLabel("Check out your booked offers:");
			bookedOffersButton = new JButton("Booked offers");
			
			guestPanel.add(bookedOffersLabel); guestPanel.add(bookedOffersButton);
			
			// TODO JLabel icon = new JLabel(); icon.setIcon(new ImageIcon("icons/guestPersonalZoneIcon.png")); guestPanel.add(icon, BorderLayout.EAST);
			
			pestanias.addTab("Guest zone", null, guestPanel);
		}
		
		if (role == Role.HOST || role == Role.MULTIROLE) {
			hostPanel = new JPanel();
			hostPanel.setLayout(new GridLayout(10, 1)); // This 10 is just to make it look nice
			
			// Adding content to hostPanel
			offersLabel = new JLabel("Check out your offers:");
			offersButton = new JButton("My offers");
			housesLabel = new JLabel("Check out your houses:");
			housesButton = new JButton("My houses");
			
			JPanel topPanel = new JPanel(); topPanel.setLayout(new FlowLayout());
			JPanel bottomPanel = new JPanel(); topPanel.setLayout(new FlowLayout());
			
			topPanel.add(offersLabel); topPanel.add(offersButton);
			bottomPanel.add(housesLabel); bottomPanel.add(housesButton);
			
			hostPanel.add(topPanel); hostPanel.add(bottomPanel);
			
			// TODO JLabel icon = new JLabel(); icon.setIcon(new ImageIcon("icons/hostPersonalZoneIcon.png")); hostPanel.add(icon, BorderLayout.EAST);
			
			pestanias.addTab("Host zone", null, hostPanel);
		}
		
		if (role == Role.ADMIN) {
			adminPanel = new JPanel(); 
			
			// Adding content to adminPanel
			pendingLabel = new JLabel("See all the offers pending for approval:");
			pendingButton = new JButton("Pending offers");
			creditCardLabel = new JLabel("Modify credit card number of any user:");
			creditCardButton = new JButton("Modify credit cards");
			
			adminPanel.add(pendingLabel); adminPanel.add(pendingButton);
			adminPanel.add(creditCardLabel); adminPanel.add(creditCardButton);
			
			// TODO JLabel icon = new JLabel(); icon.setIcon(new ImageIcon("icons/adminPersonalZoneIcon.png")); adminPanel.add(icon, BorderLayout.EAST);
			
			pestanias.addTab("Admin zone", null, adminPanel);
		}

		cont.add(pestanias,BorderLayout.CENTER);
		cont.add(logoutButton, BorderLayout.SOUTH);
		
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	/**
	 * Method that assigns the logoutButton with the logout controller
	 * @param c Controller to logout of the system
	 */
	public void setLogoutController(LogoutController c) {
		logoutButton.addActionListener(c);
	}
	
	/**
	 * Method that assigns all the buttons to the PersonalWindowController
	 * @param c
	 */
	public void setController(PersonalWindowController c) {
		this.searchButton.addActionListener(c);
		
		if (this.role == Role.GUEST || this.role == Role.MULTIROLE) {
			bookedOffersButton.addActionListener(c);
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
