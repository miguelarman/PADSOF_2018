package windows;

import java.awt.*;
import javax.swing.*;

import controllers.LoginController;

/**
 * @author Miguel Arconada (miguel.arconada@estudiante.uam.es) y Alberto
 *         Gonzalez (alberto.gonzalezk@estudiante.uam.es)
 */
public class LoginWindow extends JFrame {
	
	/**
	* ID needed for the class to be Serializable
	*/
	private static final long serialVersionUID = -8342207357186589030L;
	
	/**
	 * Button that tries to log into the system with the given NIF and password
	 */
	private JButton loginButton;
	
	/**
	 * Button that goes into the system as¡
	 */
	private JButton noUserButton;
	private JTextField idField;
	private JPasswordField passwordField;
	
	public LoginWindow() {
		super("Login to our app");
		
		Container cont = super.getContentPane();
		cont.setLayout(new GridLayout(0, 1));
		
		// Adding a label
		JLabel label  = new JLabel("Welcome. Please log in");
		cont.add(label);
		
		// Adding fields for username and password
		idField = new JTextField(10);
		idField.setText("X1130055");//"NIF");
		idField.setSize(new Dimension(50,100));
		idField.setBounds(100, 150, 200, 35);
		cont.add(idField);
		
		passwordField = new JPasswordField(10);
		passwordField.setText("secret");//"Password");
		cont.add(passwordField);
		
		loginButton = new JButton("Log in");
		cont.add(loginButton);
		
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
	
	public void setController(LoginController c) {
		loginButton.addActionListener(c);
		noUserButton.addActionListener(c);
	}
}
