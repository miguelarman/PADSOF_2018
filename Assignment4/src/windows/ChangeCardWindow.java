package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import application.users.RegisteredUser;
import controllers.ChangeCardController;
import controllers.GoBackController;
import exceptions.NoRowSelectedException;
import windows.tableModels.ChangeCardTableModel;
import windows.tableModels.MyHousesTableModel;

public class ChangeCardWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1860964590268350725L;
	private JButton goBackButton;
	private JButton changeButton;
	private JTable table;
	private ChangeCardTableModel dataModel;
	
	public ChangeCardWindow(List<RegisteredUser> bannedUsers) {
		super("Banned users");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		this.dataModel = new ChangeCardTableModel(bannedUsers);
		this.table = new JTable(dataModel);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollBar = new JScrollPane(table);

		JPanel tablePanel = new JPanel();
		tablePanel.add(scrollBar, BorderLayout.CENTER);
		
		changeButton = new JButton("Change");
		tablePanel.add(changeButton, BorderLayout.EAST);

		cont.add(tablePanel, BorderLayout.CENTER);
		
		this.goBackButton = new JButton("Go back");
		cont.add(this.goBackButton, BorderLayout.SOUTH);
		
		this.setSize(750, 500);
		this.setVisible(false);
	}

	public void setGoBackController(GoBackController g) {
		this.goBackButton.addActionListener(g);
	}
	
	public void setController(ChangeCardController c) {
		this.changeButton.addActionListener(c);
	}
	
	public RegisteredUser getSelection() throws NoRowSelectedException {
		int selectedRow = this.table.getSelectedRow();
		
		return this.dataModel.getRow(selectedRow);
	}

	public void delete(RegisteredUser selectedUser) {
		ChangeCardTableModel model = (ChangeCardTableModel) this.table.getModel();
		model.delete(selectedUser);
	}
}
