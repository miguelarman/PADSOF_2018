package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.App;
import windows.ChangesWindow;
import windows.PendingOffersWindow;

public class ChangesController implements ActionListener {
	
	private App app;
	private ChangesWindow window;
	private PendingOffersWindow ancientWindow;
	public ChangesController(App app, ChangesWindow w, PendingOffersWindow aw) {
		this.app = app;
		this.window = w;
		this.ancientWindow = aw;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String changes = this.window.getChanges();
		
		this.app.suggestChanges(this.window, changes);
		//TODO
	}

}
