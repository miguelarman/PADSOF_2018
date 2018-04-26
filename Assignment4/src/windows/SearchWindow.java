package windows;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import application.users.RegisteredUser;

public class SearchWindow extends JFrame {
	
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
		ButtonGroup group = new ButtonGroup();
		group.add(dates);
		group.add(zipCode);
		group.add(offerType);
		
		options.add(dates);
		options.add(zipCode);
		options.add(offerType);
		
	}
}
