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

import controllers.ChangesController;
import controllers.GoBackController;

public class ChangesWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1775724968032433117L;
	private JTextField changesField;
	private JButton acceptButton;
	private JButton goBackButton;

	public ChangesWindow() {
		Container cont = super.getContentPane();
		cont.setLayout(new BorderLayout());
		JPanel buttons = new JPanel(new GridLayout(1, 2));
		JPanel text = new JPanel(new FlowLayout());
		
		this.changesField = new JTextField("10");
		text.add(changesField);
		
		JLabel label = new JLabel("Introduce the required changes");
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
	public void setController(ChangesController c) {
		acceptButton.addActionListener(c);
		
	}

	public void setGoBackController(GoBackController g) {
		goBackButton.addActionListener(g);
		
	}
	
	public String getChanges() {
		return changesField.getText();
	}

}
