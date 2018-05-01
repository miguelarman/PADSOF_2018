package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import application.offer.House;
import application.users.Host;
import controllers.GoBackController;
import controllers.HouseWindowController;
import exceptions.DuplicateCharacteristicException;
import windows.tableModels.HouseCharacteristicsTableModel;

public class HouseWindow extends JFrame {

	private JButton goBackButton;


	public HouseWindow(House house) {
		super("House in " + house.getZipCode() + " (" + house.getCity() + ")");
		
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		
		this.goBackButton = new JButton("Go back");
		cont.add(goBackButton, BorderLayout.SOUTH);
		
		// TODO poner los componentes que queramos en la pagina
		
		JPanel houseData = new JPanel();
		houseData.setLayout(new GridLayout(0, 2));
		
		houseData.add(new JLabel("House city"));			houseData.add(new JLabel(house.getCity()));
		houseData.add(new JLabel("House zip code"));		houseData.add(new JLabel(house.getZipCode()));
		houseData.add(new JLabel("House owner"));			houseData.add(new JLabel(house.getHost().getName() + " " + house.getHost().getSurname()));
		houseData.add(new JLabel("House characteristics"));	houseData.add(new JTable(new HouseCharacteristicsTableModel(house.getCharacteristics())));
		
		cont.add(houseData, BorderLayout.CENTER);
		
		
		this.setSize(400, 400);
		this.setVisible(false);
	}

	public void setController(HouseWindowController h) {
		// TODO Auto-generated method stub

	}

	public void setGoBackController(GoBackController g) {
		 this.goBackButton.addActionListener(g);
	}
	
	
	public static void main(String...strings) throws DuplicateCharacteristicException {
		House h = new House("28080", "Madrid", new Host(null, null, null, null, null));
		h.addCharacteristic("Pool", "covered");
		h.addCharacteristic("Bathrooms", "four");
		h.addCharacteristic("Bedrooms", "three");
		
		HouseWindow w = new HouseWindow(h);
		w.setController(new HouseWindowController(null, w, h));
		w.setGoBackController(new GoBackController(new LoginWindow(), w));
		w.setVisible(true);
		
	}

}
