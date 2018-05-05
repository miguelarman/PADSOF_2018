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

public class MyOffersWindow extends JFrame {

	private SearchResultTableModel dataModel;
	private JTable table;
	private JButton goBackButton;
	private JButton viewOffer;

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

	public void setController(MyOffersController o) {
		this.viewOffer.addActionListener(o);
	}

	public void setGoBackController(GoBackController gb) {
		this.goBackButton.addActionListener(gb);
	}

}
