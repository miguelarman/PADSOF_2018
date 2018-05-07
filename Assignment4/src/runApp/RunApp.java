package runApp;

import application.App;
import application.dates.ModifiableDate;
import controllers.LoginController;
import windows.LoginWindow;

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class RunApp {

	/**
	 * Main to execute the app
	 * @param args Arguments received by the main
	 */
	public static void main(String[] args) {
//		ModifiableDate.plusDays(10);
		
		App app = App.openApp();
		
		LoginWindow w = new LoginWindow();
		LoginController c = new LoginController(app, w);
		w.setController(c);
		w.setVisible(true);
	}

}
