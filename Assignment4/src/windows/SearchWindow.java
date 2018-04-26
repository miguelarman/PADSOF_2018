package windows;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import application.users.RegisteredUser;
import controllers.SearchController;

public class SearchWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5035536669428890618L;
	JRadioButton dates;
	JRadioButton zipCode;
	JRadioButton offerType;
	JRadioButton booked;
	JRadioButton paid;
	JRadioButton avgRating;
	
	public SearchWindow(RegisteredUser user) {
		super("Search offers");
		JPanel options = new JPanel(new FlowLayout());
		dates = new JRadioButton("Search by dates");
		zipCode = new JRadioButton("Search by ZIP code");
		offerType = new JRadioButton("Search by type of offer");
		dates.setSelected(true);
		
		ButtonGroup group = new ButtonGroup();
		group.add(dates);
		group.add(zipCode);
		group.add(offerType);
		
		options.add(dates);
		options.add(zipCode);
		options.add(offerType);
		
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
		this.setSize(400, 500);
		this.setVisible(false);
		
	}

	public void setController(SearchController s) {
		// TODO Auto-generated method stub
		
	}
}
