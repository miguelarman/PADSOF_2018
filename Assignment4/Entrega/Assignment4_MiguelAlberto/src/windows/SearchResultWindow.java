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
import controllers.SearchResultController;
import exceptions.NoRowSelectedException;
import windows.tableModels.SearchResultTableModel;

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class SearchResultWindow extends JFrame {
	
	/**
	* ID needed for the class to be Serializable
	*/
	private static final long serialVersionUID = -2518801928619944684L;
	
	/**
	 * Button to view the selected offer
	 */
	private JButton viewOffer;
	
	/**
	 * Button to go to the previous window
	 */
	private JButton goBackButton;
	
	/**
	 * Structure where the results for the search are deployed
	 */
	private JTable table;
	
	/**
	 * Model to show the results of a search
	 */
	private SearchResultTableModel dataModel;
	
	/**
	 * Constructor of the class SearchResultWindow
	 * @param list Result of the search
	 */
	public SearchResultWindow(List<Offer> list) {
		super("Search result");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("Here are the offers you have searched for:");
		cont.add(label, BorderLayout.NORTH);
		
		dataModel = new SearchResultTableModel(list);

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
		
		this.setSize(750, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}
	
	/**
	 * Method that assigns the goBackButton with the goBackController
	 * @param g Controller that allows you to go to the previous window
	 */
	public void setGoBackController(GoBackController g) {
		this.goBackButton.addActionListener(g);
	}
	
	/**
	 * Method that assigns the viewOfferButton with the SearchResultController
	 * @param c Controller that allows you to do the needed functionality
	 */
	public void setController(SearchResultController c) {
		this.viewOffer.addActionListener(c);
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
	 * Method that removes an offer from the table
	 * @param o Offer to be removed
	 */
	public void removeOffer(Offer o) {
		SearchResultTableModel model = (SearchResultTableModel)table.getModel();
		model.removeOffer(o);
	}

}
