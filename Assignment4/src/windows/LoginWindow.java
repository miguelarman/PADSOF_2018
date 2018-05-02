package windows;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8342207357186589030L;
	private JButton button;
	private JButton noUserButton;
	final private JTextField idField;
	final private JPasswordField passwordField;
	
	public LoginWindow() {
		super("Login to our app");
		
		Container cont = super.getContentPane();
		cont.setLayout(new FlowLayout());
		
		// Adding a label
		JLabel label  = new JLabel("Welcome. Please log in");
		cont.add(label);
		
		// Adding fields for username and password
		idField = new JTextField(10);
		idField.setText("X1130055");//"NIF");
		cont.add(idField);
		
		passwordField = new JPasswordField(10);
		passwordField.setText("secret");//"Password");
		cont.add(passwordField);
		
		button = new JButton("Log in");
		cont.add(button);
		
		noUserButton = new JButton("Enter without account");
		cont.add(noUserButton);
		
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
		noUserButton.addActionListener(c);
	}
}
