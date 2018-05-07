package windows.tableModels;

import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

/**
 * Table model for the tables used to present the characteristics of a house. It has two columns:
 * <ul>
 * <li>Characteristic</li>
 * <li>Description</li>
 * </ul>
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class HouseCharacteristicsTableModel extends AbstractTableModel {
	
	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = 1044486726763898935L;
	/**
	 * Names of the column titles
	 */
	private Object[] titles;
	/**
	 * All the contents of the table. Each offer divided in fields
	 */
	private Object[][] contents;

	/**
	 * Constructor of the model
	 * 
	 * @param chs Map of characteristics to be presented
	 */
	public HouseCharacteristicsTableModel(HashMap<String, String> chs) {
		
		Object[] titles = {"Characteristic", "Description"};
		this.titles = titles;

		Object[][] contents = new Object[chs.size()][2];
		
		int i = 0;
		
		for (String ch : chs.keySet()) {
			Object[] characteristic = {ch, chs.get(ch)};

			contents[i] = characteristic;
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
	 * Method that manually adds a characteristic to the table. It is used because
	 * it is more efficient than building a whole new table
	 * 
	 * @param ch Characteristic to be added
	 * @param de Description of the characteristic
	 */
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
}
