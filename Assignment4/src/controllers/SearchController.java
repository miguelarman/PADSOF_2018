package controllers;

import application.App;
import windows.SearchWindow;

public class SearchController {
	SearchWindow window;
	App app;
	
	public SearchController(App a, SearchWindow w) {
		
		window = w;
		app = a;
	}
}
