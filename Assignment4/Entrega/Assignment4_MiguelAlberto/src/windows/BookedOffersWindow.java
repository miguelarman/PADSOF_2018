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

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class BookedOffersWindow extends JFrame {

	/**
	 * ID needed for the class to be Serializable
	 */
	private static final long serialVersionUID = 7510882296147244766L;
	
	/**
	 * Button to view the selected offer
	 */
	private JButton viewOffer;
	
	/**
	 * Button to go back to the previous window
	 */
	private JButton goBackButton;
	
	/**
	 * Button to pay the selected reservation
	 */
	private JButton payButton;
	
	/**
	 * Structure to deploy the reservations
	 */
	private JTable table;
	
	/**
	 * Model to show the reservations
	 */
	private ReservationTableModel dataModel;
	
	/**
	 * Constructor of the class BookedOffersWindow
	 * @param list List of the reservations of the loggedUser
	 */
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
	 * Method that assigns the viewOffer and the payButton with the BookedOffersController 
	 * @param c Controller that allows you to do the needed functionality
	 */
	public void setController(BookedOffersController c) {
		this.viewOffer.addActionListener(c);
		this.payButton.addActionListener(c);
	}
	
	/**
	 * Method that gives you the selected row of the table
	 * @return The selected reservation in the table
	 * @throws NoRowSelectedException When no row has been selected
	 */
	public Reservation getSelection() throws NoRowSelectedException {
		int selectedRow = this.table.getSelectedRow();
		
		return this.dataModel.getRow(selectedRow);
	}

	/**
	 * Method that removes a reservation from the window
	 * 
	 * @param r Reservation to be removed
	 */
	public void removeReservation(Reservation r) {
		ReservationTableModel model = (ReservationTableModel)table.getModel();
		
		model.removeReservation(r);
		
	}

}
