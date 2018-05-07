package windows.tableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import application.offer.Offer;
import application.offer.OfferStatus;
import exceptions.NoRowSelectedException;

/**
 * Table model for the tables used to present the offers from a specific user. It has six columns:
 * <ul>
 * <li>ZIP code</li>
 * <li>Starting date</li>
 * <li>Price</li>
 * <li>Average rating</li>
 * <li>Type of offer</li>
 * <li>Satus of the offer</li>
 * </ul>
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class MyOffersTableModel extends AbstractTableModel {

	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = -6080066385334434387L;
	/**
	 * Names of the column titles
	 */
	private Object[] titles;
	/**
	 * All the contents of the table. Each offer divided in fields
	 */
	private Object[][] contents;
	/**
	 * Array of all the offers that are represented in the table. Needed to return the selected one
	 */
	private Offer[] offersArray;

	/**
	 * Constructor of the model
	 * 
	 * @param offers List of offers to be presented
	 */
	public MyOffersTableModel(List<Offer> offers) {
		Object[] titles = {"ZIP code", "Starting on", "Price", "Rating", "Offer type", "Status"};
		this.titles = titles;

		Object[][] contents = new Object[offers.size()][this.titles.length];
		offersArray = new Offer[offers.size()];
		
		int i = 0;
		
		for (Offer o : offers) {
			String strStatus;
			
			if(o.getStatus().equals(OfferStatus.PENDING_FOR_APPROVAL)) {
				strStatus = "APPROVAL";
			} else if(o.getStatus().equals(OfferStatus.PENDING_FOR_CHANGES)) {
				strStatus = "CHANGES";
			} else {
				strStatus = o.getStatus().toString(); 
			}
			Object[] offer = {o.getHouse().getZipCode(), o.getDate(), o.getAmount(), o.getAvgRating(), o.getType(), strStatus};

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

	/**
	 * Method that returns the offer that is presented in an specific row
	 * 
	 * @param selectedRow Index of the row we want to return
	 * @return Selected offer
	 * @throws NoRowSelectedException When no row is selected
	 */
	public Offer getRow(int selectedRow) throws NoRowSelectedException {
		if (selectedRow >= this.offersArray.length || selectedRow < 0) {
			throw new NoRowSelectedException();
		} else {
			return offersArray[selectedRow];
		}
	}

	/**
	 * Method that manually removes an offer from the table. It is used because it
	 * is more efficient than building a whole new table
	 * 
	 * @param offer Offer to be removed
	 */
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

	/**
	 * Method that manually adds an offer to the table. It is used because it is
	 * more efficient than building a whole new table
	 * 
	 * @param o Offer to be added
	 */
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
		
		String strStatus;
		
		if(o.getStatus().equals(OfferStatus.PENDING_FOR_APPROVAL)) {
			strStatus = "APPROVAL";
		} else if(o.getStatus().equals(OfferStatus.PENDING_FOR_CHANGES)) {
			strStatus = "CHANGES";
		} else {
			strStatus = o.getStatus().toString(); 
		}
		Object[] newOffer = {o.getHouse().getZipCode(), o.getDate(), o.getAmount(), o.getAvgRating(), o.getType(), strStatus};
		newContents[this.contents.length] = newOffer;
		newOffersArray[this.offersArray.length] = o;
		
		this.contents = newContents;
		this.offersArray = newOffersArray;
		
		this.fireTableDataChanged();
	}

	/**
	 * Method that automatically refreshes the status field of all the offers in the table
	 */
	public void refreshStatus() {
		for (int i = 0; i < contents.length; i++) {
			String strStatus;
			
			if (offersArray[i].getStatus().equals(OfferStatus.PENDING_FOR_APPROVAL)) {
				strStatus = "APPROVAL";
			} else if(offersArray[i].getStatus().equals(OfferStatus.PENDING_FOR_CHANGES)) {
				strStatus = "CHANGES";
			} else {
				strStatus = offersArray[i].getStatus().toString(); 
			}
			contents[i][5] = strStatus;
		}
		
		this.fireTableDataChanged();
	}
}
