package windows;

import application.App;
import controllers.LoginController;

public class test {

	public static void main(String[] args) {
		App app = App.openApp();
		
		LoginWindow w = new LoginWindow();
		LoginController c = new LoginController(app, w);
		w.setController(c);
		w.setVisible(true);
	}

}
