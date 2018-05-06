package windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.GoBackController;

public class SuggestionsWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7334966751127560777L;
	private JButton goBackButton;

	public SuggestionsWindow(String suggestions) {
		super("Suggestions");
		JPanel cont = new JPanel();
		cont.setLayout(new BorderLayout());
		
		JPanel messagePanel = new JPanel(new FlowLayout());
		goBackButton = new JButton("Go back");
		JLabel message = new JLabel(suggestions);
		messagePanel.add(message);
		cont.add(goBackButton, BorderLayout.SOUTH);
		cont.add(message, BorderLayout.CENTER);
		cont.add(new JLabel("Here are the suggestions made by an admin:"), BorderLayout.NORTH);
		this.setContentPane(cont);
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}
	
	public void setGoBackController(GoBackController gb) {
		goBackButton.addActionListener(gb);
	}
}
