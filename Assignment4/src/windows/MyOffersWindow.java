package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import application.offer.Offer;
import controllers.GoBackController;
import controllers.MyOffersController;
import windows.tableModels.SearchResultTableModel;

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class MyOffersWindow extends JFrame {

	/**
	* ID needed for the class to be Serializable
	*/
	private static final long serialVersionUID = -1609869068714772919L;
	
	/**
	 * Model to show the offers of the logged user
	 */
	private SearchResultTableModel dataModel;
	
	/**
	 * Structure to deploy the offers of the logged user
	 */
	private JTable table;
	
	/**
	 * Button to go back to the previous window
	 */
	private JButton goBackButton;
	
	/**
	 * Button to see the information of the selected offer
	 */
	private JButton viewOffer;

	/**
	 * Constructor of the class MyOffersWindow
	 * @param offers List of offers created by the logged user
	 */
	public MyOffersWindow(List<Offer> offers) {
		super("My offers");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		
		JLabel label = new JLabel("Here are the offers you have created:");
		cont.add(label, BorderLayout.NORTH);
		
		dataModel = new SearchResultTableModel(offers);

		this.table = new JTable(dataModel);
		
		table.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollBar = new JScrollPane(table);

		JPanel tablePanel = new JPanel();
		tablePanel.add(scrollBar, BorderLayout.CENTER);
		
		viewOffer = new JButton("View offer");
		tablePanel.add(viewOffer, BorderLayout.EAST);

		cont.add(tablePanel, BorderLayout.CENTER);
		
		this.goBackButton = new JButton("Go back");
		cont.add(this.goBackButton, BorderLayout.SOUTH);
				
		this.setSize(400, 400);
		this.setVisible(false);
	}
	
	/**
	 * Method that assigns the viewOffer with the MyOffersController 
	 * @param c Controller that allows you to do the needed functionality
	 */
	public void setController(MyOffersController o) {
		this.viewOffer.addActionListener(o);
	}
	
	/**
	 * Method that assigns the goBackButton with the goBackController
	 * @param gb Controller that allows you to go to the previous window
	 */
	public void setGoBackController(GoBackController gb) {
		this.goBackButton.addActionListener(gb);
	}

}
