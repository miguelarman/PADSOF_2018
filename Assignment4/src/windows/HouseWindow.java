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
import javax.swing.table.AbstractTableModel;

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

public class HouseWindow extends JFrame {

	private JButton goBackButton;
	private JButton addCharacteristic;
	private JTable table;


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
		
		try {
			Role role = App.getLoggedUser().getRole();
			if (role == Role.MULTIROLE || role == Role.HOST) {
				addCharacteristic = new JButton("Add a characteristic");
				bottomPanel.add(addCharacteristic, BorderLayout.SOUTH);
			}
		} catch (NullPointerException e) {
			// NO user is logged, so we don't show this button
		}
		
		cont.add(bottomPanel, BorderLayout.SOUTH);
		cont.add(houseData, BorderLayout.CENTER);
		
		this.setSize(400, 412);
		this.setVisible(false);
	}

	public void setController(HouseWindowController h) {
		this.addCharacteristic.addActionListener(h);
	}

	public void setGoBackController(GoBackController g) {
		 this.goBackButton.addActionListener(g);
	}
	
	public void addCharacteristic(String ch, String de) {
		HouseCharacteristicsTableModel model = (HouseCharacteristicsTableModel) this.table.getModel();
		model.addCharacteristic(ch, de);
	}
	
	public static void main(String...strings) throws DuplicateCharacteristicException, UserIsBannedException, IncorrectPasswordException, UnexistentUserException, AUserIsAlreadyLoggedException {
		House h = new House("28080", "Madrid", new Host("Nombre", "Apellidos", null, null, null));
		h.addCharacteristic("Pool", "covered");
		h.addCharacteristic("Bathrooms", "four");
//		h.addCharacteristic("Bedrooms", "three");
//		h.addCharacteristic("Other", "three");
//		h.addCharacteristic("Another", "three");
//		h.addCharacteristic("Other one", "three");
		
		App app = App.openApp();
		app.login("host", "host");
		
		HouseWindow w = new HouseWindow(h);
		w.setController(new HouseWindowController(null, w, h));
		w.setGoBackController(new GoBackController(new LoginWindow(), w));
		w.setVisible(true);
		
	}

}