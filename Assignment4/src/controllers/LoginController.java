package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.App;
import windows.LoginWindow;

public class LoginController implements ActionListener {
	private LoginWindow window;
	private App app;
	
	public LoginController(App app, LoginWindow w) {
		this.window = w;
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
//WindowXX ventana = new WindowXX();
//MyController c = new MyCOntroller();
//ventana.setControlador(c);
//ventana.setVisible(true);
//ventanaAnterior.setVisible(false);