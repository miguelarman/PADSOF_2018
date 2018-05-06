package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import application.App;
import application.offer.House;
import application.users.Host;
import application.users.RegisteredUser.Role;
import controllers.GoBackController;
import controllers.HouseWindowController;
import exceptions.AUserIsAlreadyLoggedException;
import exceptions.DuplicateCharacteristicException;
import exceptions.IncorrectPasswordException;
import exceptions.UnexistentUserException;
import exceptions.UserIsBannedException;
import windows.tableModels.HouseCharacteristicsTableModel;

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class HouseWindow extends JFrame {

	/**
	* ID needed for the class to be Serializable
	*/
	private static final long serialVersionUID = 4361594776654443001L;
	
	/**
	 * Button to go to the previous window
	 */
	private JButton goBackButton;
	
	/**
	 * Button to add a characteristic to the house
	 */
	private JButton addCharacteristic;
	
	/**
	 * Structure to deploy the characteristics of the house
	 */
	private JTable table;

	/**
	 * Constructor of the class HouseWindow
	 * @param house House to be showed in the window
	 */
	public HouseWindow(House house) {
		super("House in " + house.getZipCode() + " (" + house.getCity() + ")");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		this.goBackButton = new JButton("Go back");
		bottomPanel.add(goBackButton);
		
		// TODO poner los componentes que queramos en la pagina
		
		JPanel houseData = new JPanel();
		houseData.setLayout(new GridLayout(0, 2));
		
		houseData.add(new JLabel("House city"));			houseData.add(new JLabel(house.getCity()));
		houseData.add(new JLabel("House zip code"));		houseData.add(new JLabel(house.getZipCode()));
		houseData.add(new JLabel("House owner"));			houseData.add(new JLabel(house.getHost().getName() + " " + house.getHost().getSurname()));
		houseData.add(new JLabel("House characteristics"));	
		
		table = new JTable(new HouseCharacteristicsTableModel(house.getCharacteristics()));
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollBar = new JScrollPane(table);
		houseData.add(scrollBar);
		
		addCharacteristic = new JButton("Add a characteristic");
		try {
			Role role = App.getLoggedUser().getRole();
			if (role == Role.MULTIROLE || role == Role.HOST) {
				bottomPanel.add(addCharacteristic, BorderLayout.SOUTH);
			}
		} catch (NullPointerException e) {
			// NO user is logged, so we don't show this button
		}
		
		cont.add(bottomPanel, BorderLayout.SOUTH);
		cont.add(houseData, BorderLayout.CENTER);
		
		this.setSize(400, 412);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}
	
	/**
	 * Method that assigns the addCharacteristic with the HouseWindowController
	 * @param h Controller that allows you to do the needed functionality
	 */
	public void setController(HouseWindowController h) {
		this.addCharacteristic.addActionListener(h);
	}
	
	/**
	 * Method that assigns the goBackButton with the goBackController
	 * @param g Controller that allows you to go to the previous window
	 */
	public void setGoBackController(GoBackController g) {
		 this.goBackButton.addActionListener(g);
	}
	
	/**
	 * Method that adds a characteristic to the table
	 * @param ch Name of the characteristic
	 * @param de Description of the characteristic
	 */
	public void addCharacteristic(String ch, String de) {
		HouseCharacteristicsTableModel model = (HouseCharacteristicsTableModel) this.table.getModel();
		model.addCharacteristic(ch, de);
	}
}
