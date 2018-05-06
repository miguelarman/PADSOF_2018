package windows.tableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import application.users.RegisteredUser;
import exceptions.NoRowSelectedException;

public class ChangeCardTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3936695391075292789L;
	private Object[] titles;
	private Object[][] contents;
	private RegisteredUser[] bannedUsersArray;
	
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
		// TODO Auto-generated method stub
		return this.titles.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.contents.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
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
	
	public RegisteredUser getRow(int selectedRow) throws NoRowSelectedException {
		if (selectedRow >= this.bannedUsersArray.length || selectedRow < 0) {
			throw new NoRowSelectedException();
		} else {
			return bannedUsersArray[selectedRow];
		}
	}

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

	public void updateCCard(String nif, String newCreditCard) {
		for (int i = 0; i < this.contents.length; i++) {
			if (contents[i][0].equals(nif)) {
				contents[i][1] = newCreditCard;
			}
		}
		
		this.fireTableDataChanged();
	}

}
