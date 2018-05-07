package windows.tableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import application.users.RegisteredUser;
import exceptions.NoRowSelectedException;

/**
 * Table model for the tables used to present the users that are banned. It has two columns:
 * <ul>
 * <li>NIF</li>
 * <li>Credit card number</li>
 * </ul>
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class ChangeCardTableModel extends AbstractTableModel {
	
	/**
	 * ID needed for serialization
	 */
	private static final long serialVersionUID = 3936695391075292789L;
	/**
	 * Names of the column titles
	 */
	private Object[] titles;
	/**
	 * All the contents of the table. Each offer divided in fields
	 */
	private Object[][] contents;
	/**
	 * Array of all the users that are represented in the table. Needed to return the selected one
	 */
	private RegisteredUser[] bannedUsersArray;
	
	/**
	 * Constructor of the model
	 * 
	 * @param bannedUsers List of banned users to be presented
	 */
	public ChangeCardTableModel(List<RegisteredUser> bannedUsers) {
		Object[] titles = {"User NIF", "Creditcard number"};
		this.titles = titles;
		
		Object[][] contents = new Object[bannedUsers.size()][2];
		bannedUsersArray = new RegisteredUser[bannedUsers.size()];
		int i = 0;
		
		for(RegisteredUser r: bannedUsers) {
			Object[] user = {r.getNIF(), r.getCreditCard()};
			
			contents[i] = user;
			bannedUsersArray[i] = r;  
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
	 * Method that returns the user that is presented in an specific row
	 * 
	 * @param selectedRow Index of the row we want to return
	 * @return Selected user
	 * @throws NoRowSelectedException When no row is selected
	 */
	public RegisteredUser getRow(int selectedRow) throws NoRowSelectedException {
		if (selectedRow >= this.bannedUsersArray.length || selectedRow < 0) {
			throw new NoRowSelectedException();
		} else {
			return bannedUsersArray[selectedRow];
		}
	}

	/**
	 * Method that manually removes a user from the table. It is used because it is
	 * more efficient than building a whole new table
	 * 
	 * @param selectedUser User to be removed
	 */
	public void delete(RegisteredUser selectedUser) {
		int index = 0;
		
		Object[][] newContents = new Object[this.contents.length - 1][2];
		
		for (int i = 0; i < this.contents.length; i++) {
			// We delete the selected user from the table
			if (!selectedUser.getNIF().equals(contents[i][0])) {
				newContents[index][0] = this.contents[i][0];
				newContents[index][1] = this.contents[i][1];
				index++;
			}
		}
		
		this.contents = newContents;
		
		this.fireTableDataChanged();
	}

	/**
	 * Method that changes the credit card that appears in the table of a specific user
	 * 
	 * @param nif NIF of the user
	 * @param newCreditCard New credit card
	 */
	public void updateCCard(String nif, String newCreditCard) {
		for (int i = 0; i < this.contents.length; i++) {
			if (contents[i][0].equals(nif)) {
				contents[i][1] = newCreditCard;
			}
		}
		
		this.fireTableDataChanged();
	}

}
