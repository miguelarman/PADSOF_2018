package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import application.offer.House;
import controllers.GoBackController;
import controllers.MyHousesController;
import exceptions.NoRowSelectedException;
import windows.tableModels.MyHousesTableModel;

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class MyHousesWindow extends JFrame {

	/**
	* ID needed for the class to be Serializable
	*/
	private static final long serialVersionUID = 955236722583928240L;
	
	/**
	 * Structure to show the houses owned by the logged user
	 */
	private JTable table;
	
	/**
	 * Model to show the houses owned by the logged user
	 */
	private MyHousesTableModel dataModel;
	
	/**
	 * Button to create a new house
	 */
	private JButton addHouseButton;
	
	/**
	 * Button to see the information of the selected house
	 */
	private JButton viewHouseButton;
	
	/**
	 * Button to go to the previous window
	 */
	private JButton goBackButton;

	/**
	 * Constructor of the class MyHousesWindow
	 * @param houses List of houses owned by the logged user
	 */
	public MyHousesWindow(List<House> houses) {
		super("My houses");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		
		// TODO Auto-generated constructor stub
		
		// Create the table
		this.dataModel = new MyHousesTableModel(houses);
		table = new JTable(dataModel);
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollBar = new JScrollPane(table);
		cont.add(scrollBar, BorderLayout.CENTER);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(0,1));
		this.addHouseButton = new JButton("Add house");
		this.viewHouseButton = new JButton("View house");
		buttonsPanel.add(addHouseButton);
		buttonsPanel.add(viewHouseButton);
		cont.add(buttonsPanel, BorderLayout.EAST);
		
		this.goBackButton = new JButton("Go back");	
		cont.add(goBackButton, BorderLayout.SOUTH);
		
		
		this.setSize(400, 400);
		this.setVisible(false);
	}

	/**
	 * Method that assigns the addHouseButton and the viewHouseButton with the MyHousesController
	 * @param h Controller that allows you to do the needed functionality
	 */
	public void setController(MyHousesController h) {
		this.addHouseButton.addActionListener(h);
		this.viewHouseButton.addActionListener(h);
	}

	/**
	 * Method that assigns the goBackButton with the goBackController
	 * @param gb Controller that allows you to go to the previous window
	 */
	public void setGoBackController(GoBackController gb) {
		 this.goBackButton.addActionListener(gb);
	}
	
	/**
	 * Method that gives you the selected row of the table
	 * @return The selected house in the table
	 * @throws NoRowSelectedException When no row has been selected
	 */
	public House getSelection() throws NoRowSelectedException {
		int selectedRow = this.table.getSelectedRow();
		
		return this.dataModel.getRow(selectedRow);
	}

	/**
	 * Method that adds a house to the table
	 * @param h House to be added
	 */
	public void addHouse(House h) {
		MyHousesTableModel model = (MyHousesTableModel) this.table.getModel();
		model.addHouse(h);
	}

}
