package windows;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginWindow extends JFrame {
	
	private JButton button;
	final private JTextField idField;
	final private JPasswordField passwordField;
	
	public LoginWindow() {
		super("Login to our app");
		
		// Adding a label
		JLabel label  = new JLabel("Welcome. Please log in");
		this.setLayout(new FlowLayout());
		this.add(label);
		
		// Adding fields for username and password
		idField = new JTextField(10);
		idField.setText("NIF");
		this.add(idField);
		
		passwordField = new JPasswordField(10);
		passwordField.setText("Password");
		this.add(passwordField);
		
		button = new JButton("Fuck off");
		this.add(button);
		
		this.setSize(400, 500);
		this.setVisible(false);
	}
	
	public String getIdField() {
		return this.idField.getText();
	}
	
	public String getPasswordField() {
		return String.valueOf(this.passwordField.getPassword());
	}
	
	public void setController(ActionListener c) {
		button.addActionListener(c);
	}
}
