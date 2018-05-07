package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.offer.Offer;
import controllers.GoBackController;
import controllers.WatchOfferController;

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class WatchOfferWindow extends JFrame{
	
	/**
	* ID needed for the class to be Serializable
	*/
	private static final long serialVersionUID = -4624058926786461881L;
	
	/**
	 * Offer displayed on screen
	 */
	private Offer offer;
	
	/**
	 * Button to view the house of the offer
	 */
	private JButton viewHouseButton;
	
	/**
	 * Button to approve the offer
	 */
	private JButton approveButton;
	
	/**
	 * Button to deny the offer
	 */
	private JButton denyButton;
	
	/**
	 * Button to suggest changes
	 */
	private JButton suggestChangesButton;
	
	/**
	 * Button to go to the previous window
	 */
	private JButton goBackButton;
	
	/**
	 * Constructor of the class WatchOfferWindow
	 * @param offer Offer pending for approval
	 */
	public WatchOfferWindow(Offer offer) {
		super("Offer");
		
		this.offer = offer;
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		JLabel titleLabel = new JLabel("Revise this offer:");
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 20));
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(titleLabel);
		cont.add(titlePanel, BorderLayout.NORTH);
		
		JPanel offerPanel = new JPanel();;
		
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
		approveButton = new JButton("Approve");
		buttonsPanel.add(approveButton);
		denyButton = new JButton("Deny");
		buttonsPanel.add(denyButton);
		suggestChangesButton = new JButton("Suggest changes");
		buttonsPanel.add(suggestChangesButton);
		goBackButton = new JButton("Go back");
		buttonsPanel.add(goBackButton);
		cont.add(buttonsPanel, BorderLayout.SOUTH);
		
		// We add left and right margins
		JPanel leftMargin = new JPanel(); 
		JPanel rightMargin = new JPanel();
		leftMargin.setPreferredSize(new Dimension(50, 0));
		rightMargin.setPreferredSize(new Dimension(50, 0));
		cont.add(leftMargin, BorderLayout.WEST);
		cont.add(rightMargin, BorderLayout.EAST);
		
		
		this.setSize(600,  400);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	/**
	 * Method that assigns all the buttons with the WatchOfferController
	 * @param c Controller that allows you to do the needed functionality
	 */
	public void setController(WatchOfferController c) {
		approveButton.addActionListener(c);
		denyButton.addActionListener(c);
		suggestChangesButton.addActionListener(c);
		viewHouseButton.addActionListener(c);
		
	}

	/**
	 * Method that assigns the goBackButton with the goBackController
	 * @param g Controller that allows you to go to the previous window
	 */
	public void setGoBackController(GoBackController g) {
		goBackButton.addActionListener(g);
	}
	
	/**
	 * Getter method for the offer
	 * @return Offer to be approved
	 */
	public Offer getOffer() {
		return this.offer;
	}
}
