package controllers;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import application.App;
import windows.SearchWindow;

public class SearchBoxController  implements ItemListener {
	SearchWindow window;
	App app;
	
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
