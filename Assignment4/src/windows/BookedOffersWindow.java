package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import application.offer.Reservation;
import controllers.BookedOffersController;
import controllers.GoBackController;
import exceptions.NoRowSelectedException;
import windows.tableModels.ReservationTableModel;

public class BookedOffersWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7510882296147244766L;
	private JButton viewOffer;
	private JButton goBackButton;
	private JButton payButton;
	private JTable table;
	private ReservationTableModel dataModel;
	
	public BookedOffersWindow(List<Reservation> list) {
		super("Booked offers");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		JPanel buttons = new JPanel(new GridLayout(2, 1));
		JLabel label = new JLabel("Here are the offers you have searched for:");
		cont.add(label, BorderLayout.NORTH);
		
		dataModel = new ReservationTableModel(list);

		this.table = new JTable(dataModel);
		
		table.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollBar = new JScrollPane(table);

		JPanel tablePanel = new JPanel();
		tablePanel.add(scrollBar, BorderLayout.CENTER);
		
		payButton = new JButton("Pay reservation");
		buttons.add(payButton);
		
		viewOffer = new JButton("View offer");
		buttons.add(viewOffer);
		
		tablePanel.add(buttons, BorderLayout.EAST);
		cont.add(tablePanel, BorderLayout.CENTER);
		
		this.goBackButton = new JButton("Go back");
		cont.add(this.goBackButton, BorderLayout.SOUTH);
		
		this.setSize(750, 500);
		this.setVisible(false);
	}
	
	public void setGoBackController(GoBackController g) {
		this.goBackButton.addActionListener(g);
	}
	
	public void setController(BookedOffersController c) {
		this.viewOffer.addActionListener(c);
		this.payButton.addActionListener(c);
	}
	
	public Reservation getSelection() throws NoRowSelectedException {
		int selectedRow = this.table.getSelectedRow();
		
		return this.dataModel.getRow(selectedRow);
	}

}
