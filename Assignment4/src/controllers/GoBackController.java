package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * General controller without any specific window. It is added to all the "Go
 * back" buttons in the system. When they are clicked, this controller hides the
 * current window and shows the previous one
 *
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) Alberto Gonzalez
 *         (alberto.gonzalezk@estudiante.uam.es)
 */
public class GoBackController implements ActionListener {
	
	/**
	 * Previous window to be shown
	 */
	private JFrame previousWindow;
	/**
	 * New window to be hidden
	 */
	private JFrame nextWindow;
	
	/**
	 * Constructor for the GoBackController
	 * 
	 * @param p Previous window
	 * @param n New window
	 */
	public GoBackController(JFrame p, JFrame n) {
		this.previousWindow = p;
		this.nextWindow = n;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.nextWindow.setVisible(false);
		this.previousWindow.setVisible(true);
	}
	
	
}
