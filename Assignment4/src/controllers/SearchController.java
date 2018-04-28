package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import application.App;
import application.offer.Offer;
import application.offer.OfferType;
import exceptions.NoUserLoggedException;
import windows.SearchResultWindow;
import windows.SearchWindow;

public class SearchController implements ActionListener {
	
	SearchWindow window;
	App app;
	
	public SearchController(App a, SearchWindow w) {
		window = w;
		app = a;
	}
	
	public void actionPerformed(ActionEvent a) {
		List<Offer> offers = null; 
		SearchResultWindow newWindow;
		switch (window.getCurrentBoxOption()) {
		case ("ZIP code"):
			String zip = window.getZipCodeField().getText();
			offers = app.searchZipCode(zip);
			break;

		case ("Type of offer"):
			String offerType = (String) window.getOfferTypeBox().getSelectedItem();
			OfferType type;
			if (offerType.equals("Living offer")) {
				type = OfferType.LIVING;
			} else {
				type = OfferType.HOLIDAY;
			}
			offers = app.searchOfferType(type);
			break;
		
		case("Dates"):
			Date ini = window.getIniDate().getDate();
			Date end = window.getEndDate().getDate();
			offers = app.searchStartingDate(Instant.ofEpochMilli(ini.getTime()).atZone(ZoneId.systemDefault()).toLocalDate(), Instant.ofEpochMilli(end.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
			break;
		
		case("Booked offers"):
			try {
				offers = app.searchBooked();
			} catch (NoUserLoggedException e) {
				e.printStackTrace();
			}
			//TODO esta excepcion sobra
			break;
		
		case("Paid offers"):
			try {
				offers = app.searchPaid();
			} catch (NoUserLoggedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case("Average rating"):
			String rating = window.getAvgRatingField().getText();
		
			try {
				offers = app.searchAvgRating(Double.parseDouble(rating));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please introduce a valid rating",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (NoUserLoggedException e) {
				e.printStackTrace();
			}
			break;
		}
		
		if(offers.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No offers were found" ,
					"Whoops", JOptionPane.WARNING_MESSAGE);
			return;
		}
		newWindow = new SearchResultWindow(offers);
		newWindow.setController(new SearchResultController(app, newWindow));
		newWindow.setGoBackController(new GoBackController(this.window, newWindow));
		newWindow.setVisible(true);
		this.window.setVisible(false);
	}
}
