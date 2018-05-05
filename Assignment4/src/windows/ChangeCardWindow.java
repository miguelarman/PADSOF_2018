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

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class ChangeCardWindow extends JFrame {
	
	/**
	* ID needed for the class to be Serializable
	*/
	private static final long serialVersionUID = -1860964590268350725L;
	
	/**
	 * Button to go back to the previous window
	 */
	private JButton goBackButton;
	
	/**
	 * Button to change the selected credit card number
	 */
	private JButton changeButton;
	
	/**
	 * Structure to deploy the banned users
	 */
	private JTable table;
	
	/**
	 * Model to show the banned users 
	 */
	private ChangeCardTableModel dataModel;
	
	/**
	 * Constructor of the class ChangeCardWindow
	 * @param bannedUsers List of users banned from the system
	 */
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
	
	/**
	 * Method that assigns the goBackButton with the goBackController
	 * @param g Controller that allows you to go to the previous window
	 */
	public void setGoBackController(GoBackController g) {
		this.goBackButton.addActionListener(g);
	}
	
	/**
	 * Method that assigns the changeButton with the ChangeCardController 
	 * @param c Controller that allows you to do the needed functionality
	 */
	public void setController(ChangeCardController c) {
		this.changeButton.addActionListener(c);
	}
	
	/**
	 * Method that gives you the selected row of the table
	 * @return The selected user in the table
	 * @throws NoRowSelectedException When no row has been selected
	 */
	public RegisteredUser getSelection() throws NoRowSelectedException {
		int selectedRow = this.table.getSelectedRow();
		
		return this.dataModel.getRow(selectedRow);
	}
	
	/**
	 * Method to delete a user from the table
	 * @param selectedUser User that you want to delete from the table
	 */
	public void delete(RegisteredUser selectedUser) {
		ChangeCardTableModel model = (ChangeCardTableModel) this.table.getModel();
		model.delete(selectedUser);
	}
}
