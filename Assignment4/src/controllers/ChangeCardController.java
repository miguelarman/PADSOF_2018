package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import application.App;
import application.users.RegisteredUser;
import exceptions.InvalidRolException;
import exceptions.NoRowSelectedException;
import windows.ChangeCardWindow;

public class ChangeCardController implements ActionListener {
	
	private ChangeCardWindow window;
	private App app;
	
	public ChangeCardController(App app, ChangeCardWindow w) {
		this.window = w;
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RegisteredUser selectedUser = null;
		try {
			selectedUser = this.window.getSelection();
		} catch (NoRowSelectedException e1) {
			JOptionPane.showMessageDialog(null, "You must select a user before clicking this button", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		JTextField ccard = new JTextField();
		Object[] content = { "Credit card:", ccard};
		
		int option = JOptionPane.showConfirmDialog(null, content, "New credit card", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String newCreditCard = ccard.getText();
			if (newCreditCard.equals("") || newCreditCard == null) {
				JOptionPane.showMessageDialog(null, "Please, introduce a credit card", "Error", JOptionPane.ERROR_MESSAGE);
			}
			try {
				this.app.changeCreditCard(newCreditCard, selectedUser);
			} catch (InvalidRolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "The credit card has been changed successfully");
		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled correctly");
		}
		
	}
}
