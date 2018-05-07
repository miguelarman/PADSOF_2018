package windows.tableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import application.offer.Reservation;
import exceptions.NoRowSelectedException;

/**
 * Table model for the tables used to present the offers that a user has booked. It has three columns:
 * <ul>
 * <li>ZIP code</li>
 * <li>Booking date</li>
 * <li>Price</li>
 * </ul>
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class ReservationTableModel extends AbstractTableModel {
	
	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = 6392354398619616364L;
	/**
	 * Names of the column titles
	 */
	private Object[] titles;
	/**
	 * All the contents of the table. Each reservation divided in fields
	 */
	private Object[][] contents;
	/**
	 * Array of all the reservations that are represented in the table. Needed to return the selected one
	 */
	private Reservation[] reservationsArray;

	/**
	 * Constructor of the model
	 * 
	 * @param bookedOffers List of reservations to be presented
	 */
	public ReservationTableModel(List<Reservation> bookedOffers) {
		
		Object[] titles = {"ZIP code", "Booking date", "Price"};
		this.titles = titles;

		Object[][] contents = new Object[bookedOffers.size()][this.titles.length];
		reservationsArray = new Reservation[bookedOffers.size()];
		
		int i = 0;
		
		for (Reservation r : bookedOffers) {
			Object[] offer = {r.getBookedOffer().getHouse().getZipCode(), r.getBookingDate(), r.getBookedOffer().getAmount()};

			contents[i] = offer;
			reservationsArray[i] = r;
			i++;
		}
		
		this.contents = contents;
	}

	@Override
	public int getColumnCount() {
		return this.titles.length;
	}

	@Override
	public int getRowCount() {
		return this.contents.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.contents[rowIndex][columnIndex];
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	@Override
	public String getColumnName(int col) {
		return (String) this.titles[col];
	}

	/**
	 * Method that returns the reservation that is presented in an specific row
	 * 
	 * @param selectedRow Index of the row we want to return
	 * @return Selected reservation
	 * @throws NoRowSelectedException When no row is selected
	 */
	public Reservation getRow(int selectedRow) throws NoRowSelectedException {
		if (selectedRow >= this.reservationsArray.length || selectedRow < 0) {
			throw new NoRowSelectedException();
		} else {
			return reservationsArray[selectedRow];
		}
	}

	/**
	 * Method that manually removes an reservation from the table. It is used because it
	 * is more efficient than building a whole new table
	 * 
	 * @param reservation Reservation to be removed
	 */
	public void removeReservation(Reservation reservation) {
		int index = 0;
		
		Object[][] newContents = new Object[this.contents.length - 1][2];
		Reservation[] newReservationsArray = new Reservation[this.contents.length - 1];
		
		for (int i = 0; i < this.contents.length; i++) {
			if (this.reservationsArray[i] != reservation) {
				for (int j = 0; j < this.titles.length; j++) { // Copy of all the fields of the object
					newContents[index][j] = this.contents[i][j];
				}
				
				newReservationsArray[index] = this.reservationsArray[i];
				index++;
			}
		}
		
		this.contents = newContents;
		this.reservationsArray = newReservationsArray;
		
		this.fireTableDataChanged();
	}
}
