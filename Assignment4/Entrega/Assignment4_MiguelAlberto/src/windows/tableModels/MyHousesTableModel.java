package windows.tableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import application.offer.House;
import exceptions.NoRowSelectedException;

/**
 * Table model for the tables used to present the houses an specific user has created. It has two columns:
 * <ul>
 * <li>ZIP code</li>
 * <li>City</li>
 * </ul>
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class MyHousesTableModel extends AbstractTableModel {

	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = -4487011468881467877L;
	/**
	 * Names of the column titles
	 */
	private Object[] titles;
	/**
	 * Array of all the houses that are represented in the table. Needed to return the selected one
	 */
	private House[] housesArray;
	/**
	 * All the contents of the table. Each house divided in fields
	 */
	private Object[][] contents;

	
	/**
	 * Constructor of the model
	 * 
	 * @param houses List of houses to be presented
	 */
	public MyHousesTableModel(List<House> houses) {
		
		Object[] titles = { "ZIP code", "City" };
		this.titles = titles;

		// Create a matrix of table contents
		Object[][] contents = new Object[houses.size()][titles.length];
		housesArray = new House[houses.size()];

		int i = 0;

		for (House h : houses) {

			Object[] house = {h.getZipCode(), h.getCity()};

			contents[i] = house;
			housesArray[i] = h;
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
	 * Method that returns the house that is presented in an specific row
	 * 
	 * @param selectedRow Index of the row we want to return
	 * @return Selected house
	 * @throws NoRowSelectedException When no row is selected
	 */
	public House getRow(int selectedRow) throws NoRowSelectedException {
		if (selectedRow >= this.housesArray.length || selectedRow < 0) {
			throw new NoRowSelectedException();
		} else {
			return housesArray[selectedRow];
		}
	}

	/**
	 * Method that manually adds an house to the table. It is used because it is
	 * more efficient than building a whole new table
	 * 
	 * @param h House to be added
	 */
	public void addHouse(House h) {
		Object[][] newContents = new Object[this.contents.length + 1][2];

		for (int i = 0; i < this.contents.length; i++) {
			newContents[i][0] = this.contents[i][0];
			newContents[i][1] = this.contents[i][1];
		}
		
		newContents[this.contents.length][0] = h.getZipCode();
		newContents[this.contents.length][1] = h.getCity();
		
		House[] newHousesArray = new House[this.housesArray.length + 1];
		for (int i = 0; i < this.housesArray.length; i++) {
			newHousesArray[i] = this.housesArray[i];
		}
		
		newHousesArray[this.housesArray.length] = h;
		
		this.housesArray = newHousesArray;
		
		this.contents = newContents;
		
		this.fireTableDataChanged();
	}

}
