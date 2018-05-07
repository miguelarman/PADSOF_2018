package windows.tableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import application.opinion.*;
import exceptions.NoRowSelectedException;

/**
 * Table model for the tables used to present the opinions of an offer. It has three columns:
 * <ul>
 * <li>Opinions</li>
 * <li>Rating</li>
 * <li>Author</li>
 * </ul>
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class OfferOpinionsTableModel extends AbstractTableModel {

	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = 2992788647977565646L;
	/**
	 * Names of the column titles
	 */
	private Object[] titles;
	/**
	 * Array of all the opinions that are represented in the table. Needed to return the selected one
	 */
	private Opinion[] opinionsArray;
	/**
	 * All the contents of the table. Each opinion divided in fields
	 */
	private Object[][] contents;

	/**
	 * Constructor of the model
	 * 
	 * @param opinions List of opinions to be presented
	 */
	public OfferOpinionsTableModel(List<Opinion> opinions) {
		
		Object[] titles = { "Opinion", "Rating", "Author" };
		this.titles = titles;

		Object[][] contents = new Object[opinions.size()][4];
		opinionsArray = new Opinion[opinions.size()];

		int i = 0;

		for (Opinion o : opinions) {

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

	/**
	 * Method that returns the opinion that is presented in an specific row
	 * 
	 * @param selectedRow Index of the row we want to return
	 * @return Selected Opinion
	 * @throws NoRowSelectedException When no row is selected
	 */
	public Opinion getRow(int selectedRow) throws NoRowSelectedException {
		if (selectedRow >= this.opinionsArray.length || selectedRow < 0) {
			throw new NoRowSelectedException();
		} else {
			return opinionsArray[selectedRow];
		}
	}

	/**
	 * Method that manually adds an opinion to the table. It is used because it is
	 * more efficient than building a whole new table
	 * 
	 * @param o Opinion to be added
	 */
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
