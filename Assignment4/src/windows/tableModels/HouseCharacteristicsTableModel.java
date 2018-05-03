package windows.tableModels;

import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import application.offer.Offer;
import exceptions.NoRowSelectedException;

public class HouseCharacteristicsTableModel extends AbstractTableModel {
	
//	private Object[][] characteristicsArray;
	private Object[] titles;
	private Object[][] contents;

	public HouseCharacteristicsTableModel(HashMap<String, String> chs) {
		// Create an array of column titles
		// TODO
		Object[] titles = {"Characteristic", "Description"};
		this.titles = titles;

		// Create a matrix of table contents
		Object[][] contents = new Object[chs.size()][2];
//		characteristicsArray = new Object[chs.size()][2];
		
		int i = 0;
		
		for (String ch : chs.keySet()) { // TODO
			Object[] characteristic = {ch, chs.get(ch)};

			contents[i] = characteristic;
//			characteristicsArray[i] = characteristic;
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
	
	public void addCharacteristic(String ch, String de) {
		Object[][] newContents = new Object[this.contents.length + 1][2];
		
		for (int i = 0; i < this.contents.length; i++) {
			newContents[i][0] = this.contents[i][0];
			newContents[i][1] = this.contents[i][1];
		}
		
		newContents[this.contents.length][0] = ch;
		newContents[this.contents.length][1] = de;
		
		this.contents = newContents;
		
		this.fireTableDataChanged();
	}

	/*public Object[] getRow(int selectedRow) throws NoRowSelectedException {
		if (selectedRow >= this.characteristicsArray.length || selectedRow < 0) {
			throw new NoRowSelectedException();
		} else {
			return characteristicsArray[selectedRow];
		}
	}*/

}
