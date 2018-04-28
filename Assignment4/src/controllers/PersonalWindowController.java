package controllers;

import java.awt.event.*;

import application.App;
import windows.PersonalWindow;
import windows.SearchWindow;

public class PersonalWindowController implements ActionListener {

	private PersonalWindow window;
	private App app;

	public PersonalWindowController(App app, PersonalWindow w) {
		this.window = w;
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		String pressedButton = arg0.getActionCommand();
		
		switch(pressedButton) {
		case("Search"):
	
			SearchWindow newWindow = new SearchWindow(App.getLoggedUser());
			SearchController s = new SearchController(this.app, newWindow);
			newWindow.setPreviousController(new GoBackController(this, newWindow));
			newWindow.setSearchController(s);
			newWindow.setVisible(true);
			this.window.setVisible(false);
			break;
		
		case("Booked offers"):
			// TODO
//			BookedOffersWindow newWindow = new BookedOffersWindow(App.getLoggedUser().getReservedOffers());
//			BookedOffersController b = new BookedOffersController(this.app, newWindow);
//			newWindow.setController(b);
//			
//			GoBackController gb = new GoBackController(this.window, newWindow);
//			newWindow.setGoBackController(gb);
//			newWindow.setVisible(true);
//			this.window.setVisible(false);
			break;
		
		case("Paid offers"):
			// TODO
//			PaidOffersWindow newWindow = new PaidOffersWindow(this.app.getPaidOffersByLoggedUser());
//			PaidOffersController p = new PaidOffersController(this.app, newWindow);
//			newWindow.setController(p);
//
//			GoBackController gb = new GoBackController(this.window, newWindow);
//			newWindow.setGoBackController(gb);
//			newWindow.setVisible(true);
//			this.window.setVisible(false);
			break;
		
		case("My offers"):
			// TODO
//			MyOffersWindow newWindow = new MyOffersWindow(this.app.getCreatedOffersByLoggerUser());
//			MyOffersController o = new MyOffersController(this.app, newWindow);
//			newWindow.setController(o);
//	
//			GoBackController gb = new GoBackController(this.window, newWindow);
//			newWindow.setGoBackController(gb);
//			newWindow.setVisible(true);
//			this.window.setVisible(false);
			break;
		
		case("My houses"):
			// TODO
//			MyHousesWindow newWindow = new MyHousesWindow(((Host)App.getLoggedUser()).getHouses());
//			MyHousesController h = new MyHousesController(this.app, newWindow);
//			newWindow.setController(h);
//	
//			GoBackController gb = new GoBackController(this.window, newWindow);
//			newWindow.setGoBackController(gb);
//			newWindow.setVisible(true);
//			this.window.setVisible(false);
			break;
		
		case("Pending offers"):
			// TODO
//			PendingOffersWindow newWindow = new PendingOffersWindow(this.app.getPendingOffers());
//			PendingOffersController p = new PendingOffersController(this.app, newWindow);
//			newWindow.setController(p);
//			
//			GoBackController gb = new GoBackController(this.window, newWindow);
//			newWindow.setGoBackController(gb);
//			newWindow.setVisible(true);
//			this.window.setVisible(false);
			break;
		
		case("Modify credit cards"):
			// TODO
//			CreditCardsWindow newWindow = new CreditCardsWindow(this.app.getBannedUsers());
//			CreditCardsController s = new CreditCardsController(this.app, newWindow);
//			newWindow.setController(s);
//			
//			GoBackController gb = new GoBackController(this.window, newWindow);
//			newWindow.setGoBackController(gb);
//			newWindow.setVisible(true);
//			this.window.setVisible(false);
			break;
		default:
			break;
		}
	}
}




//GoBackController gb = new GoBackController(this.window, newWindow);
//newWindow.setGoBackController(gb);