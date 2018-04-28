package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import application.users.RegisteredUser;
import controllers.GoBackController;
<<<<<<< HEAD
import controllers.SearchController;
=======
>>>>>>> 9c41fea5df8b7546aa179663f74a3374bdfa250d

public class SearchWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5035536669428890618L;
	final private JComboBox<String> options;
	
	private JButton searchButton;
	private JButton goBackButton;
	
//	final private JTextField zipCodeField;
//	final private JComboBox<String> offerTypeBox;
	
	
	public SearchWindow(RegisteredUser user) {
		super("Search offers");
		
		Container cont = super.getContentPane();
		JPanel buttons = new JPanel(new GridLayout(1, 2));
		JPanel searches = new JPanel(new BorderLayout());
		
		String[] typesOfOffer = {"Living offer", "Holiday offer"};
		if(user == null) {
			String[] typesOfSearch = {"ZIP code", "Type of offer", "Dates"};
			options = new JComboBox<String>(typesOfSearch);
		}
		else {
			String[] typesOfSearch = {"ZIP code", "Type of offer", "Dates", "Booked offers", "Paid offers", "Average rating"};
			options = new JComboBox<String>(typesOfSearch);
		}
		//offerTypeBox = new JComboBox<String>(typesOfOffer);
		
		searches.add(options, BorderLayout.NORTH);
		
		goBackButton = new JButton("Previous");
		searchButton = new JButton("Search");
		buttons.add(goBackButton);
		buttons.add(searchButton);
		cont.add(searches, BorderLayout.CENTER);
		cont.add(buttons, BorderLayout.SOUTH);
		this.setContentPane(cont);
		this.setSize(400, 500);
		this.setVisible(false);
		
	}

	public void setBoxController(ActionListener s) {
		options.addActionListener(s);
	}
	
	public void setSearchController(ActionListener b) {
		searchButton.addActionListener(b);
		
	}
	
	public void setGoBackController(GoBackController g) {
		this.goBackButton.addActionListener(g);
	}
	
}
