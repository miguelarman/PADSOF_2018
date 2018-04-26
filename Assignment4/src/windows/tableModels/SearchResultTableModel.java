package windows.tableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import application.offer.Offer;

public class SearchResultTableModel extends AbstractTableModel {

	// In this example table data are attributes of the class
	private Object[] titles;
	private Object[][] contents;

	// In this example the constructor initializes the attributes
	public SearchResultTableModel(List<Offer> offers) {
		// Create an array of column titles
		// TODO
		Object[] titles = {"ZIP code", "Starting date", "Price", "Average rating"};
		this.titles = titles;

		// Create a matrix of table contents
		Object[][] contents = new Object[offers.size()][4];
		
		int i = 0;
		
		for (Offer o : offers) {
			Object[] offer = {/*o.getHouse().getZipCode()*/"house", o.getDate(), o.getAmount(), o.getAvgRating()};

			contents[i] = offer;
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
}