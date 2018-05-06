package windows.tableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import application.offer.Reservation;
import exceptions.NoRowSelectedException;

public class ReservationTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6392354398619616364L;
	private Object[] titles;
	private Object[][] contents;
	private Reservation[] reservationsArray;

	public ReservationTableModel(List<Reservation> bookedOffers) {
		// Create an array of column titles
		// TODO
		Object[] titles = {"ZIP code", "Booking date", "Price", "Average rating"};
		this.titles = titles;

		// Create a matrix of table contents
		Object[][] contents = new Object[bookedOffers.size()][4];
		reservationsArray = new Reservation[bookedOffers.size()];
		
		int i = 0;
		
		for (Reservation r : bookedOffers) { // TODO
			Object[] offer = {r.getBookedOffer().getHouse().getZipCode(), r.getBookingDate(), r.getBookedOffer().getAmount(), r.getBookedOffer().getAvgRating()};

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

	public Reservation getRow(int selectedRow) throws NoRowSelectedException {
		if (selectedRow >= this.reservationsArray.length || selectedRow < 0) {
			throw new NoRowSelectedException();
		} else {
			return reservationsArray[selectedRow];
		}
	}

	public void removeReservation(Reservation reservation) {
		int index = 0;
		
		Object[][] newContents = new Object[this.contents.length - 1][2];
		Reservation[] newReservationsArray = new Reservation[this.contents.length - 1];
		
		for (int i = 0; i < this.contents.length; i++) {
			// We delete the offer that has been approved or denied from the table
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
