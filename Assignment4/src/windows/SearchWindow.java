package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import application.users.RegisteredUser;
import controllers.SearchController;

public class SearchWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5035536669428890618L;
	final private JComboBox<String> options;
	
	private JButton searchButton;
	private JButton previousButton;
	
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
		
		options.add(new JLabel("Select a type of search:\n"));
		searches.add(options, BorderLayout.NORTH);
		
		previousButton = new JButton("Previous");
		searchButton = new JButton("Search");
		buttons.add(previousButton);
		buttons.add(searchButton);
		cont.add(searches, BorderLayout.CENTER);
		cont.add(buttons, BorderLayout.SOUTH);
		this.setContentPane(cont);
		this.setSize(400, 500);
		this.setVisible(false);
		
	}

	public void setController(SearchController s) {
		// TODO Auto-generated method stub
		
	}
}
