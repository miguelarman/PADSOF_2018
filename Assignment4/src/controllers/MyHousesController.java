package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import application.App;
import application.offer.House;
import application.users.Host;
import exceptions.InvalidRolException;
import exceptions.NoRowSelectedException;
import exceptions.NoUserLoggedException;
import exceptions.NotTheOwnerException;
import windows.HouseWindow;
import windows.MyHousesWindow;

public class MyHousesController implements ActionListener {

	private MyHousesWindow window;
	private App app;

	public MyHousesController(App app, MyHousesWindow window) {
		this.window = window;
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("View house")) {
			try {
				House house = this.window.getSelection();

				HouseWindow window = new HouseWindow(house);
				HouseWindowController controller = new HouseWindowController(this.app, window, house);
				window.setController(controller);
				window.setGoBackController(new GoBackController(this.window, window));
				window.setVisible(true);
				this.window.setVisible(false);

			} catch (NoRowSelectedException e) {
				JOptionPane.showMessageDialog(null, "You must select a house before clicking this button", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		} else if (arg0.getActionCommand().equals("Add house")) {
			// Ask for ZIP code and city
			
			JTextField zip = new JTextField();
			JTextField city = new JTextField();
			Object[] answer = {
					"ZIP code:", zip,
					"City:", city
			};		
			
			
			int option = JOptionPane.showConfirmDialog(null, answer, "Create a house", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				//TODO comprobar que no es vacio
				
				try {
					House h = new House(zip.getText(), city.getText(), (Host)App.getLoggedUser());
					this.app.addHouse(h);
					this.window.addHouse(h);
				} catch (InvalidRolException | NoUserLoggedException e) {
					JOptionPane.showMessageDialog(null, "You must be logged as a host to create houses", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				} catch (NotTheOwnerException e) {
					JOptionPane.showMessageDialog(null, "You can only add houses that you own", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				JOptionPane.showMessageDialog(null, "House created successfully!");
			} else {
				JOptionPane.showMessageDialog(null, "Operation cancelled correctly");
			}
		}
	}

}
