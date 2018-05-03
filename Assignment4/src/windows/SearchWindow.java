package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import application.dates.ModifiableDate;
import application.users.RegisteredUser;
import controllers.GoBackController;
import controllers.SearchBoxController;


public class SearchWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5035536669428890618L;
	final private JComboBox<String> options;
	
	private JButton searchButton;
	private JButton goBackButton;
	
	final private JTextField zipCodeField;
	final private JComboBox<String> offerTypeBox;
	final private JTextField avgRatingField;
	private JCalendar iniDate;
	private JCalendar endDate;
	
	public SearchWindow(RegisteredUser user) {
		super("Search offers");
		
		Container cont = super.getContentPane();
		JPanel buttons = new JPanel(new GridLayout(1, 2));
		JPanel searches = new JPanel(new BorderLayout());
		JPanel dates = new JPanel(new GridLayout(1, 2));
		String[] typesOfOffer = {"Living offer", "Holiday offer"};
		
		if(user == null) {
			String[] typesOfSearch = {"ZIP code", "Type of offer", "Dates"};
			options = new JComboBox<String>(typesOfSearch);
		}
		else {
			String[] typesOfSearch = {"ZIP code", "Type of offer", "Dates", "Booked offers", "Paid offers", "Average rating"};
			options = new JComboBox<String>(typesOfSearch);
		}
		
		zipCodeField = new JTextField(10);
		searches.add(zipCodeField, BorderLayout.CENTER);
		
		offerTypeBox = new JComboBox<String>(typesOfOffer);
		offerTypeBox.setBounds(100, 150, 200, 35);
		searches.add(offerTypeBox, BorderLayout.CENTER);
		
		avgRatingField = new JTextField(10);
		searches.add(avgRatingField, BorderLayout.CENTER);
		
		iniDate = new JCalendar();
		dates.add(iniDate, BorderLayout.CENTER);
		iniDate.setTodayButtonVisible(true);
		iniDate.setWeekOfYearVisible(false);
		iniDate.setDate(Date.from(ModifiableDate.getModifiableDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

		
		endDate = new JCalendar();
		dates.add(endDate, BorderLayout.WEST);
		endDate.setTodayButtonVisible(true);
		endDate.setWeekOfYearVisible(false);
		endDate.setDate(Date.from(ModifiableDate.getModifiableDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		
		searches.add(dates, BorderLayout.CENTER);
		searches.add(options, BorderLayout.NORTH);
		
		goBackButton = new JButton("Go back");
		searchButton = new JButton("Search");
		buttons.add(goBackButton);
		buttons.add(searchButton);
		cont.add(searches, BorderLayout.CENTER);
		cont.add(buttons, BorderLayout.SOUTH);
		this.setContentPane(cont);
		this.setSize(400, 500);
		
		zipCodeField.setVisible(false);
		iniDate.setVisible(false);
		endDate.setVisible(false);
		avgRatingField.setVisible(false);
		offerTypeBox.setVisible(false);
		this.setVisible(false);
	}

	public void setBoxController(SearchBoxController s) {
		options.addItemListener(s);
	}
	
	public void setSearchController(ActionListener b) {
		searchButton.addActionListener(b);
		
	}
	
	public void setGoBackController(GoBackController g) {
		this.goBackButton.addActionListener(g);
	}
	
	public String getCurrentBoxOption() {
		return (String)this.options.getSelectedItem();
	}
	
	public void setVisibleZipCodeField() {
		this.offerTypeBox.setVisible(false);
		this.avgRatingField.setVisible(false);
		this.iniDate.setVisible(false);
		this.endDate.setVisible(false);
		this.zipCodeField.setVisible(true);
	}
	
	public void setVisibleOfferTypeBox() {
		this.zipCodeField.setVisible(false);
		this.avgRatingField.setVisible(false);
		this.iniDate.setVisible(false);
		this.endDate.setVisible(false);
		this.offerTypeBox.setVisible(true);
	}
	
	public void setVisibleDates() {
		this.zipCodeField.setVisible(false);
		this.avgRatingField.setVisible(false);
		this.offerTypeBox.setVisible(false);
		this.iniDate.setVisible(true);
		this.endDate.setVisible(true);

	}
	
	public void setVisibleRating() {
		this.zipCodeField.setVisible(false);
		this.offerTypeBox.setVisible(false);
		this.iniDate.setVisible(false);
		this.endDate.setVisible(false);
		this.avgRatingField.setVisible(true);

	}
	
	public void hideAll() {
		this.zipCodeField.setVisible(false);
		this.avgRatingField.setVisible(false);
		this.iniDate.setVisible(false);
		this.endDate.setVisible(false);
		this.offerTypeBox.setVisible(false);
	}
	
	public JComboBox<String> getOfferTypeBox() {
		return this.offerTypeBox;
	}
	
	public JTextField getZipCodeField() {
		return this.zipCodeField;
	}
	
	public JCalendar getIniDate() {
		return this.iniDate;
	}
	
	public JCalendar getEndDate() {
		return this.endDate;
	}
	
	public JTextField getAvgRatingField() {
		return this.avgRatingField;
	}
	
	public void setUp() {
		this.setVisible(true);
		iniDate.setVisible(false);
		endDate.setVisible(false);
		avgRatingField.setVisible(false);
		offerTypeBox.setVisible(false);
		zipCodeField.setVisible(true);
	}
}
