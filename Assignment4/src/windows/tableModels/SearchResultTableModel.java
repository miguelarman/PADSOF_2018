package windows.tableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import application.offer.Offer;
import exceptions.NoRowSelectedException;

public class SearchResultTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6080066385334434387L;
	// In this example table data are attributes of the class
	private Object[] titles;
	private Object[][] contents;
	private Offer[] offersArray;

	public SearchResultTableModel(List<Offer> offers) {
		// Create an array of column titles
		// TODO
		Object[] titles = {"ZIP code", "Starting date", "Price", "Average rating"};
		this.titles = titles;

		// Create a matrix of table contents
		Object[][] contents = new Object[offers.size()][4];
		offersArray = new Offer[offers.size()];
		
		int i = 0;
		
		for (Offer o : offers) { // TODO
			Object[] offer = {o.getHouse().getZipCode(), o.getDate(), o.getAmount(), o.getAvgRating()};

			contents[i] = offer;
			offersArray[i] = o;
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

	public Offer getRow(int selectedRow) throws NoRowSelectedException {
		if (selectedRow >= this.offersArray.length || selectedRow < 0) {
			throw new NoRowSelectedException();
		} else {
			return offersArray[selectedRow];
		}
	}

	public void removeOffer(Offer offer) {
		int index = 0;
		
		Object[][] newContents = new Object[this.contents.length - 1][this.titles.length];
		Offer[] newOffersArray = new Offer[this.contents.length - 1];
		
		for (int i = 0; i < this.contents.length; i++) {
			// We delete the offer that has been approved or denied from the table
			if (this.offersArray[i] != offer) {
				for (int j = 0; j < this.titles.length; j++) { // Copy of all the fields of the object
					newContents[index][j] = this.contents[i][j];
				}
				
				newOffersArray[index] = this.offersArray[i];
				index++;
			}
		}
		
		this.contents = newContents;
		this.offersArray = newOffersArray;
		
		this.fireTableDataChanged();
	}

	public void addOfferToTable(Offer o) {
		Object[][] newContents = new Object[this.contents.length + 1][this.titles.length];
		Offer[] newOffersArray = new Offer[this.contents.length + 1];
		
		// We copy the existing contents
		for (int i = 0; i < this.contents.length; i++) {
			for (int j = 0; j < this.titles.length; j++) {
				newContents[i][j] = this.contents[i][j];
			}
			
			newOffersArray[i] = this.offersArray[i];
		}
		Object[] newOffer = {o.getHouse().getZipCode(), o.getDate(), o.getAmount(), o.getAvgRating()};
		newContents[this.contents.length] = newOffer;
		newOffersArray[this.offersArray.length] = o;
		
		this.contents = newContents;
		this.offersArray = newOffersArray;
		
		this.fireTableDataChanged();
	}
}