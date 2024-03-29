package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import application.App;
import application.offer.House;
import application.users.Host;
import application.users.MultiRoleUser;
import application.users.RegisteredUser.Role;
import windows.*;

/**
 * Controller for the PersonalWindow. Can perform several actions:
 * <ul>
 * <li>Personal window:
 * <ul>
 * <li>Search offers in the system</li>
 * </ul>
 * </li>
 * <li>Guest window:
 * <ul>
 * <li>View booked offers of the user</li>
 * </ul>
 * </li>
 * <li>Host window:
 * <ul>
 * <li>View offers of the user</li>
 * <li>View houses of the user</li>
 * </ul>
 * </li>
 * <li>Admin window:
 * <ul>
 * <li>Review pending offers (approve, deny, suggest changes...)</li>
 * <li>Modify credit card of banned users</li>
 * </ul>
 * </li>
 * </ul>
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez
 *         (alberto.gonzalezk@estudiante.uam.es)
 */
public class PersonalWindowController implements ActionListener {

	/**
	 * View field for the controller. Contains the PersonalWindow
	 */
	private PersonalWindow window;
	/**
	 * Model field for the controller. Contains our app in its current state
	 */
	private App app;

	/**
	 * Constructor of the PersonalWindowController
	 * 
	 * @param app Model for the controller
	 * @param w View for the controller
	 */
	public PersonalWindowController(App app, PersonalWindow w) {
		this.window = w;
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String pressedButton = arg0.getActionCommand();
		
		switch(pressedButton) {
		case("Search"):
	
			SearchWindow newWindow = new SearchWindow(App.getLoggedUser());
			SearchController s = new SearchController(this.app, newWindow);
			newWindow.setGoBackController(new GoBackController(this.window, newWindow));
			newWindow.setSearchController(s);
			newWindow.setBoxController(new SearchBoxController(this.app, newWindow));
			newWindow.setUp();
			this.window.setVisible(false);
			break;
		
		case("Booked offers"):
			BookedOffersWindow newWindow3 = new BookedOffersWindow(App.getLoggedUser().getReservedOffers());
			BookedOffersController b = new BookedOffersController(this.app, newWindow3);
			newWindow3.setController(b);
			
			GoBackController gb = new GoBackController(this.window, newWindow3);
			newWindow3.setGoBackController(gb);
			newWindow3.setVisible(true);
			this.window.setVisible(false);
			break;
		
		case("My offers"):
			MyOffersWindow mow = new MyOffersWindow(this.app.getCreatedOffersByLoggerUser());
			MyOffersController o = new MyOffersController(this.app, mow);
			mow.setController(o);
	
			GoBackController gbc = new GoBackController(this.window, mow);
			mow.setGoBackController(gbc);
			mow.setVisible(true);
			this.window.setVisible(false);
			break;
		
		case("My houses"):
			MyHousesWindow mhw;
			if (App.getLoggedUser().getRole().equals(Role.HOST)) {
				mhw = new MyHousesWindow(((Host)App.getLoggedUser()).getHouses());
			} else if (App.getLoggedUser().getRole().equals(Role.MULTIROLE)) {
				mhw = new MyHousesWindow(((MultiRoleUser)App.getLoggedUser()).getHouses());
			} else {
				mhw = new MyHousesWindow(new ArrayList<House>());
			}
			MyHousesController h = new MyHousesController(this.app, mhw);
			mhw.setController(h);
			GoBackController gb3 = new GoBackController(this.window, mhw);
			mhw.setGoBackController(gb3);
			mhw.setVisible(true);
			this.window.setVisible(false);
			break;
		
		case("Pending offers"):
			PendingOffersWindow newWindow2 = new PendingOffersWindow(this.app.getPendingForApprovalOffers());
			PendingOffersController p = new PendingOffersController(this.app, newWindow2);
			newWindow2.setController(p);
			
			GoBackController gbc2 = new GoBackController(this.window, newWindow2);
			newWindow2.setGoBackController(gbc2);
			newWindow2.setVisible(true);
			this.window.setVisible(false);
			break;
		
		case("Modify credit cards"):
			ChangeCardWindow newWindow1 = new ChangeCardWindow(this.app.getBannedUsers());
			ChangeCardController s1 = new ChangeCardController(this.app, newWindow1);
			newWindow1.setController(s1);
			
			GoBackController gb1 = new GoBackController(this.window, newWindow1);
			newWindow1.setGoBackController(gb1);
			newWindow1.setVisible(true);
			this.window.setVisible(false);
			break;
		default:
			break;
		}
	}
}