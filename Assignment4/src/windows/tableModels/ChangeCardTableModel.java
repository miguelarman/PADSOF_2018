package windows.tableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import application.offer.Offer;
import application.users.RegisteredUser;
import exceptions.NoRowSelectedException;

public class ChangeCardTableModel extends AbstractTableModel {
	
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
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
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
	}

}
