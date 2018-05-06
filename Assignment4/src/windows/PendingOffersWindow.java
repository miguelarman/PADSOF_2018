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
import windows.tableModels.MyHousesTableModel;
import windows.tableModels.SearchResultTableModel;

public class PendingOffersWindow extends JFrame {

	/**
	 * 
	 */
	private JButton viewOfferButton;
	private JButton goBackButton;
	private SearchResultTableModel dataModel;
	private JTable table;
	private static final long serialVersionUID = 8434334879116180704L;
	
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

	public void setGoBackController(GoBackController gb) {
		this.goBackButton.addActionListener(gb);
		
	}

	public void setController(PendingOffersController p) {
		this.viewOfferButton.addActionListener(p);
		
	}
	
	public Offer getSelection() throws NoRowSelectedException {
		int selectedRow = this.table.getSelectedRow();
		
		return this.dataModel.getRow(selectedRow);
	}

	public void deleteOfferFromTable(Offer offer) {
		SearchResultTableModel model = (SearchResultTableModel) this.table.getModel();
		model.removeOffer(offer);
	}

}
