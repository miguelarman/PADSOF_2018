package windows;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

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
	
	/**
	 * Field to introduce the id of the user (NIF)
	 */
	private JTextField idField;
	
	/**
	 * Field to introduce the password of the user
	 */
	private JPasswordField passwordField;
	
	/**
	 * Constructor of the class LoginWindow
	 */
	public LoginWindow() {
		super("Login to our app");
		
		JPanel cont = new JPanel();
		Border padding = BorderFactory.createEmptyBorder(20, 30, 10, 30);

		cont.setBorder(padding);
		cont.setLayout(new GridLayout(0, 1));
		
		// Adding a label
		
		JLabel label  = new JLabel("Welcome. Please log in");
		label.setFont(new Font(label.getFont().getName(), Font.BOLD, 20));
		JPanel labelPanel = new JPanel(new FlowLayout());
		labelPanel.add(label);
		cont.add(labelPanel);
		
		JLabel label2 = new JLabel("NIF:");
		cont.add(label2);
		// Adding fields for username and password
		idField = new JTextField(10);
		JPanel idPanel = new JPanel(new GridLayout(1, 1));
		Border padding3 = BorderFactory.createEmptyBorder(15, 75, 15, 75);
		idPanel.setBorder(padding3);
		idPanel.add(idField);
		cont.add(idPanel);
		JLabel label3 = new JLabel("Password:");
		cont.add(label3);
		JPanel passwordPanel = new JPanel(new GridLayout(1, 1));
		Border padding2 = BorderFactory.createEmptyBorder(15, 75, 15, 75);
		passwordPanel.setBorder(padding2);
		passwordField = new JPasswordField(10);
		
		passwordPanel.add(passwordField);
		cont.add(passwordPanel);
		
		JPanel loginPanel = new JPanel(new FlowLayout());
		loginButton = new JButton("Log in");
		loginPanel.add(loginButton);
		cont.add(loginPanel);
		
		JLabel label4 = new JLabel("You are not a registered user? Press here:");
		cont.add(label4);
		JPanel noUserPanel = new JPanel(new FlowLayout());
		noUserButton = new JButton("Enter without account");
		noUserPanel.add(noUserButton);
		cont.add(noUserPanel);
		this.setContentPane(cont);
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}
	
	/**
	 * Getter method for the introduced text in the idField
	 * @return The text that the user has introduced in the idField
	 */
	public String getIdField() {
		return this.idField.getText();
	}
	
	/**
	 * Getter method for the introduced text in the passwordField
	 * @return The text that the user has introduced in the passwordField
	 */
	public String getPasswordField() {
		return String.valueOf(this.passwordField.getPassword());
	}
	
	/**
	 * Method that assigns the loginButton and the noUserButton with the ChangeCardController 
	 * @param c Controller that allows you to do the needed functionality
	 */
	public void setController(LoginController c) {
		loginButton.addActionListener(c);
		noUserButton.addActionListener(c);
	}
}
