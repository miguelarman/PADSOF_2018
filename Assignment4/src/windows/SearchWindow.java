package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
	private JRadioButton dates;
	private JRadioButton zipCode;
	private JRadioButton offerType;
	private JRadioButton booked;
	private JRadioButton paid;
	private JRadioButton avgRating;
	
	final private JTextField zipCodeField;
	final private JComboBox<String> offerTypeBox;
	
	
	public SearchWindow(RegisteredUser user) {
		super("Search offers");
		
		JPanel options;
		Container cont = super.getContentPane();
		JPanel buttons = new JPanel(new GridLayout(1, 2));
		if(user == null) {
			options = new JPanel(new GridLayout(3, 2));
		}
		else {
			options = new JPanel(new GridLayout(6, 2));
		}
		String[] typesOfOffer = {"Living offer", "Holiday offer"};
		offerTypeBox = new JComboBox<String>(typesOfOffer);
		dates = new JRadioButton("Search by dates");
		zipCode = new JRadioButton("Search by ZIP code");
		offerType = new JRadioButton("Search by type of offer");
		dates.setSelected(true);
		
		ButtonGroup group = new ButtonGroup();
		group.add(dates);
		group.add(zipCode);
		group.add(offerType);
		
		zipCodeField = new JTextField();
		options.add(zipCode);
		options.add(zipCodeField);
		options.add(offerType);
		options.add(offerTypeBox);
		options.add(dates);
		
		if(user != null) {
			booked = new JRadioButton("Search booked offers");
			paid = new JRadioButton("Search paid offers");
			avgRating = new JRadioButton("Search by average rating");	
			
			group.add(booked);
			group.add(paid);
			group.add(avgRating);
			
			options.add(booked);
			options.add(paid);
			options.add(avgRating);
		}
		cont.add(options, BorderLayout.CENTER);
		cont.add(buttons, BorderLayout.SOUTH);
		this.setContentPane(cont);
		this.setSize(400, 500);
		this.setVisible(false);
		
	}

	public void setController(SearchController s) {
		// TODO Auto-generated method stub
		
	}
}
