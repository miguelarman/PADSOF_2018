package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import application.App;
import application.offer.House;
import exceptions.DuplicateCharacteristicException;
import windows.HouseWindow;

/**
 * Controller for the HouseWindow. Listens for the user to try to add a characteristic to the house
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class HouseWindowController implements ActionListener {

	/**
	 * Model field for the controller. Contains our app in its current state
	 */
	@SuppressWarnings("unused")
	private App app;
	/**
	 * View field for the controller. Contains the HouseWindow
	 */
	private HouseWindow window;
	/**
	 * House that is displayed. Needed to add characteristics
	 */
	private House house;

	/**
	 * Constructor of the HouseWindowController
	 * 
	 * @param app Model for the controller
	 * @param window View for the controller
	 * @param house House to be displayed
	 */
	public HouseWindowController(App app, HouseWindow window, House house) {
		this.app = app;
		this.window = window;
		this.house = house;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JTextField ch = new JTextField();
		JTextField descr = new JTextField();
		Object[] answer = {
				"Characteristic:", ch,
				"Description:", descr
		};		
		
		
		int option = JOptionPane.showConfirmDialog(null, answer, "Add a characteristic", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String characteristic = ch.getText();
			String description = descr.getText();
			
			if (characteristic == null || characteristic.equals("") || description.equals("") || description == null) {
				// The user didn't write in one of the fields
				JOptionPane.showMessageDialog(null, "Please enter a characteristic and a description before clicking this button", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				this.house.addCharacteristic(characteristic, description);
				this.window.addCharacteristic(characteristic, description);
			} catch (DuplicateCharacteristicException e) {
				JOptionPane.showMessageDialog(null, "That characteristic already exists in the house", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			JOptionPane.showMessageDialog(null, "The characteristic was successfully added to the house");
			
		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled correctly");
		}
	}

}
