package controllers;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import application.App;
import windows.SearchWindow;

/**
 * Controller for the dropdown selector of type of search. Changes the window according to the item selected
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class SearchBoxController  implements ItemListener {
	/**
	 * View for the controller. Contains the SearchWindow
	 */
	SearchWindow window;
	/**
	 * Model field for the controller. Contains our app in its current state
	 */
	App app;
	
	/**
	 * Constructor of the SearchBoxController
	 * 
	 * @param a Model for the controller
	 * @param w View for the controller
	 */
	public SearchBoxController(App a, SearchWindow w){
		window = w;
		app = a;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getStateChange() == ItemEvent.SELECTED) {
			
			if(window.getCurrentBoxOption().equals("ZIP code")) {
				window.setVisibleZipCodeField();
			}
			else if(window.getCurrentBoxOption().equals("Type of offer")) {
				window.setVisibleOfferTypeBox();
			}
			
			else if(window.getCurrentBoxOption().equals("Dates")) {
				window.setVisibleDates();
			}
			
			else if(window.getCurrentBoxOption().equals("Booked offers")) {
				window.hideAll();
			}
			else if(window.getCurrentBoxOption().equals("Paid offers")) {
				window.hideAll();
			}
			else if(window.getCurrentBoxOption().equals("Average rating")) {
				window.setVisibleRating();
			}
		}
		
	}
}
