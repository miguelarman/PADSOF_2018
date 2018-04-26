package controllers;

import java.awt.event.*;

import javax.swing.JFrame;

import application.App;

public class GoBackController implements ActionListener {
	private JFrame previousWindow;
	private JFrame nextWindow;
	
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
