package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import application.App;
import application.offer.House;
import application.users.Host;
import application.users.MultiRoleUser;
import application.users.RegisteredUser.Role;
import exceptions.*;
import windows.HouseWindow;
import windows.MyHousesWindow;

/**
 * Controller for the MyHousesWindow. Wait for the user to click a button trying to view a house or add a new house
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class MyHousesController implements ActionListener {

	/**
	 * View field for the controller. Contains the MyHousesWindow
	 */
	private MyHousesWindow window;
	/**
	 * Model field for the controller. Contains our app in its current state
	 */
	private App app;

	/**
	 * Constructor of the MyHousesController
	 * 
	 * @param app Model for the controller
	 * @param window View for the controller
	 */
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
					House h;
					if (App.getLoggedUser().getRole().equals(Role.HOST)) {
						h = new House(zip.getText(), city.getText(), (Host)App.getLoggedUser());
					} else if (App.getLoggedUser().getRole().equals(Role.MULTIROLE)) {
						h = new House(zip.getText(), city.getText(), (MultiRoleUser)App.getLoggedUser());
					} else {
						h = new House("", "", (Host)null);
					}
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
