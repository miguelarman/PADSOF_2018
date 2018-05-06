package windows.tableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import application.offer.Offer;
import application.opinion.*;
import exceptions.NoRowSelectedException;

public class OfferOpinionsTableModel extends AbstractTableModel {

	private Object[] titles;
	private Opinion[] opinionsArray;
	private Object[][] contents;

	public OfferOpinionsTableModel(List<Opinion> opinions) {
		// Create an array of column titles
		// TODO
		Object[] titles = { "Opinion", "Rating", "Author" };
		this.titles = titles;

		// Create a matrix of table contents
		Object[][] contents = new Object[opinions.size()][4];
		opinionsArray = new Opinion[opinions.size()];

		int i = 0;

		for (Opinion o : opinions) { // TODO

			if (o.getClass() == Comment.class) {
				Comment c = (Comment) o;
				Object[] opinion = { c.getText(), null, o.getCommenter().getName() };
				contents[i] = opinion;
				opinionsArray[i] = o;
				i++;
			} else if (o.getClass() == Rating.class) {
				Rating r = (Rating) o;
				Object[] opinion = { null, r.getRating(), o.getCommenter().getName() };
				contents[i] = opinion;
				opinionsArray[i] = o;
				i++;
			}
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

	public Opinion getRow(int selectedRow) throws NoRowSelectedException {
		if (selectedRow >= this.opinionsArray.length || selectedRow < 0) {
			throw new NoRowSelectedException();
		} else {
			return opinionsArray[selectedRow];
		}
	}

	public void addOpinion(Opinion o) {

		Object[][] newContents = new Object[this.contents.length + 1][this.titles.length];
		Opinion[] newOpinionsArray = new Opinion[this.contents.length + 1];
		
		// We copy the existing contents
		for (int i = 0; i < this.contents.length; i++) {
			for (int j = 0; j < this.titles.length; j++) {
				newContents[i][j] = this.contents[i][j];
			}
			
			newOpinionsArray[i] = this.opinionsArray[i];
		}
		
		if (o.getClass() == Comment.class) {
			Comment c = (Comment)o;
			Object[] opinion = { c.getText(), null, o.getCommenter().getName() };
			newContents[this.contents.length] = opinion;
			newOpinionsArray[this.opinionsArray.length] = o;
		} else if (o.getClass() == Rating.class) {
			Rating r = (Rating)o;
			Object[] opinion = { null, r.getRating(), o.getCommenter().getName() };
			newContents[this.contents.length] = opinion;
			newOpinionsArray[this.opinionsArray.length] = o;
		}
		
		
		this.contents = newContents;
		this.opinionsArray = newOpinionsArray;
		
		this.fireTableDataChanged();
	}
}
