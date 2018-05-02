package windows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.users.RegisteredUser;
import controllers.GoBackController;
import controllers.IntroduceCardController;

public class IntroduceCardWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4562262230075120615L;
	private JTextField ccNumberField;
	private JButton acceptButton;
	private JButton goBackButton;
	
	public IntroduceCardWindow() {
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		JPanel buttons = new JPanel(new GridLayout(1, 2));
		JPanel text = new JPanel(new FlowLayout());
		
		this.ccNumberField = new JTextField("10");
		text.add(ccNumberField);
		
		JLabel label = new JLabel("Introduce the new creditcard number:");
		cont.add(label, BorderLayout.NORTH);
		cont.add(text, BorderLayout.CENTER);
		
		acceptButton = new JButton("Accept");
		goBackButton = new JButton("Go back");
		
		buttons.add(acceptButton);
		buttons.add(goBackButton);
		cont.add(buttons, BorderLayout.SOUTH);
		
		this.setSize(300, 200);
		this.setVisible(false);
		
	}
	
	public void setController(IntroduceCardController c) {
		acceptButton.addActionListener(c);
	}
	
	public void setGoBackController(GoBackController gb) {
		goBackButton.addActionListener(gb);
	}
	
	public String getNumber() {
		return ccNumberField.getText();
	}

}
