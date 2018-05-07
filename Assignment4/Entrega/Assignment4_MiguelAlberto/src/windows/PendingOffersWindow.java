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
import controllers.PendingOffersController;
import exceptions.NoRowSelectedException;
import windows.tableModels.SearchResultTableModel;

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class PendingOffersWindow extends JFrame {

	/**
	* ID needed for the class to be Serializable
	*/
	private static final long serialVersionUID = 8434334879116180704L;
	
	/**
	 * Button to view the selected offer
	 */
	private JButton viewOfferButton;
	
	/**
	 * Button to go to the previous window
	 */
	private JButton goBackButton;
	
	/**
	 * Model to show the offers pending for approval
	 */
	private SearchResultTableModel dataModel;
	
	/**
	 * Structure to display the offers pending for approval
	 */
	private JTable table;

	/**
	 * Constructor of the class PendingOfferWindow
	 * @param list List of offers pending for approval
	 */
	public PendingOffersWindow(List<Offer> list) {
		super("Pending for approval");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("Here are the offers pending for approval:");
		cont.add(label, BorderLayout.NORTH);
		
		dataModel = new SearchResultTableModel(list);

		this.table = new JTable(dataModel);
		
		table.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollBar = new JScrollPane(table);

		JPanel tablePanel = new JPanel();
		tablePanel.add(scrollBar, BorderLayout.CENTER);
		
		viewOfferButton = new JButton("View offer");
		tablePanel.add(viewOfferButton, BorderLayout.EAST);

		cont.add(tablePanel, BorderLayout.CENTER);
		
		this.goBackButton = new JButton("Go back");
		cont.add(this.goBackButton, BorderLayout.SOUTH);
		
		this.setSize(750, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	/**
	 * Method that assigns the goBackButton with the goBackController
	 * @param gb Controller that allows you to go to the previous window
	 */
	public void setGoBackController(GoBackController gb) {
		this.goBackButton.addActionListener(gb);
		
	}
	
	/**
	 * Method that assigns the viewOfferButton with the PendingOffersController
	 * @param p Controller that allows you to do the needed functionality
	 */
	public void setController(PendingOffersController p) {
		this.viewOfferButton.addActionListener(p);
		
	}
	
	/**
	 * Method that gives you the selected row of the table
	 * @return The selected offer in the table
	 * @throws NoRowSelectedException When no row has been selected
	 */
	public Offer getSelection() throws NoRowSelectedException {
		int selectedRow = this.table.getSelectedRow();
		
		return this.dataModel.getRow(selectedRow);
	}

	/**
	 * Method to delete an offer from the table
	 * @param offer Offer to be deleted
	 */
	public void deleteOfferFromTable(Offer offer) {
		SearchResultTableModel model = (SearchResultTableModel) this.table.getModel();
		model.removeOffer(offer);
	}

}
