package windows.tableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import application.offer.House;
import application.opinion.Comment;
import application.opinion.Opinion;
import application.opinion.Rating;
import exceptions.NoRowSelectedException;

public class MyHousesTableModel extends AbstractTableModel {

	private Object[] titles;
	private House[] housesArray;
	private Object[][] contents;

	public MyHousesTableModel(List<House> houses) {
		// Create an array of column titles
		// TODO
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

	public House getRow(int selectedRow) throws NoRowSelectedException {
		if (selectedRow >= this.housesArray.length || selectedRow < 0) {
			throw new NoRowSelectedException();
		} else {
			return housesArray[selectedRow];
		}
	}

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
