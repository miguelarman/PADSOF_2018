package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import application.App;
import application.offer.House;
import exceptions.DuplicateCharacteristicException;
import windows.HouseWindow;

public class HouseWindowController implements ActionListener {

	private App app;
	private HouseWindow window;
	private House house;

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
		
		
		int option = JOptionPane.showConfirmDialog(null, answer, "Aasda", JOptionPane.OK_CANCEL_OPTION);
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
			// TODO
			System.out.println("Cancelled");
		}
	}

}
