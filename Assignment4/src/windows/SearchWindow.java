package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import application.dates.ModifiableDate;
import application.users.RegisteredUser;
import controllers.GoBackController;
import controllers.SearchBoxController;
import controllers.SearchController;

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class SearchWindow extends JFrame {
	
	/**
	* ID needed for the class to be Serializable
	*/
	private static final long serialVersionUID = 5035536669428890618L;
	
	/**
	 * JComboBox that shows the types of searches
	 */
	final private JComboBox<String> options;
	
	/**
	 * Button to search
	 */
	private JButton searchButton;
	
	/**
	 * Button to go to the previous window
	 */
	private JButton goBackButton;
	
	/**
	 * Field to introduce a ZIP code to search for
	 */
	final private JTextField zipCodeField;
	
	/**
	 * JComboBox that shows the types of offers
	 */
	final private JComboBox<String> offerTypeBox;
	
	/**
	 * Field to introduce a average rating to search for
	 */
	final private JTextField avgRatingField;
	
	/**
	 * Calendar to introduce the initial date to search for
	 */
	private JCalendar iniDate;
	
	/**
	 * Calendar to introduce the ending date to search for
	 */
	private JCalendar endDate;
	
	/**
	 * Constructor of the class SearchWindow
	 * @param user User that is logged
	 */
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
		zipCodeField.setSize(new Dimension(50,100));
		zipCodeField.setBounds(100, 150, 200, 35);
		searches.add(zipCodeField, BorderLayout.CENTER);
		
		offerTypeBox = new JComboBox<String>(typesOfOffer);
		offerTypeBox.setBounds(100, 150, 200, 35);
		searches.add(offerTypeBox, BorderLayout.CENTER);
		
		avgRatingField = new JTextField(10);
		avgRatingField.setSize(new Dimension(50,100));
		avgRatingField.setBounds(100, 150, 200, 35);
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
		this.setLocationRelativeTo(null);
		
		zipCodeField.setVisible(false);
		iniDate.setVisible(false);
		endDate.setVisible(false);
		avgRatingField.setVisible(false);
		offerTypeBox.setVisible(false);
		this.setVisible(false);
	}

	/**
	 * Method to set up the controller for the options comboBox
	 * @param s Controller that contains an itemListener for the options comboBox
	 */
	public void setBoxController(SearchBoxController s) {
		options.addItemListener(s);
	}
	
	/**
	 * Method that assigns the searchButton with the SearchController
	 * @param b Controller that allows you to do the needed functionality
	 */
	public void setSearchController(SearchController b) {
		searchButton.addActionListener(b);
		
	}
	
	/**
	 * Method that assigns the goBackButton with the goBackController
	 * @param g Controller that allows you to go to the previous window
	 */
	public void setGoBackController(GoBackController g) {
		this.goBackButton.addActionListener(g);
	}
	
	/**
	 * Method that returns the current selected option in options
	 * @return Selected type of search
	 */
	public String getCurrentBoxOption() {
		return (String)this.options.getSelectedItem();
	}
	
	/**
	 * Method that puts visible the zipCodeField and hides all the other ones
	 */
	public void setVisibleZipCodeField() {
		this.offerTypeBox.setVisible(false);
		this.avgRatingField.setVisible(false);
		this.iniDate.setVisible(false);
		this.endDate.setVisible(false);
		this.zipCodeField.setVisible(true);
	}
	
	/**
	 * Method that puts visible the offerTypeBox and hides all the other ones
	 */
	public void setVisibleOfferTypeBox() {
		this.zipCodeField.setVisible(false);
		this.avgRatingField.setVisible(false);
		this.iniDate.setVisible(false);
		this.endDate.setVisible(false);
		this.offerTypeBox.setVisible(true);
	}
	
	/**
	 * Method that puts visible iniDate and endDate and hides all the other ones
	 */
	public void setVisibleDates() {
		this.zipCodeField.setVisible(false);
		this.avgRatingField.setVisible(false);
		this.offerTypeBox.setVisible(false);
		this.iniDate.setVisible(true);
		this.endDate.setVisible(true);

	}
	
	/**
	 * Method that puts visible the avgRatingField and hides all the other ones
	 */
	public void setVisibleRating() {
		this.zipCodeField.setVisible(false);
		this.offerTypeBox.setVisible(false);
		this.iniDate.setVisible(false);
		this.endDate.setVisible(false);
		this.avgRatingField.setVisible(true);

	}
	
	/**
	 * Method that hides all the fields
	 */
	public void hideAll() {
		this.zipCodeField.setVisible(false);
		this.avgRatingField.setVisible(false);
		this.iniDate.setVisible(false);
		this.endDate.setVisible(false);
		this.offerTypeBox.setVisible(false);
	}
	
	/**
	 * Getter method for the offerTypeBox
	 * @return offerTypeBox
	 */
	public JComboBox<String> getOfferTypeBox() {
		return this.offerTypeBox;
	}
	
	/**
	 * Getter method for the zipCodeField
	 * @return zipCodeField
	 */
	public JTextField getZipCodeField() {
		return this.zipCodeField;
	}
	
	/**
	 * Getter method for the iniDate
	 * @return iniDate
	 */
	public JCalendar getIniDate() {
		return this.iniDate;
	}
	
	/**
	 * Getter method for the endDate
	 * @return endDate
	 */
	public JCalendar getEndDate() {
		return this.endDate;
	}
	
	/**
	 * Getter method for the avgRatingField
	 * @return avgRatingField
	 */
	public JTextField getAvgRatingField() {
		return this.avgRatingField;
	}
	
	/**
	 * Method that prepares the visibility of all the elements when the window is showed for the first time
	 */
	public void setUp() {
		this.setVisible(true);
		iniDate.setVisible(false);
		endDate.setVisible(false);
		avgRatingField.setVisible(false);
		offerTypeBox.setVisible(false);
		zipCodeField.setVisible(true);
	}
}
